package com.barry.basic.entity;

/**
 * Created by bynn on 2018/1/17.
 */
public class City {
    private String name;
    private String temp;
    private String pm25;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getPm25() {
        return pm25;
    }
    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }
    @Override
    public String toString() {
        return "City [name=" + name + ", temp=" + temp + ", pm25=" + pm25 + "]";
    }


}

