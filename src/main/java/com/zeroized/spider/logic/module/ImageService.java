package com.zeroized.spider.logic.module;

import com.zeroized.spider.domain.observable.ImageEntity;
import com.zeroized.spider.logic.rx.CrawlerObservable;
import com.zeroized.spider.util.HttpRequestUtil;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Zero on 2018/4/14.
 */
@Service
public class ImageService {
    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);
    private final Disposable disposable;

    @Value("${crawler.config.default.image-save-path}")
    private String imageSaveBaseDir;

    public ImageService(CrawlerObservable crawlerObservable) {
        this.disposable = crawlerObservable.subscribeImage(this::save);
    }

    public void save(ImageEntity imageEntity) throws IOException {
        //TODO error handling
        String dir = imageSaveBaseDir + imageEntity.getSpiderName() + "\\";
        List<String> urls = imageEntity.getImgUrl();
//        HttpRequestUtil.getImage(url);
        String fileNamePrefix = imageEntity.getId() + "_" + imageEntity.getColumn() + "_";
        int i = 0;
        for (String url : urls) {
            String suffix = url.substring(url.lastIndexOf("."));
            String fileName = dir + fileNamePrefix + i + suffix;
            File image = HttpRequestUtil.getImage(url, fileName);
            logger.info("New image saved: " + image.getAbsolutePath());
            i++;
        }
    }

    @PreDestroy
    public void destroy() {
        disposable.dispose();
    }
}
