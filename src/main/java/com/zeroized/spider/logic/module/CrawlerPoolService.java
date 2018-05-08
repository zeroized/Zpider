package com.zeroized.spider.logic.module;

import com.zeroized.spider.domain.CrawlConfig;
import com.zeroized.spider.logic.pool.CrawlerPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Zero on 2018/5/7.
 */
@Service
public class CrawlerPoolService {

    private final CrawlerPool crawlerPool;

    @Autowired
    public CrawlerPoolService(CrawlerPool crawlerPool) {
        this.crawlerPool = crawlerPool;
    }

    public void register(CrawlConfig crawlConfig) {

    }
}
