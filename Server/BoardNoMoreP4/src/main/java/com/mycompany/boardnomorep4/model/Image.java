package com.mycompany.boardnomorep4.model;


import javax.xml.bind.annotation.XmlRootElement;

//You will need to create a Java Object. Jersey uses these to contruct requests and responses.

public class Image {
    private int imgId;
    private int gid;
    private String path;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
   
}