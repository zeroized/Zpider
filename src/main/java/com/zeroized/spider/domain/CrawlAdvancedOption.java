package com.zeroized.spider.domain;

/**
 * Created by Zero on 2018/4/6.
 */
public class CrawlAdvancedOption {
    private int workers;
    private int maxDepth;
    private int maxPage;
    private int politeWait;

    public CrawlAdvancedOption() {
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
