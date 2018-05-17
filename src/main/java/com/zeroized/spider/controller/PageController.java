package com.zeroized.spider.controller;

import com.zeroized.spider.domain.crawler.CrawlConfig;
import com.zeroized.spider.business.service.DomParseService;
import com.zeroized.spider.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Zero on 2018/3/7.
 */
@Controller
@RequestMapping("/")
@SessionAttributes("crawlerConfig")
public class PageController {
    private final DomParseService domParseService;

    @Autowired
    public PageController(DomParseService domParseService) {
        this.domParseService = domParseService;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/create")
    public ModelAndView create(ModelMap modelMap) {
        if (!modelMap.containsAttribute("crawlerConfig")) {
            modelMap.addAttribute("crawlerConfig", new CrawlConfig());
        }
        return new ModelAndView("create");
    }

    @RequestMapping("/list")
    public ModelAndView list(){
        return new ModelAndView("list");
    }

    @RequestMapping("/load")
    public @ResponseBody
    String load(@RequestParam String url) throws IOException {
        String resp = HttpRequestUtil.get(url);
        int domainStartPos = url.indexOf("//") + 2;
        int domainEndPos = url.indexOf("/", domainStartPos);
        String domain;
        if (domainEndPos == -1) {
            domain = url.substring(domainStartPos);
        } else {
            domain = url.substring(domainStartPos, domainEndPos);
        }
        System.out.println("domain: " + domain);
        String html = domParseService.parseIFrameProxy(resp, domain);
//        System.out.println(html);
        return html;
    }

    @RequestMapping("/proxy")
    public @ResponseBody
    String proxy(@RequestParam String url) throws IOException {
        String resp = HttpRequestUtil.get(url);
        return resp;
    }
}
