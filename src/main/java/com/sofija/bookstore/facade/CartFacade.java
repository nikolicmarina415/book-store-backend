package com.sofija.bookstore.facade;

import com.sofija.bookstore.data.CartData;
import com.sofija.bookstore.service.CartService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CartFacade {

    @Resource
    private CartService cartService;

    public double getTotalSum(CartData cartData, boolean goldenCustomer) {
        return cartService.getTotalSum(cartData, goldenCustomer);
    }

    public double getGoldenCustomerDiscount() {
        return cartService.getGoldenCustomerDiscount();
    }
}
