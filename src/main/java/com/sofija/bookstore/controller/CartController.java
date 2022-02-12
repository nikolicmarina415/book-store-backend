package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.CartData;
import com.sofija.bookstore.facade.CartFacade;
import com.sofija.bookstore.facade.UserFacade;
import com.sofija.bookstore.transfer.Response;
import com.sofija.bookstore.transfer.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/carts")
@CrossOrigin
public class CartController {

    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

    @Resource
    private CartFacade cartFacade;

    @Resource
    private UserFacade userFacade;

    @RequestMapping(
            value = "/total-sum",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response getCartTotalSum(@RequestBody CartData cartData) {
        try {
            boolean goldenCustomer = userFacade.isGoldenCustomer(cartData.getUserId());
            double totalSum = cartFacade.getTotalSum(cartData, goldenCustomer);
            return ResponseUtil.createResponse(totalSum, HttpStatus.OK.value(), getMessage(goldenCustomer));
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }

    private String getMessage(boolean goldenCustomer) {
        if (goldenCustomer) {
            return String.format("Golden customer - you get %s%% discount", cartFacade.getGoldenCustomerDiscount() * 100);
        }
        return null;
    }
}
