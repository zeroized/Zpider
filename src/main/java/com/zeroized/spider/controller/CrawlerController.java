package com.zeroized.spider.controller;

import com.zeroized.spider.domain.Column;
import com.zeroized.spider.domain.CrawlAdvConfig;
import com.zeroized.spider.domain.CrawlConfig;
import com.zeroized.spider.logic.CrawlerStarter;
import com.zeroized.spider.logic.module.CrawlerPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zero on 2018/3/23.
 */
@RestController
@RequestMapping("/crawl")
@SessionAttributes("crawlerConfig")
public class CrawlerController {

    private final CrawlerStarter crawlerStarter;

    private final CrawlerPoolService crawlerPoolService;

    @Autowired
    public CrawlerController(CrawlerStarter crawlerStarter, CrawlerPoolService crawlerPoolService) {
        this.crawlerStarter = crawlerStarter;
        this.crawlerPoolService = crawlerPoolService;
    }

    @PostMapping("/config/basic/{field}/{operate}")
    public MessageBean configBasic(@PathVariable("field") String field, @PathVariable("operate") String operate,
                                   @RequestParam String value,
                                   @ModelAttribute("crawlerConfig") CrawlConfig crawlConfig) {
        MessageBean messageBean;
        List<String> configList;
        switch (field) {
            case "name":
                crawlConfig.setName(value);
                messageBean = MessageBean.successBean();
                messageBean.getMessage().put("data", value);
                return messageBean;
            case "seed":
                configList = crawlConfig.getSeeds();
                break;
            case "domain":
                configList = crawlConfig.getAllowDomains();
                break;
            case "crawl":
                configList = crawlConfig.getCrawlUrlPrefixes();
                break;
            default:
                messageBean = MessageBean.errorBean();
                messageBean.getMessage().put("error", "config field is invalid");
                return messageBean;
        }
        switch (operate) {
            case "add":
                if (configList.contains(value)) {
                    messageBean = MessageBean.errorBean();
                    messageBean.getMessage().put("error", "seed exists");
                } else {
                    configList.add(value);
                    messageBean = MessageBean.successBean();
                }
                break;
            case "delete":
                if (!configList.contains(value)) {
                    messageBean = MessageBean.errorBean();
                    messageBean.getMessage().put("error", "seed does not exist");
                } else {
                    configList.remove(value);
                    messageBean = MessageBean.successBean();
                }
                break;
            default:
                messageBean = MessageBean.errorBean();
                messageBean.getMessage().put("error", "operate is invalid");
                return messageBean;
        }

        messageBean.getMessage().put("data", configList);
        return messageBean;
    }

    @PostMapping("/config/basic/column/{operate}")
    public MessageBean configColumn(@PathVariable("operate") String operate,
                                    @RequestParam String value, @RequestParam(required = false) String rule,
                                    @RequestParam(required = false) String type,
                                    @ModelAttribute("crawlerConfig") CrawlConfig crawlConfig) {
        MessageBean messageBean;
        List<Column> columns = crawlConfig.getColumns();
        switch (operate) {
            case "add":
                for (Column column : columns) {
                    if (column.getColumn().equals(value)) {
                        messageBean = MessageBean.errorBean();
                        messageBean.getMessage().put("error", "column exists");
                        return messageBean;
                    }
                }
                Column column = new Column(value, rule, type);
                columns.add(column);
                messageBean = MessageBean.successBean();
                messageBean.getMessage().put("data", columns);
                break;
            case "delete":
                for (Column column1 : columns) {
                    if (column1.getColumn().equals(value)) {
                        columns.remove(column1);
                        messageBean = MessageBean.successBean();
                        messageBean.getMessage().put("data", columns);
                        return messageBean;
                    }
                }
                messageBean = MessageBean.errorBean();
                messageBean.getMessage().put("error", "column does not exist");
                break;
            default:
                messageBean = MessageBean.errorBean();
                messageBean.getMessage().put("error", "operate invalid");
        }
        return messageBean;
    }

    @PostMapping("/config/adv")
    public MessageBean configAdv(@RequestParam int workers, @RequestParam int maxDepth,
                                 @RequestParam int maxPage, @RequestParam int politeWait,
                                 @ModelAttribute("crawlerConfig") CrawlConfig crawlConfig) {
        MessageBean messageBean;
        CrawlAdvConfig crawlAdvConfig = crawlConfig.getAdvancedOpt();
        crawlAdvConfig.setWorkers(workers);
        crawlAdvConfig.setMaxDepth(maxDepth);
        crawlAdvConfig.setMaxPage(maxPage);
        crawlAdvConfig.setPoliteWait(politeWait);
        messageBean = MessageBean.successBean();
        messageBean.getMessage().put("data", crawlAdvConfig);
        return messageBean;
    }

    @GetMapping("/config/confirm")
    public MessageBean confirm(@ModelAttribute("crawlerConfig") CrawlConfig crawlConfig) {
        MessageBean messageBean = MessageBean.successBean();
        messageBean.getMessage().put("data", crawlConfig);
        return messageBean;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/start")
    public String setup(@RequestBody CrawlConfig crawlConfig) throws Exception {
//        System.out.println("/crawl/start visited");
        List<String> seeds = crawlConfig.getSeeds();
        List<String> allowDomains = crawlConfig.getAllowDomains()
                .stream()
                .map(x -> x.startsWith("http://") ? x : "http://" + x)
                .collect(Collectors.toList());
        List<String> crawlUrlPrefixes = crawlConfig.getCrawlUrlPrefixes()
                .stream()
                .map(x -> x.startsWith("http://") ? x : "http://" + x)
                .collect(Collectors.toList());

        List<Column> columns = crawlConfig.getColumns();

        crawlerStarter.start(crawlConfig.getName(), seeds, allowDomains,
                crawlUrlPrefixes, columns, crawlConfig.getAdvancedOpt());
//        CrawlControllerOptions options = CrawlControllerOptions.defaultOptions();
//        options.setWorkers(crawlRequest.getAdvancedOpt().getWorkers());
//        options.setDelay(crawlRequest.getAdvancedOpt().getPoliteWait());
//        options.setDepth(crawlRequest.getAdvancedOpt().getMaxDepth());
//        options.setPage(crawlRequest.getAdvancedOpt().getMaxPage());
//        options.setDir(crawlRequest.getSpiderName() + "\\");
//
//        CrawlController crawlController = crawlControllerFactory.newController(options);
//
//        PublishSubject<Map<String, ?>> crawlSubject = PublishSubject.create();
//        crawlSubject.buffer(60, TimeUnit.SECONDS, Schedulers.computation(), 20,
//                () -> Collections.synchronizedList(new LinkedList<>()), true)
//                .subscribeData(
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

    @GetMapping("/create")
    public MessageBean create(@ModelAttribute("crawlerConfig") CrawlConfig crawlConfig,
                              SessionStatus sessionStatus) throws Exception {
        MessageBean messageBean;
        String checked=CrawlConfig.checkFinished(crawlConfig);
        if (checked.equals("")) {
            crawlerPoolService.register(crawlConfig);
            messageBean = MessageBean.successBean();
            sessionStatus.setComplete();
        }else{
            messageBean=MessageBean.errorBean();
            messageBean.getMessage().put("error",checked);
        }
        return messageBean;
    }
}
