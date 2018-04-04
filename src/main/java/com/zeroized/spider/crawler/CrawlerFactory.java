package com.zeroized.spider.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import io.reactivex.subjects.PublishSubject;

import java.util.Map;

/**
 * Created by Zero on 2018/3/22.
 */
public class CrawlerFactory implements CrawlController.WebCrawlerFactory {
    private CrawlerOptions options;
    private PublishSubject<Map<String, ?>> publishSubject;

    public CrawlerFactory(CrawlerOptions options, PublishSubject<Map<String, ?>> publishSubject) {
        this.options = options;
        this.publishSubject = publishSubject;
    }

    public CrawlerOptions getOptions() {
        return options;
    }

    public void setOptions(CrawlerOptions options) {
        this.options = options;
    }

    @Override
    public WebCrawler newInstance() {
        return new Crawler(options, publishSubject);
    }
}
