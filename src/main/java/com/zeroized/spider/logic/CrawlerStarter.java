package com.zeroized.spider.logic;

import com.zeroized.spider.crawler.CrawlControllerFactory;
import com.zeroized.spider.crawler.CrawlControllerOptions;
import com.zeroized.spider.crawler.CrawlerFactory;
import com.zeroized.spider.crawler.CrawlerOptions;
import com.zeroized.spider.domain.Column;
import com.zeroized.spider.domain.CrawlAdvancedOption;
import com.zeroized.spider.logic.pool.CrawlerPool;
import com.zeroized.spider.logic.rx.CrawlerObservable;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Zero on 2018/4/10.
 */
@Component
public class CrawlerStarter {
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;
    private final CrawlerObservable crawlerObservable;
    private final CrawlControllerFactory crawlControllerFactory;
    private final CrawlerPool crawlerPool;

    @Autowired
    public CrawlerStarter(CrawlerObservable crawlerObservable, CrawlControllerFactory crawlControllerFactory, CrawlerPool crawlerPool) {
        this.crawlerObservable = crawlerObservable;
        this.crawlControllerFactory = crawlControllerFactory;
        this.crawlerPool = crawlerPool;
    }

    public int start(String name, List<String> seeds, List<String> allowDomains, List<String> crawlUrlPrefixes,
                     List<Column> columns, CrawlAdvancedOption advancedOption) throws Exception {
        CrawlControllerOptions options = crawlControllerFactory.createOption();
        options.setWorkers(advancedOption.getWorkers());
        options.setDelay(advancedOption.getPoliteWait());
        options.setDepth(advancedOption.getMaxDepth());
        options.setPage(advancedOption.getMaxPage());
        options.setDir(name + "\\");

        CrawlController crawlController = crawlControllerFactory.newController(options);

        CrawlerOptions crawlerOptions = new CrawlerOptions(allowDomains, crawlUrlPrefixes, columns, name);
        CrawlerFactory crawlerFactory = new CrawlerFactory(crawlerOptions, crawlerObservable);
        for (String seed : seeds) {
            crawlController.addSeed(seed);
        }
        crawlController.startNonBlocking(crawlerFactory, options.getWorkers());
        return SUCCESS;
    }
}
