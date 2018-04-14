package com.zeroized.spider.repo;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Zero on 2018/4/14.
 */
@Repository
public class FileRepo {
    public void saveFile(String path, InputStream image) throws IOException {
        FileUtils.copyToFile(image, new File(path));
    }
}
