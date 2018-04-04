package com.zeroized.spider.repo.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2018/4/3.
 */
@Service
public class ElasticRepo {
    private final ElasticClient elasticClient;

    @Autowired
    public ElasticRepo(ElasticClient elasticClient) {
        this.elasticClient = elasticClient;
    }

    public void generateBulkIndex(List<Map<String, ?>> docs) {
        if (docs.size() == 0) {
            return;
        }
        try {
            List<String> ids = elasticClient.bulkIndex(docs);
            System.out.println(ids);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
