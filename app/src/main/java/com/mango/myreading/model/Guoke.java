package com.mango.myreading.model;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class Guoke {

    private String title;
    private String imageUrl;
    private String textUrl;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Guoke() {
    }

    public Guoke(String title,String imageUrl,String textUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.textUrl = textUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getTextUrl() {
        return textUrl;
    }

    public void setTextUrl(String textUrl) {
        this.textUrl = textUrl;
    }
}
