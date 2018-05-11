package com.zeroized.spider.repository;

import com.zeroized.spider.domain.CrawlConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrawlConfigRepository extends MongoRepository<CrawlConfig,String> {
   Optional<CrawlConfig> findByName(String name);
}
