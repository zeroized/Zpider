package com.zeroized.spider.logic.rx;

import com.zeroized.spider.domain.CrawlConfig;
import com.zeroized.spider.domain.observable.DataEntity;
import com.zeroized.spider.domain.observable.ImageEntity;
import com.zeroized.spider.domain.observable.WarningEntity;
import com.zeroized.spider.repository.CrawlConfigRepository;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Zero on 2018/4/3.
 */
@Component
public class CrawlerObservable {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerObservable.class);
    private final PublishSubject<DataEntity> dataSubject;
    private final Observable<List<DataEntity>> observable;
    private final PublishSubject<WarningEntity> warningSubject;
    private final PublishSubject<ImageEntity> imageSubject;
    private final PublishSubject<CrawlConfig> crawlSubject;
    private Disposable dataDisposable;
    private Disposable imageDisposable;
    private Disposable crawlDisposable;
    private CrawlConfigRepository crawlConfigRepository;

    @Value("${rx.elasticsearch.time-span}")
    private int timeSpan;

    @Value("${rx.elasticsearch.buffer-size}")
    private int bufferSize;

    public CrawlerObservable() {
        dataSubject = PublishSubject.create();
        warningSubject = PublishSubject.create();
        observable = dataSubject.buffer(timeSpan, TimeUnit.SECONDS, Schedulers.computation(), 60,
                () -> Collections.synchronizedList(new LinkedList<>()), true);
        imageSubject = PublishSubject.create();
        crawlSubject =PublishSubject.create();
    }

    @PostConstruct
    public void init() {
        dataDisposable = dataSubject.subscribe(x -> writeLog(x.toString(), "data"));
        imageDisposable = imageSubject.subscribe(x -> writeLog(x.toString(), "image"));
        crawlDisposable=crawlSubject.subscribe(x -> crawlConfigRepository.save(x));
    }

    public void productData(DataEntity data) {
        dataSubject.onNext(data);
    }

    public void produceCrawl(CrawlConfig crawl){crawlSubject.onNext(crawl);}

    public void produceImage(ImageEntity image) {
        imageSubject.onNext(image);
    }

    public void produceWarning(WarningEntity warningEntity) {
        warningSubject.onNext(warningEntity);
    }

    public Disposable subscribeData(Consumer<List<DataEntity>> consumer) {
        return observable.subscribe(consumer);
    }

    public Disposable subscribeWarning(Consumer<WarningEntity> consumer) {
        return warningSubject.subscribe(consumer);
    }

    public Disposable subscribeImage(Consumer<ImageEntity> consumer) {
        return imageSubject.subscribe(consumer);
    }


    private void writeLog(String logString, String type) {
        logger.info("New " + type + " in the observable: " + logString);
    }

    @PreDestroy
    public void destroy() {
        dataDisposable.dispose();
        imageDisposable.dispose();
    }
}
