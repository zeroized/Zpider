package com.zeroized.spider.repo.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Zero on 2018/4/3.
 */
@Repository
public class ElasticClient {
    private RestHighLevelClient highLevelClient;

    @Value("${elasticsearch.highRest.host:127.0.0.1}")
    private String host;

    @Value("${elasticsearch.highRest.port:9200}")
    private int port;

    @Value("${elasticsearch.default.index:food}")
    private String index;

    @Value("${elasticsearch.default.type:food}")
    private String type;

    @PostConstruct
    public void init() {
        highLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, port, "http")));

    }

    public void createIndex(String index) {
        CreateIndexRequest indexRequest = new CreateIndexRequest(index);
    }

    public String indexDoc(Map<String, ?> doc) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, type);
        indexRequest.source(doc);
        IndexResponse indexResponse = highLevelClient.index(indexRequest);
        return indexResponse.getId();
    }

    public List<String> bulkIndex(List<Map<String, ?>> docs) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (Map<String, ?> doc : docs) {
            IndexRequest indexRequest = new IndexRequest(index, type);
            indexRequest.source(doc);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = highLevelClient.bulk(bulkRequest);
        return Arrays.stream(bulkResponse.getItems())
                .map(BulkItemResponse::getId)
                .collect(Collectors.toList());
    }

    @PreDestroy
    public void close() {
        try {
            highLevelClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
