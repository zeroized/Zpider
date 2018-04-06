package com.zeroized.spider.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Zero on 2018/3/22.
 */
@Component
public class CrawlControllerFactory {
    @Value("${crawler.config.storageBaseDir}")
    private String baseDir;

    public CrawlControllerFactory() {
    }

    public static CrawlControllerOptions defaultOptions() {
        return new CrawlControllerOptions();
    }

    public CrawlController newController(CrawlControllerOptions options) throws Exception {
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(baseDir + options.getDir());
        config.setMaxDepthOfCrawling(options.getDepth());
        config.setPolitenessDelay(options.getDelay());
        config.setResumableCrawling(options.isResumeable());
        config.setDefaultHeaders(options.getHeaders());
        if (options.getPage() != -1) {
            config.setMaxPagesToFetch(options.getPage());
        }
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        return new CrawlController(config, pageFetcher, robotstxtServer);
    }
}
