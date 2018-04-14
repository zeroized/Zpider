package com.zeroized.spider.logic.module;

import com.zeroized.spider.domain.observable.ImageEntity;
import com.zeroized.spider.logic.rx.CrawlerObservable;
import com.zeroized.spider.repo.FileRepo;
import io.reactivex.disposables.Disposable;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

/**
 * Created by Zero on 2018/4/14.
 */
@Service
public class ImageService {
    private final Disposable disposable;
    private final FileRepo fileRepo;

    public ImageService(FileRepo fileRepo, CrawlerObservable crawlerObservable) {
        this.disposable = crawlerObservable.subscribeImage(this::save);
        this.fileRepo = fileRepo;
    }

    public void save(ImageEntity imageEntity) {

    }

    @PreDestroy
    public void destroy() {
        disposable.dispose();
    }
}
