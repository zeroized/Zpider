package com.zeroized.spider.crawler;

import org.apache.http.message.BasicHeader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Zero on 2018/3/22.
 */
public class CrawlControllerOptions {

    private int workers;

    private String dir;

    private boolean resumeable = false;

    private int delay;

    private int depth;

    private int page;

    private Map<String, String> headers = new HashMap<>();

    public CrawlControllerOptions(int workers, String dir, boolean resumeable, int delay, int depth, int page) {
        this.workers = workers;
        this.dir = dir;
        this.resumeable = resumeable;
        this.delay = delay;
        this.depth = depth;
        this.page = page;
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        headers.put("Connection", "keep-alive");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public boolean isResumeable() {
        return resumeable;
    }

    public void setResumeable(boolean resumeable) {
        this.resumeable = resumeable;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    public List<BasicHeader> getHeaders() {
        return headers.entrySet().stream().map(x -> new BasicHeader(x.getKey(), x.getValue())).collect(Collectors.toList());
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
