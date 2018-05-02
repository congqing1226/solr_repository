package com.itcast.jd.po;

/**
 * @author congzi
 * @Description: 商品Entity
 * @create 2018-05-02
 * @Version 1.0
 */
public class Product {

    private String pid;
    private String name;
    private String price;
    private String picture;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
