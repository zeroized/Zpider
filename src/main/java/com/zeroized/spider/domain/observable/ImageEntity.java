package com.zeroized.spider.domain.observable;

import java.util.List;

/**
 * Created by Zero on 2018/4/14.
 */
public class ImageEntity {
    private String id;
    private String spiderName;
    private List<String> imgUrl;
    private String column;

    public ImageEntity() {
    }

    public ImageEntity(String id, String spiderName, List<String> imgUrl, String column) {
        this.id = id;
        this.spiderName = spiderName;
        this.imgUrl = imgUrl;
        this.column = column;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "ImageEntity{" +
                "id='" + id + '\'' +
                ", imgUrl=" + imgUrl +
                ", column='" + column + '\'' +
                '}';
    }

    public String getSpiderName() {
        return spiderName;
    }

    public void setSpiderName(String spiderName) {
        this.spiderName = spiderName;
    }
}
