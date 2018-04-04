package com.zeroized.spider.repo;

import com.zeroized.spider.domain.Data;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Zero on 2018/3/23.
 */
public interface MongoRepo extends MongoRepository<Data, Long> {
}
