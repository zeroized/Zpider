package com.zeroized.spider.business.service;

import com.zeroized.spider.business.rx.CrawlerObservable;
import com.zeroized.spider.domain.observable.DataEntity;
import com.zeroized.spider.repo.mongo.DataRepo;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zero on 2018/5/18.
 */
@Service
public class MongoService {
    private static final Logger logger=LoggerFactory.getLogger(MongoService.class);

    private final DataRepo dataRepo;

    private final Disposable disposable;

    public MongoService(DataRepo dataRepo,CrawlerObservable crawlerObservable) {
        this.dataRepo = dataRepo;
        disposable=crawlerObservable.subscribeData(this::saveAll);
    }

    public void save(DataEntity dataEntity){
        dataRepo.save(dataEntity);
    }

    public void saveAll(List<DataEntity> dataEntities){
        dataRepo.saveAll(dataEntities);
    }

    public List<DataEntity> findByCrawlerId(String id){
        return dataRepo.findByIndexId(id);
    }

    public List<DataEntity> findByCrawlerName(String name){
        return dataRepo.findByType(name);
    }
}
