package com.zeroized.spider.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Zero on 2018/5/16.
 */
@Document
public class CrawlerInfoEntity {
    @Id
    private String id;

    private int status;

    private CrawlConfig crawlConfig;

    public CrawlerInfoEntity(String id, int status, CrawlConfig crawlConfig) {
        this.id = id;
        this.status = status;
        this.crawlConfig = crawlConfig;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CrawlConfig getCrawlConfig() {
        return crawlConfig;
    }

    public void setCrawlConfig(CrawlConfig crawlConfig) {
        this.crawlConfig = crawlConfig;
    }
}
