package com.zeroized.spider.domain;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2018/3/23.
 */
public class Data {
    @Id
    private Long id;

    private Map<String, List<String>> data;

    public Data() {
    }

    public Data(Long id, Map<String, List<String>> data) {
        this.id = id;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, List<String>> getData() {
        return data;
    }

    public void setData(Map<String, List<String>> data) {
        this.data = data;
    }
}
