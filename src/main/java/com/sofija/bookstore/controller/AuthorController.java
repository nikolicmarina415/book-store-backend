package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.AuthorData;
import com.sofija.bookstore.facade.AuthorFacade;
import com.sofija.bookstore.transfer.Response;
import com.sofija.bookstore.transfer.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/authors")
@CrossOrigin
public class AuthorController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorController.class);

    @Resource
    private AuthorFacade authorFacade;

    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response getAllAuthors() {
        try {
            List<AuthorData> authors = authorFacade.getAll();
            return ResponseUtil.createResponse(authors, HttpStatus.OK.value());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }
}
