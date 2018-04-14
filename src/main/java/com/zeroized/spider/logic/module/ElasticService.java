package com.zeroized.spider.logic.module;

import com.zeroized.spider.domain.observable.DataEntity;
import com.zeroized.spider.logic.rx.CrawlerObservable;
import com.zeroized.spider.repo.elastic.ElasticClient;
import io.reactivex.disposables.Disposable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;

/**
 * Created by Zero on 2018/4/3.
 */
@Service
public class ElasticService {
    private final ElasticClient elasticClient;
    private final Disposable disposable;

    @Autowired
    public ElasticService(ElasticClient elasticClient, CrawlerObservable crawlerObservable) {
        this.elasticClient = elasticClient;
        disposable = crawlerObservable.subscribeData(this::generateBulkIndex);
    }

    public void generateBulkIndex(List<DataEntity> docs) {
        if (docs.size() == 0) {
            return;
        }
        try {
            elasticClient.bulkIndex(docs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        disposable.dispose();
    }
}
