package com.zeroized.spider.controller;

import com.zeroized.spider.business.service.CrawlerPoolService;
import com.zeroized.spider.business.service.ElasticService;
import com.zeroized.spider.business.service.MongoService;
import com.zeroized.spider.domain.CrawlerStatusInfo;
import com.zeroized.spider.domain.crawler.CrawlConfig;
import com.zeroized.spider.domain.observable.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zero on 2018/5/9.
 */
@RestController
@RequestMapping("/monitor")
@SessionAttributes("crawlerConfig")
public class MonitorController {

    private final CrawlerPoolService crawlerPoolService;

    private final ElasticService elasticService;

    private final MongoService mongoService;

    @Autowired
    public MonitorController(CrawlerPoolService crawlerPoolService, ElasticService elasticService, MongoService mongoService) {
        this.crawlerPoolService = crawlerPoolService;
        this.elasticService = elasticService;
        this.mongoService = mongoService;
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

    @RequestMapping("/show/name")
    public MessageBean showName(){
        List<String> names=crawlerPoolService.getCrawlerNameWithResult();
        MessageBean messageBean=MessageBean.successBean();
        messageBean.getMessage().put("data",names);
        return messageBean;
    }

    @RequestMapping("/opt/start")
    public MessageBean start(@RequestParam String uuid){
        MessageBean messageBean;
        int statusCode=crawlerPoolService.startCrawler(uuid);
        if (statusCode==CrawlerPoolService.SUCCESS){
            messageBean= MessageBean.successBean();
        }else{
            messageBean= MessageBean.errorBean();
        }
        return messageBean;
    }

    @RequestMapping("/opt/revise")
    public ModelAndView revise(@RequestParam String uuid, ModelMap modelMap){
        if (modelMap.containsAttribute("crawlerConfig")){
            modelMap.remove("crawlerConfig");
        }
        modelMap.put("crawlerConfig",crawlerPoolService.getConfig(uuid));
        return new ModelAndView("redirect:/create");
    }

    @RequestMapping("/opt/stop")
    public MessageBean stop(@RequestParam String uuid){
        MessageBean messageBean;
        int statusCode=crawlerPoolService.stopCrawler(uuid);
        if (statusCode==CrawlerPoolService.SUCCESS){
            messageBean= MessageBean.successBean();
        }else{
            messageBean= MessageBean.errorBean();
        }
        return messageBean;
    }

    @RequestMapping("/opt/restart")
    public MessageBean restart(@RequestParam String uuid){
        MessageBean messageBean;
        int statusCode=crawlerPoolService.restartCrawler(uuid);
        if (statusCode==CrawlerPoolService.SUCCESS){
            messageBean= MessageBean.successBean();
        }else{
            messageBean= MessageBean.errorBean();
        }
        return messageBean;
    }

    @RequestMapping("/opt/show")
    public MessageBean result(@RequestParam String uuid){
        MessageBean messageBean;
        List<DataEntity> result=mongoService.findByCrawlerId(uuid);
        messageBean=MessageBean.successBean();
        messageBean.getMessage().put("data",result);
        return messageBean;
    }
}
