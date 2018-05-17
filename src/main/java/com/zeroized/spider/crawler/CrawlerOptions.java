package com.zeroized.spider.crawler;

import com.zeroized.spider.domain.crawler.Column;

import java.util.List;

/**
 * Created by Zero on 2018/3/22.
 */
public class CrawlerOptions {
    private List<String> allowDomains;
    private List<String> crawlUrlPrefixes;
    private List<Column> columns;
    private String indexId;
    private String type;

    public CrawlerOptions() {
    }

    public CrawlerOptions(List<String> allowDomains, List<String> crawlUrlPrefixes, List<Column> columns, String indexId, String type) {
        this.allowDomains = allowDomains;
        this.crawlUrlPrefixes = crawlUrlPrefixes;
        this.columns = columns;
        this.indexId = indexId;
        this.type = type;
    }

    public List<String> getAllowDomains() {
        return allowDomains;
    }

    public void setAllowDomains(List<String> allowDomains) {
        this.allowDomains = allowDomains;
    }

    public List<String> getCrawlUrlPrefixes() {
        return crawlUrlPrefixes;
    }

    public void setCrawlUrlPrefixes(List<String> crawlUrlPrefixes) {
        this.crawlUrlPrefixes = crawlUrlPrefixes;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "CrawlerOptions{" +
                "allowDomains=" + allowDomains.toString() +
                ", crawlUrlPrefixes=" + crawlUrlPrefixes.toString() +
                ", columns=" + columns.toString() +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }
}