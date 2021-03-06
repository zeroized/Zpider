package com.zeroized.spider.business.pool;

import com.zeroized.spider.config.CrawlerPoolConfig;
import com.zeroized.spider.crawler.CrawlerFactory;
import com.zeroized.spider.domain.CrawlerInfo;
import com.zeroized.spider.domain.crawler.CrawlConfig;
import com.zeroized.spider.util.IdGenerator;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zero on 2018/4/21.
 */
@Component
public class CrawlerPool {
    public static final int SUCCESS = 1;
    public static final int ERROR = 0;

    public static final int START_CRAWLER = 1;
    public static final int STOP_CRAWLER = 2;
    public static final int RESTART_CRAWLER = 4;

    private final CrawlerPoolConfig crawlerPoolConfig;

    private Map<String, CrawlerInfo> pool;

    private Map<String, CrawlerInfo> finishedCrawler;
//    private List<CrawlerInfo> waitingList;
//    private volatile int currentCrawler = 0;

    @Autowired
    public CrawlerPool(CrawlerPoolConfig crawlerPoolConfig) {
        this.crawlerPoolConfig = crawlerPoolConfig;
    }

    @PostConstruct
    public void init() {
        pool = new HashMap<>(crawlerPoolConfig.getPoolSize());
//        waitingList=new LinkedList<>();
    }

    public CrawlerInfo register(CrawlController crawlController, CrawlerFactory crawlerFactory, CrawlConfig crawlConfig) {
        String uuidName = IdGenerator.generateUUID();
        CrawlerInfo info = new CrawlerInfo(uuidName, crawlController, crawlerFactory, crawlConfig);
//        if (currentCrawler==crawlerPoolConfig.getPoolSize()){
//            waitingList.add(info);
//            return;
//        }
        int createStatus = addCrawlerToPool(uuidName, info);
        if (createStatus == SUCCESS) {
            return info;
        } else {
            return null;
        }
    }

    public CrawlerInfo register(String uuidName, int status, CrawlController crawlController, CrawlerFactory crawlerFactory, CrawlConfig crawlConfig) {
        CrawlerInfo info = new CrawlerInfo(uuidName, crawlController, crawlerFactory, crawlConfig);
        info.setStatus(status);
//        if (currentCrawler==crawlerPoolConfig.getPoolSize()){
//            waitingList.add(info);
//            return;
//        }
        int createStatus = addCrawlerToPool(uuidName, info);
        if (createStatus == SUCCESS) {
            return info;
        } else {
            return null;
        }
    }

    public Map<String, CrawlerInfo> getAll() {
        return pool;
    }

    public int changeStatus(String uuid, int operate) {
        CrawlerInfo targetCrawler = pool.get(uuid);
        if (targetCrawler != null) {
            int status = targetCrawler.getStatus();
            if (!verifyStatusChange(status, operate)) {
                return ERROR;
            }
            switch (operate) {
                case START_CRAWLER:
                    return start(targetCrawler);
                case STOP_CRAWLER:
                    return stop(targetCrawler);
                case RESTART_CRAWLER:
                    return start(targetCrawler);
            }
        }
        return ERROR;
    }

    public CrawlerInfo get(String uuid) {
        return pool.get(uuid);
    }

    private int addCrawlerToPool(String name, CrawlerInfo crawlerInfo) {
        pool.put(name, crawlerInfo);
        return SUCCESS;
    }

    private int start(CrawlerInfo crawler) {
        crawler.setStatus(CrawlerInfo.STARTED);
        CrawlerFactory factory = crawler.getCrawlerFactory();
        crawler.getCrawlController().startNonBlocking(factory, crawler.getCrawlConfig().getAdvancedOpt().getWorkers());
        return SUCCESS;
    }

    private int stop(CrawlerInfo crawler) {
        crawler.setStatus(CrawlerInfo.STOPPED);
        CrawlController controller = crawler.getCrawlController();
        controller.shutdown();
        controller.waitUntilFinish();
        return SUCCESS;
    }

    private boolean verifyStatusChange(int oldStatus, int operate) {
        return operate == oldStatus + 1;
    }
}
