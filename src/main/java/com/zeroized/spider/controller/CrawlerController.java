package com.zeroized.spider.controller;

import com.zeroized.spider.domain.Column;
import com.zeroized.spider.domain.CrawlRequest;
import com.zeroized.spider.logic.CrawlerStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zero on 2018/3/23.
 */
@RestController
@RequestMapping("/crawl")
public class CrawlerController {
    private final CrawlerStarter crawlerStarter;

    @Autowired
    public CrawlerController(CrawlerStarter crawlerStarter) {
        this.crawlerStarter = crawlerStarter;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/start")
    public String setup(@RequestBody CrawlRequest crawlRequest) throws Exception {
//        System.out.println("/crawl/start visited");
        List<String> seeds = crawlRequest.getSeeds();
        List<String> allowDomains = crawlRequest.getAllowDomains()
                .stream()
                .map(x -> x.startsWith("http://") ? x : "http://" + x)
                .collect(Collectors.toList());
        List<String> crawlUrlPrefixes = crawlRequest.getCrawlUrlPrefixes()
                .stream()
                .map(x -> x.startsWith("http://") ? x : "http://" + x)
                .collect(Collectors.toList());

        List<Column> columns = crawlRequest.getColumns();

        crawlerStarter.start(crawlRequest.getName(), seeds, allowDomains,
                crawlUrlPrefixes, columns, crawlRequest.getAdvancedOpt());
//        CrawlControllerOptions options = CrawlControllerOptions.defaultOptions();
//        options.setWorkers(crawlRequest.getAdvancedOpt().getWorkers());
//        options.setDelay(crawlRequest.getAdvancedOpt().getPoliteWait());
//        options.setDepth(crawlRequest.getAdvancedOpt().getMaxDepth());
//        options.setPage(crawlRequest.getAdvancedOpt().getMaxPage());
//        options.setDir(crawlRequest.getName() + "\\");
//
//        CrawlController crawlController = crawlControllerFactory.newController(options);
//
//        PublishSubject<Map<String, ?>> crawlSubject = PublishSubject.create();
//        crawlSubject.buffer(60, TimeUnit.SECONDS, Schedulers.computation(), 20,
//                () -> Collections.synchronizedList(new LinkedList<>()), true)
//                .subscribe(
//                        elasticRepo::generateBulkIndex
////                        System.out::println
//                );
//
//        CrawlerOptions crawlerOptions = new CrawlerOptions(allowDomains, crawlUrlPrefixes, columns);
//        System.out.println(crawlerOptions.toString());
//        CrawlerFactory crawlerFactory = new CrawlerFactory(crawlerOptions, crawlSubject);
//        for (String seed : seeds) {
//            crawlController.addSeed(seed);
//        }
//        crawlController.startNonBlocking(crawlerFactory, options.getWorkers());
        return "";
    }
}
