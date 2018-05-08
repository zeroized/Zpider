package com.zeroized.spider.logic.pool;

import com.zeroized.spider.config.CrawlerPoolConfig;
import com.zeroized.spider.domain.CrawlerInfo;
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
    private final CrawlerPoolConfig crawlerPoolConfig;
    private Map<String, CrawlerInfo> pool;
    private Map<String, CrawlerInfo> finishedCrawler;
    private volatile int currentCrawler = 0;

    @Autowired
    public CrawlerPool(CrawlerPoolConfig crawlerPoolConfig) {
        this.crawlerPoolConfig = crawlerPoolConfig;
    }

    @PostConstruct
    public void init() {
        pool = new HashMap<>(crawlerPoolConfig.getPoolSize());
    }

    public void register(String name, CrawlController crawlController) {

    }

}
