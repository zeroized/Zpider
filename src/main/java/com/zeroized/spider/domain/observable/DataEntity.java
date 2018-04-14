package com.zeroized.spider.domain.observable;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2018/3/23.
 */
public class DataEntity {
    private String id;

    private String type;

    private Map<String, List<String>> data;


    public DataEntity() {
    }

    public DataEntity(String id, String type, Map<String, List<String>> data) {
        this.id = id;
        this.type = type;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, List<String>> getData() {
        return data;
    }

    public void setData(Map<String, List<String>> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DataEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}
