package com.zeroized.spider.crawler;

import com.zeroized.spider.business.rx.CrawlerObservable;
import edu.uci.ics.crawler4j.crawler.CrawlController;

/**
 * Created by Zero on 2018/3/22.
 */
public class CrawlerFactory implements CrawlController.WebCrawlerFactory<Crawler> {
    private CrawlerOptions options;
    private CrawlerObservable crawlerObservable;

    public CrawlerFactory(CrawlerOptions options, CrawlerObservable crawlerObservable) {
        this.options = options;
        this.crawlerObservable = crawlerObservable;
    }

    public CrawlerOptions getOptions() {
        return options;
    }

    public void setOptions(CrawlerOptions options) {
        this.options = options;
    }

    @Override
    public Crawler newInstance() {
        return new Crawler(options, crawlerObservable);
    }
}
