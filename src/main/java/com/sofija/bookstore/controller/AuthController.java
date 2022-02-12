package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.UserData;
import com.sofija.bookstore.exception.UserException;
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
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Resource
    private UserFacade userFacade;

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response register(@RequestBody UserData userData) {
        try {
            UserData registeredUserData = userFacade.register(userData);
            return ResponseUtil.createResponse(registeredUserData, HttpStatus.CREATED.value(), "Registration successful");
        } catch (UserException ex) {
            return ResponseUtil.createResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response login(@RequestBody UserData userData) {
        try {
            UserData loggedInUserData = userFacade.login(userData);
            return ResponseUtil.createResponse(loggedInUserData, HttpStatus.OK.value(), "Login successful");
        } catch (UserException ex) {
            return ResponseUtil.createResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }

    @RequestMapping(
            value = "/admin",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response isAdmin(@RequestBody UserData userData) {
        try {
            boolean isAdmin = userFacade.isAdmin(userData.getId());
            int statusCode = isAdmin ? HttpStatus.OK.value() : HttpStatus.UNAUTHORIZED.value();
            return ResponseUtil.createResponse(statusCode);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }
}
