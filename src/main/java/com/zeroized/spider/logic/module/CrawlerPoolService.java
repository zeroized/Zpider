package com.zeroized.spider.logic.module;

import com.zeroized.spider.crawler.CrawlControllerFactory;
import com.zeroized.spider.crawler.CrawlControllerOptions;
import com.zeroized.spider.crawler.CrawlerFactory;
import com.zeroized.spider.crawler.CrawlerOptions;
import com.zeroized.spider.domain.*;
import com.zeroized.spider.logic.pool.CrawlerPool;
import com.zeroized.spider.logic.rx.CrawlerObservable;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    @Autowired
    public CrawlerPoolService(CrawlerPool crawlerPool, CrawlerObservable crawlerObservable, CrawlControllerFactory crawlControllerFactory) {
        this.crawlerPool = crawlerPool;
        this.crawlerObservable = crawlerObservable;
        this.crawlControllerFactory = crawlControllerFactory;
    }

    public String register(CrawlConfig crawlConfig) throws Exception {
        CrawlController controller = configController(crawlConfig.getName(),
                crawlConfig.getAdvancedOpt(), crawlConfig.getSeeds());
        CrawlerFactory crawlerFactory = configCrawler(crawlConfig.getAllowDomains(),
                crawlConfig.getCrawlUrlPrefixes(), crawlConfig.getColumns(), crawlConfig.getName());
        return crawlerPool.register(controller, crawlerFactory, crawlConfig);
    }

    public List<CrawlerStatusInfo> getAllCrawler() {
        Map<String, CrawlerInfo> crawlers = crawlerPool.getAll();
        return crawlers.entrySet().stream()
                .map(x -> {
                    String uuid = x.getKey();
                    int status = x.getValue().getStatus();
                    return statusToStatusInfo(uuid,status);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public int startCrawler(String uuid) {
        return crawlerPool.changeStatus(uuid, CrawlerPool.START_CRAWLER);
    }

    public int stopCrawler(String uuid) {
        return crawlerPool.changeStatus(uuid, CrawlerPool.STOP_CRAWLER);
    }

    public void getCrawlerStatus(String uuid) {
        CrawlerInfo crawlerInfo=crawlerPool.get(uuid);
    }

    public CrawlConfig getConfig(String uuid){
        return crawlerPool.get(uuid).getCrawlConfig();
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

    private CrawlerFactory configCrawler(List<String> allowDomains, List<String> crawlUrlPrefixes, List<Column> columns, String name) {
        CrawlerOptions crawlerOptions = new CrawlerOptions(allowDomains, crawlUrlPrefixes, columns, name);
        return new CrawlerFactory(crawlerOptions, crawlerObservable);
    }

    private static CrawlerStatusInfo statusToStatusInfo(String uuid,int status){
        switch (status) {
            case CrawlerInfo.READY:
                return new CrawlerStatusInfo(uuid,"就绪","启动,start;修改配置,revise;删除,delete");
            case CrawlerInfo.STARTED:
                return new CrawlerStatusInfo(uuid, "运行中", "停止,stop;查看已有结果,show");
            case CrawlerInfo.ERROR:
                return new CrawlerStatusInfo(uuid, "错误", "查看错误,show");
            case CrawlerInfo.FINISHED:
                return new CrawlerStatusInfo(uuid, "完成", "查看结果,show");
            case CrawlerInfo.PENDING_PROCESS:
                return new CrawlerStatusInfo(uuid,"待处理","查看待处理的事件,show");
            case CrawlerInfo.STOPPED:
                return new CrawlerStatusInfo(uuid, "停止", "查看结果,show;继续,resume;重启,restart");
        }
        return null;
    }
}
