package com.zeroized.spider.logic.module;

import com.zeroized.spider.repo.elastic.ElasticClient;
import com.zeroized.spider.rx.CrawlerObservable;
import io.reactivex.disposables.Disposable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2018/4/3.
 */
@Service
public class ElasticService {
    private final ElasticClient elasticClient;
    private final CrawlerObservable crawlerObservable;
    private final Disposable disposable;

    @Autowired
    public ElasticService(ElasticClient elasticClient, CrawlerObservable crawlerObservable) {
        this.elasticClient = elasticClient;
        this.crawlerObservable = crawlerObservable;
        disposable = this.crawlerObservable.subscribe(this::generateBulkIndex);
    }

    public void generateBulkIndex(List<Map<String, List<String>>> docs) {
        if (docs.size() == 0) {
            return;
        }
        try {
            List<String> ids = elasticClient.bulkIndex(new LinkedList<>(docs));
            System.out.println(ids);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        disposable.dispose();
    }
}
