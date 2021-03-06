package com.zeroized.spider.repo.elastic;

import com.zeroized.spider.domain.observable.DataEntity;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ElasticClient.class);

    @Value("${elasticsearch.highRest.host:127.0.0.1}")
    private String host;

    @Value("${elasticsearch.highRest.port:9200}")
    private int port;

    @PostConstruct
    public void init() {
        highLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, port, "http")));
//        try {
//            if (highLevelClient.ping()) {
        logger.info("Elasticsearch server connected on " + host + ":" + port);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void createIndex(String index) throws IOException {
        CreateIndexRequest indexRequest = new CreateIndexRequest(index);
        highLevelClient.indices().create(indexRequest);
//        highLevelClient.indices()
    }

    public boolean indexExists(String index){
//        try {
//            indexDoc(new HashMap<>(),index,"1");
//            delete(index,"1");
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
        return true;
    }

    public String indexDoc(Map<String, ?> doc, String index,String id) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, "doc",id);
        indexRequest.source(doc);
        IndexResponse indexResponse = highLevelClient.index(indexRequest);
        return indexResponse.getId();
    }

    public List<String> bulkIndex(List<DataEntity> docs) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (DataEntity doc : docs) {
            IndexRequest indexRequest = new IndexRequest(doc.getIndexId(), "doc", doc.getId());
            indexRequest.source(doc.getData());
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = highLevelClient.bulk(bulkRequest);
        return Arrays.stream(bulkResponse.getItems())
                .map(BulkItemResponse::getId)
                .collect(Collectors.toList());
    }

    public List<String> bulkIndex(List<Map<String, ?>> docs,String index) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (Map<String, ?> doc : docs) {
            IndexRequest indexRequest = new IndexRequest(index, "doc");
            indexRequest.source(doc);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = highLevelClient.bulk(bulkRequest);
        return Arrays.stream(bulkResponse.getItems())
                .map(BulkItemResponse::getId)
                .collect(Collectors.toList());
    }

    public void delete(String index,String id) throws IOException {
        DeleteRequest deleteRequest=new DeleteRequest(index,"doc",id);
        highLevelClient.delete(deleteRequest);
    }

    public List<Map<String,Object>> matchQuery(String index) throws IOException {
        SearchRequest searchRequest=new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse=highLevelClient.search(searchRequest);
        return Arrays.stream(searchResponse.getHits().getHits())
                .map(SearchHit::getSourceAsMap).collect(Collectors.toList());
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
