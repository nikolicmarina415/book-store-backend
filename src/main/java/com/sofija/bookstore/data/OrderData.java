package com.sofija.bookstore.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

public class OrderData {

    private int id;
    private UserData userData;
    private OrderStatusData orderStatusData;
    @JsonManagedReference
    private List<OrderEntryData> orderEntryDataList;
    private String address;
    private String city;
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public OrderStatusData getOrderStatusData() {
        return orderStatusData;
    }

    public void setOrderStatusData(OrderStatusData orderStatusData) {
        this.orderStatusData = orderStatusData;
    }

    public List<OrderEntryData> getOrderEntryDataList() {
        return orderEntryDataList;
    }

    public void setOrderEntryDataList(List<OrderEntryData> orderEntryDataList) {
        this.orderEntryDataList = orderEntryDataList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
