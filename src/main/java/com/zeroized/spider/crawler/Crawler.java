package com.zeroized.spider.crawler;

import com.zeroized.spider.domain.Column;
import com.zeroized.spider.domain.WarningEntity;
import com.zeroized.spider.rx.CrawlerObservable;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by Zero on 2018/3/22.
 */
public class Crawler extends WebCrawler {
    private CrawlerOptions crawlerOptions;
    private CrawlerObservable crawlerObservable;

    Crawler(CrawlerOptions crawlerOptions, CrawlerObservable crawlerObservable) {
        this.crawlerOptions = crawlerOptions;
        this.crawlerObservable = crawlerObservable;
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
        List<String> warningColumns = new LinkedList<>();
        for (Column column : crawlerOptions.getColumns()) {
            String name = column.getColumn();
            String rule = column.getRule();
            Elements eles = doc.select(rule);
            if (eles.size() == 0) {
                warningColumns.add(name);
                continue;
            }
            List<String> values = new ArrayList<>(eles.size());
            for (Element ele : eles) {
                values.add(ele.text());
            }
            data.put(name, values);
        }
//        System.out.println(data);
        crawlerObservable.produce(data);
        if (warningColumns.size() != 0) {
            String url = page.getWebURL().getURL();
            crawlerObservable.produceWarning(new WarningEntity(url, warningColumns));
        }
//        System.out.println(data.toString());
    }


}
