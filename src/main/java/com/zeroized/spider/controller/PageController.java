package com.zeroized.spider.controller;

import com.zeroized.spider.parse.IframeParseDom;
import com.zeroized.spider.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Zero on 2018/3/7.
 */
@Controller
@RequestMapping("/")
public class PageController {
    private final IframeParseDom iframeParseDom;

    @Autowired
    public PageController(IframeParseDom iframeParseDom) {
        this.iframeParseDom = iframeParseDom;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("select");
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
        System.out.println("domain" + domain);
        String html = iframeParseDom.proxySub(resp, domain);
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
