package com.zeroized.spider.util;

import java.util.UUID;

/**
 * Created by Zero on 2018/4/14.
 */
public class IdGenerator {
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
