package com.app.model;


import javax.xml.bind.annotation.XmlRootElement;

//You will need to create a Java Object. Jersey uses these to contruct requests and responses.

public class BoardGame {
    private int gid;
    private String name;
    private int price;
    private int minP;
    private int maxP;
    private int play_time;
    private int age;
    private String description;

    /**
     * @return the gid
     */
    public int getGid() {
        return gid;
    }

    /**
     * @param gid the gid to set
     */
    public void setGid(int gid) {
        this.gid = gid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the minP
     */
    public int getMinP() {
        return minP;
    }

    /**
     * @param minP the minP to set
     */
    public void setMinP(int minP) {
        this.minP = minP;
    }

    /**
     * @return the maxP
     */
    public int getMaxP() {
        return maxP;
    }

    /**
     * @param maxP the maxP to set
     */
    public void setMaxP(int maxP) {
        this.maxP = maxP;
    }

    /**
     * @return the play_time
     */
    public int getPlay_time() {
        return play_time;
    }

    /**
     * @param play_time the play_time to set
     */
    public void setPlay_time(int play_time) {
        this.play_time = play_time;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

   
}