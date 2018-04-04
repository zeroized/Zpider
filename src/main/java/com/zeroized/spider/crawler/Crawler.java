package com.zeroized.spider.crawler;

import com.zeroized.spider.domain.Column;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import io.reactivex.subjects.PublishSubject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2018/3/22.
 */
public class Crawler extends WebCrawler {
    private CrawlerOptions crawlerOptions;
    private PublishSubject<Map<String, ?>> publishSubject;

    public Crawler(CrawlerOptions crawlerOptions, PublishSubject<Map<String, ?>> publishSubject) {
        this.crawlerOptions = crawlerOptions;
        this.publishSubject = publishSubject;
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String uri = url.getURL();
        for (String s : crawlerOptions.getAllowDomains()) {
            if (uri.startsWith(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        for (String prefix : crawlerOptions.getCrawlUrlPrefixes()) {
            if (url.startsWith(prefix)) {
//                System.out.println("analyze");
                analyze(page);
                return;
            }
        }
    }

    private void analyze(Page page) {
        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
        String html = htmlParseData.getHtml();
        Document doc = Jsoup.parse(html);
        Map<String, List<String>> data = new HashMap<>();
        for (Column column : crawlerOptions.getColumns()) {
            String name = column.getColumn();
            String rule = column.getRule();
            Elements eles = doc.select(rule);
            List<String> values = new ArrayList<>(eles.size());
            for (Element ele : eles) {
                values.add(ele.text());
            }
            data.put(name, values);
        }
//        System.out.println(data);
        publishSubject.onNext(data);
//        System.out.println(data.toString());
    }


}
