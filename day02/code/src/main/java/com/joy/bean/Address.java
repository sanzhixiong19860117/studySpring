package com.joy.bean;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/26 20:55
 */
public class Address {
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    private String town;

    @Override
    public String toString() {
        return "Address{" +
                "town='" + town + '\'' +
                '}';
    }
}
