package com.zeroized.spider.domain.observable;

import java.util.List;

/**
 * Created by Zero on 2018/4/10.
 */
public class WarningEntity {
    private String id;
    private String warningUrl;
    private List<String> warningColumns;

    public WarningEntity() {
    }

    public WarningEntity(String id, String warningUrl, List<String> warningColumns) {
        this.id = id;
        this.warningUrl = warningUrl;
        this.warningColumns = warningColumns;
    }

    public String getWarningUrl() {
        return warningUrl;
    }

    public void setWarningUrl(String warningUrl) {
        this.warningUrl = warningUrl;
    }

    public List<String> getWarningColumns() {
        return warningColumns;
    }

    public void setWarningColumns(List<String> warningColumns) {
        this.warningColumns = warningColumns;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WarningEntity{" +
                "id='" + id + '\'' +
                ", warningUrl='" + warningUrl + '\'' +
                ", warningColumns=" + warningColumns +
                '}';
    }
}
