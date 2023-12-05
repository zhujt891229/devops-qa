package com.zjt.qas.model;

public class SSQPoolItem {
    private String red;
    private String blue;

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "SSQPoolItem{" +
                "red='" + red + '\'' +
                ", blue='" + blue + '\'' +
                '}';
    }
}
