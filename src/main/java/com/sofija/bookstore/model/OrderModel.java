package com.sofija.bookstore.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private OrderStatusModel orderStatusModel;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="order")
    private List<OrderEntryModel> orderEntryModels;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    public OrderModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public OrderStatusModel getOrderStatusModel() {
        return orderStatusModel;
    }

    public void setOrderStatusModel(OrderStatusModel orderStatusModel) {
        this.orderStatusModel = orderStatusModel;
    }

    public List<OrderEntryModel> getOrderEntryModels() {
        return orderEntryModels;
    }

    public void setOrderEntryModels(List<OrderEntryModel> orderEntryModels) {
        this.orderEntryModels = orderEntryModels;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel orderModel = (OrderModel) o;
        return id == orderModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
