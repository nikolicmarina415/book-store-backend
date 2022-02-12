package com.sofija.bookstore.data;

import java.util.List;

public class CartData {

    private int userId;
    private List<CartEntryData> cartEntryDataList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartEntryData> getCartEntryDataList() {
        return cartEntryDataList;
    }

    public void setCartEntryDataList(List<CartEntryData> cartEntryDataList) {
        this.cartEntryDataList = cartEntryDataList;
    }
}
