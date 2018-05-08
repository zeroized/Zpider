package com.zeroized.spider.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zero on 2018/3/28.
 */
public class CrawlConfig {
    private List<String> seeds;
    private List<String> allowDomains;
    private List<String> crawlUrlPrefixes;
    private List<Column> columns;
    private CrawlAdvConfig advancedOpt;
    private String name;

    public CrawlConfig() {
        seeds = new LinkedList<>();
        allowDomains = new LinkedList<>();
        crawlUrlPrefixes = new LinkedList<>();
        columns = new LinkedList<>();
        advancedOpt = new CrawlAdvConfig();
    }

    public List<String> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<String> seeds) {
        this.seeds = seeds;
    }

    public void addSeed(String seed) {
        this.seeds.add(seed);
    }

    public List<String> getAllowDomains() {
        return allowDomains;
    }

    public void setAllowDomains(List<String> allowDomains) {
        this.allowDomains = allowDomains;
    }

    public void addAllowDomain(String allowDomain) {
        this.allowDomains.add(allowDomain);
    }

    public List<String> getCrawlUrlPrefixes() {
        return crawlUrlPrefixes;
    }

    public void setCrawlUrlPrefixes(List<String> crawlUrlPrefixes) {
        this.crawlUrlPrefixes = crawlUrlPrefixes;
    }

    public void addCrawlUrlPrefix(String crawlUrlPrefix) {
        this.crawlUrlPrefixes.add(crawlUrlPrefix);
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void addColumn(Column column) {
        this.columns.add(column);
    }

    public CrawlAdvConfig getAdvancedOpt() {
        return advancedOpt;
    }

    public void setAdvancedOpt(CrawlAdvConfig advancedOpt) {
        this.advancedOpt = advancedOpt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
