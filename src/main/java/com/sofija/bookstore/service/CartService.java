package com.sofija.bookstore.service;

import com.sofija.bookstore.data.CartData;
import com.sofija.bookstore.data.CartEntryData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Value("${golden.customer.discount}")
    private double goldenCustomerDiscount;

    public double getTotalSum(CartData cartData, boolean goldenCustomer) {
        if (goldenCustomer) {
            return totalSumWithDiscount(cartData);
        }
        return totalSumWithoutDiscount(cartData);
    }

    public double getGoldenCustomerDiscount() {
        return this.goldenCustomerDiscount;
    }

    private double totalSumWithDiscount(CartData cartData) {
        return cartData.getCartEntryDataList()
                .stream()
                .mapToDouble(this::getDiscountCartEntryData)
                .sum();
    }

    private double totalSumWithoutDiscount(CartData cartData) {
        return cartData.getCartEntryDataList()
                .stream()
                .mapToDouble(cartEntryData -> cartEntryData.getBookData().getPrice() * cartEntryData.getQuantity())
                .sum();
    }

    private double getDiscountCartEntryData(CartEntryData cartEntryData) {
        double totalPrice = cartEntryData.getBookData().getPrice() * cartEntryData.getQuantity();
        return totalPrice - (totalPrice * goldenCustomerDiscount);
    }
}
