package com.zeroized.spider.business.service;

import com.zeroized.spider.business.rx.CrawlerObservable;
import com.zeroized.spider.domain.observable.DataEntity;
import com.zeroized.spider.repo.elastic.ElasticClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2018/4/3.
 */
@Service
public class ElasticService {
    private static final Logger logger= LoggerFactory.getLogger(ElasticService.class);

    private final ElasticClient elasticClient;

//    private final Disposable disposable;

    @Autowired
    public ElasticService(ElasticClient elasticClient, CrawlerObservable crawlerObservable) {
        this.elasticClient = elasticClient;
//        disposable = crawlerObservable.subscribeData(this::generateBulkIndex);
    }

    public void createIndex(String index){
        try {
            elasticClient.createIndex(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIndex(String index){
        return elasticClient.indexExists(index);
    }

    public void generateBulkIndex(List<DataEntity> docs) {
        if (docs.size() == 0) {
            return;
        }
        try {
            List<String> bulkResult=elasticClient.bulkIndex(docs);
            logger.info("{} docs indexed: {}",bulkResult.size(),bulkResult.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> search(String uuid) throws IOException {
        return elasticClient.matchQuery(uuid);
    }

//    @PreDestroy
//    public void destroy() {
//        disposable.dispose();
//    }
}
