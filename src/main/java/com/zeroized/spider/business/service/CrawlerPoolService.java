package com.zeroized.spider.business.service;

import com.zeroized.spider.business.pool.CrawlerPool;
import com.zeroized.spider.business.rx.CrawlerObservable;
import com.zeroized.spider.crawler.CrawlControllerFactory;
import com.zeroized.spider.crawler.CrawlControllerOptions;
import com.zeroized.spider.crawler.CrawlerFactory;
import com.zeroized.spider.crawler.CrawlerOptions;
import com.zeroized.spider.domain.CrawlerInfo;
import com.zeroized.spider.domain.CrawlerStatusInfo;
import com.zeroized.spider.domain.crawler.Column;
import com.zeroized.spider.domain.crawler.CrawlAdvConfig;
import com.zeroized.spider.domain.crawler.CrawlConfig;
import com.zeroized.spider.domain.observable.DataEntity;
import com.zeroized.spider.domain.repo.CrawlerInfoEntity;
import com.zeroized.spider.repo.mongo.CrawlerInfoRepo;
import com.zeroized.spider.util.IdGenerator;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Zero on 2018/5/7.
 */
@Service
public class CrawlerPoolService {
    public static final int SUCCESS = 1;
    public static final int ERROR = 0;

    private final CrawlerPool crawlerPool;

    private final CrawlerObservable crawlerObservable;

    private final CrawlControllerFactory crawlControllerFactory;

    private final CrawlerInfoRepo crawlerInfoRepo;

    private final MongoService mongoService;

    @Autowired
    public CrawlerPoolService(CrawlerPool crawlerPool, CrawlerObservable crawlerObservable, CrawlControllerFactory crawlControllerFactory, CrawlerInfoRepo crawlerInfoRepo, MongoService mongoService) {
        this.crawlerPool = crawlerPool;
        this.crawlerObservable = crawlerObservable;
        this.crawlControllerFactory = crawlControllerFactory;
        this.crawlerInfoRepo = crawlerInfoRepo;
        this.mongoService = mongoService;
    }

    @PostConstruct
    public void init() {
        initPool();
    }

    public String register(CrawlConfig crawlConfig) {
        String uuidName = IdGenerator.generateUUID();
        CrawlController controller;
        try {
            controller = configController(crawlConfig.getName(),
                    crawlConfig.getAdvancedOpt(), crawlConfig.getSeeds());
            CrawlerFactory crawlerFactory = configCrawler(crawlConfig.getAllowDomains(),
                    crawlConfig.getCrawlUrlPrefixes(), crawlConfig.getColumns(),
                    uuidName, crawlConfig.getName());
            CrawlerInfo info = crawlerPool.register(uuidName, CrawlerInfo.READY,
                    controller, crawlerFactory, crawlConfig);
            if (info != null) {
                crawlerInfoRepo.save(new CrawlerInfoEntity(info.getId(), info.getStatus(),
                        info.getCrawlConfig()));
//                elasticService.createIndex(uuidName);
                return info.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CrawlerStatusInfo> getAllCrawler() {
        List<CrawlerInfoEntity> crawlers = crawlerInfoRepo.findAll();
        return crawlers.stream()
                .map(x -> {
                    String uuid = x.getId();
                    int status = x.getStatus();
                    return statusToStatusInfo(uuid, status);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<String> getCrawlerNameWithResult(){
        return crawlerInfoRepo.findByStatusIsNot(CrawlerInfo.READY).stream()
                .map(CrawlerInfoEntity::getId).collect(Collectors.toList());
    }

    public int startCrawler(String uuid) {
        int status = crawlerPool.changeStatus(uuid, CrawlerPool.START_CRAWLER);
        return updateToDatabase(status, uuid);
    }

    public int stopCrawler(String uuid) {
        int status = crawlerPool.changeStatus(uuid, CrawlerPool.STOP_CRAWLER);
        return updateToDatabase(status, uuid);
    }

    public int restartCrawler(String uuid){
        int status=crawlerPool.changeStatus(uuid,CrawlerPool.RESTART_CRAWLER);
        return updateToDatabase(status,uuid);
    }

    public void getCrawlerStatus(String uuid) {
        CrawlerInfo crawlerInfo = crawlerPool.get(uuid);
    }

    public CrawlConfig getConfig(String uuid) {
        return crawlerInfoRepo.findById(uuid).get().getCrawlConfig();
    }

    private void initPool() {
        List<CrawlerInfoEntity> allCrawlers=crawlerInfoRepo.findAll();
        allCrawlers.forEach(x->{
            List<DataEntity> testList=mongoService.findByCrawlerId(x.getId());
            if (testList.size()==0&&x.getStatus()!=CrawlerInfo.READY){
                crawlerInfoRepo.deleteById(x.getId());
            }
        });
        List<CrawlerInfoEntity> crawlerInfoList = crawlerInfoRepo.findByStatusOrStatus(CrawlerInfo.READY,CrawlerInfo.STOPPED);
        crawlerInfoList.forEach(this::register);
    }

    private String register(CrawlerInfoEntity crawlerInfoEntity) {
        CrawlConfig crawlConfig = crawlerInfoEntity.getCrawlConfig();
        CrawlController controller = null;
        try {
            controller = configController(crawlConfig.getName(),
                    crawlConfig.getAdvancedOpt(), crawlConfig.getSeeds());
            CrawlerFactory crawlerFactory = configCrawler(crawlConfig.getAllowDomains(),
                    crawlConfig.getCrawlUrlPrefixes(), crawlConfig.getColumns(),
                    crawlerInfoEntity.getId(), crawlConfig.getName());
            CrawlerInfo info = crawlerPool.register(crawlerInfoEntity.getId(), crawlerInfoEntity.getStatus(),
                    controller, crawlerFactory, crawlConfig);
            if (info != null) {
                crawlerInfoRepo.save(new CrawlerInfoEntity(info.getId(), info.getStatus(),
                        info.getCrawlConfig()));
                return info.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private CrawlController configController(String name, CrawlAdvConfig advConfig, List<String> seeds) throws Exception {
        CrawlControllerOptions options = crawlControllerFactory.createOption();
        options.setWorkers(advConfig.getWorkers());
        options.setDelay(advConfig.getPoliteWait());
        options.setDepth(advConfig.getMaxDepth());
        options.setPage(advConfig.getMaxPage());
        options.setDir(name + "\\");

        CrawlController crawlController = crawlControllerFactory.newController(options);
        for (String seed : seeds) {
            crawlController.addSeed(seed);
        }
        return crawlController;
    }

    private CrawlerFactory configCrawler(List<String> allowDomains, List<String> crawlUrlPrefixes, List<Column> columns, String indexId, String name) {
        CrawlerOptions crawlerOptions = new CrawlerOptions(allowDomains, crawlUrlPrefixes, columns, indexId, name);
        return new CrawlerFactory(crawlerOptions, crawlerObservable);
    }

    private static CrawlerStatusInfo statusToStatusInfo(String uuid, int status) {
        switch (status) {
            case CrawlerInfo.READY:
                return new CrawlerStatusInfo(uuid, "就绪", "启动,start;修改配置,revise;删除,delete");
            case CrawlerInfo.STARTED:
                return new CrawlerStatusInfo(uuid, "运行中", "停止,stop;查看已有结果,show");
            case CrawlerInfo.ERROR:
                return new CrawlerStatusInfo(uuid, "错误", "查看错误,show");
            case CrawlerInfo.FINISHED:
                return new CrawlerStatusInfo(uuid, "完成", "查看结果,show");
            case CrawlerInfo.PENDING_PROCESS:
                return new CrawlerStatusInfo(uuid, "待处理", "查看待处理的事件,show");
            case CrawlerInfo.STOPPED:
                return new CrawlerStatusInfo(uuid, "停止", "查看结果,show;继续,resume;重启,restart");
        }
        return null;
    }

    private int updateToDatabase(int status, String uuid) {
        if (status == CrawlerPool.SUCCESS) {
            CrawlerInfo crawlerInfo = crawlerPool.get(uuid);
            crawlerInfoRepo.save(new CrawlerInfoEntity(crawlerInfo.getId(),
                    crawlerInfo.getStatus(),
                    crawlerInfo.getCrawlConfig()));
        }
        return status;
    }

    @PreDestroy
    private void destroy(){
        List<CrawlerInfoEntity> entities=crawlerInfoRepo.findByStatus(CrawlerInfo.STARTED);
        entities.forEach(x->stopCrawler(x.getId()));
    }
}
