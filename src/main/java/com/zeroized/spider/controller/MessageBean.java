package com.zeroized.spider.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zero on 2018/5/7.
 */
public class MessageBean {
    public static final int ERROR = 0;
    public static final int SUCCESS = 1;

    private int status;

    private Map<String, Object> message = new HashMap<>();

    public MessageBean(int status) {
        this.status = status;
    }

    public static MessageBean errorBean() {
        return new MessageBean(ERROR);
    }

    public static MessageBean successBean() {
        return new MessageBean(SUCCESS);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, Object> getMessage() {
        return message;
    }

    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }
}
