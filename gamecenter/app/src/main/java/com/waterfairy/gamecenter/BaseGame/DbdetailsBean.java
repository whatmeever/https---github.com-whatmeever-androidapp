package com.waterfairy.gamecenter.BaseGame;

/**
 * Created by shui on 2015/10/30.
 */
public class DbdetailsBean {
    private String name;
    private int id;
    private String imgUrl;
    private String path;
    private String pkg;
    private int state;
    private int donwnIngState;

    public int getDonwnIngState() {
        return donwnIngState;
    }

    public void setDonwnIngState(int donwnIngState) {
        this.donwnIngState = donwnIngState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
