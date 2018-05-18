package com.zeroized.spider.repo.mongo;

import com.zeroized.spider.domain.observable.DataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zero on 2018/5/18.
 */
@Repository
public interface DataRepo extends MongoRepository<DataEntity,String> {
    List<DataEntity> findByIndexId(String indexId);

    List<DataEntity> findByType(String type);
}
