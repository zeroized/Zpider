package com.zeroized.spider.domain;

/**
 * Created by Zero on 2018/5/9.
 */
public class CrawlerStatusInfo {

    private String uuid;

    private String status;

    private String options;

    public CrawlerStatusInfo(String uuid, String status, String options) {
        this.uuid = uuid;
        this.status = status;
        this.options = options;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
