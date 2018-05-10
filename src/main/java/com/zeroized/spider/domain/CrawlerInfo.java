package com.zeroized.spider.domain;

import com.zeroized.spider.crawler.CrawlerFactory;
import edu.uci.ics.crawler4j.crawler.CrawlController;

/**
 * Created by Zero on 2018/4/23.
 */
public class CrawlerInfo {
    public static final int READY = 0;
    public static final int STARTED = 1;
    public static final int PENDING_PROCESS = 2;
    public static final int STOPPED = 3;
    public static final int FINISHED = 4;
    public static final int ERROR = -1;

    private int status;

    private final CrawlController crawlController;

    private final CrawlerFactory crawlerFactory;

    private final CrawlConfig crawlConfig;

    public CrawlerInfo(CrawlController crawlController, CrawlerFactory crawlerFactory, CrawlConfig crawlConfig) {
        this.crawlController = crawlController;
        this.crawlerFactory = crawlerFactory;
        this.crawlConfig = crawlConfig;
        this.status= READY;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CrawlController getCrawlController() {
        return crawlController;
    }


    public CrawlerFactory getCrawlerFactory() {
        return crawlerFactory;
    }

    public CrawlConfig getCrawlConfig() {
        return crawlConfig;
    }
}
