package com.zeroized.spider.rx;

import com.zeroized.spider.domain.WarningEntity;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Zero on 2018/4/3.
 */
@Component
public class CrawlerObservable {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerObservable.class);
    private final PublishSubject<Map<String, List<String>>> subject;
    private final Observable<List<Map<String, List<String>>>> observable;
    private final PublishSubject<WarningEntity> warningSubject;

    @Value("${rx.elasticsearch.time-span}")
    private int timeSpan;

    @Value("${rx.elasticsearch.buffer-size}")
    private int bufferSize;

    public CrawlerObservable() {
        subject = PublishSubject.create();
        warningSubject = PublishSubject.create();
        subject.subscribe(x -> writeLog(x.toString()));
        observable = subject.buffer(timeSpan, TimeUnit.SECONDS, Schedulers.computation(), bufferSize,
                () -> Collections.synchronizedList(new LinkedList<>()), true);

    }

    public void produce(Map<String, List<String>> data) {
        subject.onNext(data);
        logger.info(data.toString());
    }

    public void produceWarning(WarningEntity warningEntity) {
        warningSubject.onNext(warningEntity);
    }

    public Disposable subscribe(Consumer<List<Map<String, List<String>>>> consumer) {
        return observable.subscribe(consumer);
    }

    public Disposable subscribeWarning(Consumer<WarningEntity> consumer) {
        return warningSubject.subscribe(consumer);
    }


    private void writeLog(String logString) {
    }
}
