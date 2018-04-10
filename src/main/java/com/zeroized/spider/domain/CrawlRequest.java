package com.zeroized.spider.domain;

import java.util.List;

/**
 * Created by Zero on 2018/3/28.
 */
public class CrawlRequest {
    private List<String> seeds;
    private List<String> allowDomains;
    private List<String> crawlUrlPrefixes;
    private List<Column> columns;
    private CrawlAdvancedOption advancedOpt;
    private String name;

    public CrawlRequest() {
    }

    public List<String> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<String> seeds) {
        this.seeds = seeds;
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

    public CrawlAdvancedOption getAdvancedOpt() {
        return advancedOpt;
    }

    public void setAdvancedOpt(CrawlAdvancedOption advancedOpt) {
        this.advancedOpt = advancedOpt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
