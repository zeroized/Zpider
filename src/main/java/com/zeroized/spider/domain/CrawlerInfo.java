package com.zeroized.spider.domain;

import edu.uci.ics.crawler4j.crawler.CrawlController;

/**
 * Created by Zero on 2018/4/23.
 */
public class CrawlerInfo {
    public static final int NOT_STARTED = 0;
    public static final int RUNNING = 1;
    public static final int PENDING_PROCESS = 2;
    public static final int ERROR = -1;

    private int status;

    private CrawlController crawlController;

    public CrawlerInfo(CrawlController crawlController) {
        this.crawlController = crawlController;
        this.status = NOT_STARTED;
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

    public void setCrawlController(CrawlController crawlController) {
        this.crawlController = crawlController;
    }
}
