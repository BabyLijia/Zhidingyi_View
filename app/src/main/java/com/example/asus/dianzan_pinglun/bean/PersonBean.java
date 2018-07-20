package com.example.asus.dianzan_pinglun.bean;

public class PersonBean {
    private int img;
    private String name;
    private String message;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonBean() {

    }

    public PersonBean(int img, String name, String message) {

        this.img = img;
        this.name = name;
        this.message = message;
    }
}
