package com.zeroized.spider.logic.module;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * Created by Zero on 2018/3/15.
 */
@Service
public class DomParseService {
    public String parseIFrameProxy(String html, String domain) {
        Document doc = Jsoup.parse(html);
        Elements headCss = doc.head().select("link");
        for (Element css : headCss) {
            String href = css.attr("href");
            String proxyHref = "http://localhost:8080/proxy?url=";
            proxyHref = getProxy(domain, href, proxyHref);
            css.attr("href", proxyHref);
            css.attr("data-href", href);
        }
        Elements headJs = doc.select("script");
        for (Element js : headJs) {
            String src = js.attr("src");
            if (src != null && !src.equals("")) {
                String proxySrc = "http://localhost:8080/proxy?url=";
                proxySrc = getProxy(domain, src, proxySrc);
                js.attr("src", proxySrc);
                js.attr("data-src", src);
            }
        }
        Elements as = doc.body().select("a");
        for (Element a : as) {
            String href = a.attr("href");
            String proxyHref = "http://localhost:8080/load?url=";
            proxyHref = getProxy(domain, href, proxyHref);
            a.attr("href", "#");
            a.attr("data-href", proxyHref);
            a.removeAttr("target");
        }
        doc.head().append("<script src='/js/frameFun.js'></script>");
//        doc.body().attr("onmousedown","showDom(event)");
        return doc.html();
    }

    private String getProxy(String domain, String src, String proxySrc) {
        if (src.startsWith("//")) {
            proxySrc = proxySrc + "http:" + src;
        } else if (src.startsWith("/")) {
            proxySrc = proxySrc + "http://" + domain + src;
        } else {
            proxySrc = proxySrc + src;
        }
        return proxySrc;
    }
}
