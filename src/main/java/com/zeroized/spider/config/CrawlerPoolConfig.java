package com.zeroized.spider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Zero on 2018/4/23.
 */
@Component
public class CrawlerPoolConfig {

    @Value("${crawler.pool.max-size:4}")
    private int poolSize;

    public CrawlerPoolConfig() {
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
}
