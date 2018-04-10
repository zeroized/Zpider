package com.zeroized.spider.controller;

import com.zeroized.spider.logic.module.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Zero on 2018/4/8.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private final ElasticService elasticService;

    @Autowired
    public TestController(ElasticService elasticService) {
        this.elasticService = elasticService;
    }

}
