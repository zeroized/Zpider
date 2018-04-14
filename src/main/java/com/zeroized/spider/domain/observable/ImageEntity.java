package com.zeroized.spider.domain.observable;

/**
 * Created by Zero on 2018/4/14.
 */
public class ImageEntity {
    private String id;
    private String imgUrl;

    public ImageEntity() {
    }

    public ImageEntity(String id, String imgUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "ImageEntity{" +
                "id='" + id + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
