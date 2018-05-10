package com.zeroized.spider.controller;

import com.zeroized.spider.domain.CrawlConfig;
import com.zeroized.spider.domain.CrawlerStatusInfo;
import com.zeroized.spider.logic.module.CrawlerPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zero on 2018/5/9.
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    private final CrawlerPoolService crawlerPoolService;

    @Autowired
    public MonitorController(CrawlerPoolService crawlerPoolService) {
        this.crawlerPoolService = crawlerPoolService;
    }

    @RequestMapping("/all")
    public MessageBean allCrawlers() {
        List<CrawlerStatusInfo> allInfo = crawlerPoolService.getAllCrawler();
        MessageBean messageBean=MessageBean.successBean();
        messageBean.getMessage().put("data",allInfo!=null?allInfo:new ArrayList<>());
        return messageBean;
    }

    @RequestMapping("/show/config")
    public MessageBean showConfig(@RequestParam String uuid) {
        CrawlConfig crawlConfig= crawlerPoolService.getConfig(uuid);
        MessageBean messageBean=MessageBean.successBean();
        messageBean.getMessage().put("data",crawlConfig);
        return messageBean;
    }
}
