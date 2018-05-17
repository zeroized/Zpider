package com.zeroized.spider.domain.crawler;

/**
 * Created by Zero on 2018/4/6.
 */
public class CrawlAdvConfig {
    private int workers=1;
    private int maxDepth=10;
    private int maxPage=1000;
    private int politeWait=1000;

    public CrawlAdvConfig() {
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getPoliteWait() {
        return politeWait;
    }

    public void setPoliteWait(int politeWait) {
        this.politeWait = politeWait;
    }
}
