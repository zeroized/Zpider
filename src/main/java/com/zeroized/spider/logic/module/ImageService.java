package com.zeroized.spider.logic.module;

import com.zeroized.spider.domain.observable.ImageEntity;
import com.zeroized.spider.logic.rx.CrawlerObservable;
import com.zeroized.spider.repo.FileRepo;
import com.zeroized.spider.util.HttpRequestUtil;
import io.reactivex.disposables.Disposable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;

/**
 * Created by Zero on 2018/4/14.
 */
@Service
public class ImageService {
    private final Disposable disposable;
    private final FileRepo fileRepo;

    @Value("${crawler.config.default.image-save-path}")
    private String imageSaveBaseDir;

    public ImageService(FileRepo fileRepo, CrawlerObservable crawlerObservable) {
        this.disposable = crawlerObservable.subscribeImage(this::save);
        this.fileRepo = fileRepo;
    }

    public void save(ImageEntity imageEntity) throws IOException {
        //TODO error handling
        List<String> urls = imageEntity.getImgUrl();
//        HttpRequestUtil.getImage(url);
        String fileNamePrefix = imageEntity.getId() + "_" + imageEntity.getColumn() + "_";
        int i = 0;
        for (String url : urls) {
            String suffix = url.substring(url.lastIndexOf("."));
            String fileName = fileNamePrefix + i + suffix;
            fileRepo.saveFile(imageSaveBaseDir + imageEntity.getSpiderName() + "\\" + fileName, HttpRequestUtil.getImage(url));
            i++;
        }
    }

    @PreDestroy
    public void destroy() {
        disposable.dispose();
    }
}
