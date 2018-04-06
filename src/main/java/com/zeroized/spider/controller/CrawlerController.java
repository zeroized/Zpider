package com.zeroized.spider.controller;

import com.zeroized.spider.crawler.CrawlControllerFactory;
import com.zeroized.spider.crawler.CrawlControllerOptions;
import com.zeroized.spider.crawler.CrawlerFactory;
import com.zeroized.spider.crawler.CrawlerOptions;
import com.zeroized.spider.domain.Column;
import com.zeroized.spider.domain.CrawlRequest;
import com.zeroized.spider.repo.elastic.ElasticRepo;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by Zero on 2018/3/23.
 */
@RestController
@RequestMapping("/crawl")
public class CrawlerController {
    private final CrawlControllerFactory crawlControllerFactory;
    private final ElasticRepo elasticRepo;

    @Autowired
    public CrawlerController(CrawlControllerFactory crawlControllerFactory, ElasticRepo elasticRepo) {
        this.crawlControllerFactory = crawlControllerFactory;
        this.elasticRepo = elasticRepo;
    }
//
//    @RequestMapping(method = RequestMethod.POST,value = "/start")
//    public String setup(@RequestParam CrawlerOptions crawlerOptions,
//                        @RequestParam String[] seeds) throws Exception {
//        CrawlControllerOptions options=CrawlControllerFactory.defaultOptions();
//        CrawlController crawlController=crawlControllerFactory.newController(options);
//        CrawlerFactory crawlerFactory=new CrawlerFactory(crawlerOptions,mongoRepo);
//        for (String seed : seeds) {
//            crawlController.addSeed(seed);
//        }
//        crawlController.startNonBlocking(crawlerFactory,options.getWorkers());
//        return "";
//    }

//    @RequestMapping(method = RequestMethod.POST,value = "/start")
//    public String setup(@RequestParam List<String> seeds,
//                        @RequestParam List<String> allowDomains,
//                        @RequestParam List<String> crawlUrlPrefixes,
//                        @RequestParam List<Column> columns) throws Exception {
//        System.out.println("/crawl/start visited");
//        System.out.println(seeds);
//        System.out.println(allowDomains);
//        System.out.println(crawlUrlPrefixes);
//        System.out.println(columns);
//        CrawlControllerOptions options=CrawlControllerOptions.defaultOptions();
//        CrawlController crawlController=crawlControllerFactory.newController(options);
//        CrawlerOptions crawlerOptions=new CrawlerOptions(allowDomains,crawlUrlPrefixes,columns);
//        CrawlerFactory crawlerFactory=new CrawlerFactory(crawlerOptions,mongoRepo);
//        for (String seed:seeds){
//            crawlController.addSeed(seed);
//        }
//        crawlController.startNonBlocking(crawlerFactory,options.getWorkers());
//        return "";
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/start")
    public String setup(@RequestBody CrawlRequest crawlRequest) throws Exception {
        System.out.println("/crawl/start visited");
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

        CrawlControllerOptions options = CrawlControllerOptions.defaultOptions();
        options.setWorkers(crawlRequest.getAdvancedOpt().getWorkers());
        options.setDelay(crawlRequest.getAdvancedOpt().getPoliteWait());
        options.setDepth(crawlRequest.getAdvancedOpt().getMaxDepth());
        options.setPage(crawlRequest.getAdvancedOpt().getMaxPage());
        options.setDir(crawlRequest.getName() + "\\");

        CrawlController crawlController = crawlControllerFactory.newController(options);

        PublishSubject<Map<String, ?>> crawlSubject = PublishSubject.create();
        crawlSubject.buffer(60, TimeUnit.SECONDS, Schedulers.computation(), 20,
                () -> Collections.synchronizedList(new LinkedList<>()), true)
                .subscribe(
                        elasticRepo::generateBulkIndex
//                        System.out::println
                );

        CrawlerOptions crawlerOptions = new CrawlerOptions(allowDomains, crawlUrlPrefixes, columns);
        System.out.println(crawlerOptions.toString());
        CrawlerFactory crawlerFactory = new CrawlerFactory(crawlerOptions, crawlSubject);
        for (String seed : seeds) {
            crawlController.addSeed(seed);
        }
        crawlController.startNonBlocking(crawlerFactory, options.getWorkers());
        return "";
    }
}
