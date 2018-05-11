package com.zeroized.spider.crawler;

import com.zeroized.spider.domain.DistributedPageFetcher;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Zero on 2018/3/22.
 */
@Component
public class CrawlControllerFactory {
    @Value("${crawler.config.storage-dir}")
    private String baseDir;

    @Value("${crawler.config.default.workers:1}")
    private int workers;

    @Value("${crawler.config.default.dir:'test\\'}")
    private String dir;

    private boolean resumeable = false;

    @Value("${crawler.config.default.polite-wait:1000}")
    private int delay;

    @Value("${crawler.config.default.max-depth:-1}")
    private int depth;

    @Value("${crawler.config.default.max-page:-1}")
    private int page;

    public CrawlControllerFactory() {
    }

    public CrawlControllerOptions createOption() {
        return new CrawlControllerOptions(workers, dir, resumeable, delay, depth, page);
    }

    public CrawlController newController(CrawlControllerOptions options) throws Exception {
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(baseDir + options.getDir());
        config.setMaxDepthOfCrawling(options.getDepth());
        config.setPolitenessDelay(options.getDelay());
        config.setResumableCrawling(options.isResumeable());
        config.setDefaultHeaders(options.getHeaders());
        config.setMaxPagesToFetch(options.getPage());

        config.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");

//        AuthInfo info=new FormAuthInfo();

        DistributedPageFetcher pageFetcher = new DistributedPageFetcher(config);

        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();

        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        return new CrawlController(config, pageFetcher, robotstxtServer);
    }
}
