package com.gifary.commontemplate.models;

/**
 * Created by gifary on 6/16/18.
 */

public class Customer {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String username;
    private Boolean lat;
    private Boolean lng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getLat() {
        return lat;
    }

    public void setLat(Boolean lat) {
        this.lat = lat;
    }

    public Boolean getLng() {
        return lng;
    }

    public void setLng(Boolean lng) {
        this.lng = lng;
    }
}
