package com.zeroized.spider.repo.mongo;

import com.zeroized.spider.domain.repo.CrawlerInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zero on 2018/5/14.
 */
@Repository
public interface CrawlerInfoRepo extends MongoRepository<CrawlerInfoEntity,String> {

    List<CrawlerInfoEntity> findByStatus(int status);

    List<CrawlerInfoEntity> findByStatusIsNot(int status);

    List<CrawlerInfoEntity> findByStatusOrStatus(int status, int status2);
}
