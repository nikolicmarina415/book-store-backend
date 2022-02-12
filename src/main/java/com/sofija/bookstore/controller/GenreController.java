package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.GenreData;
import com.sofija.bookstore.facade.GenreFacade;
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
@RequestMapping("/genres")
@CrossOrigin
public class GenreController {

    private static final Logger LOG = LoggerFactory.getLogger(GenreController.class);

    @Resource
    private GenreFacade genreFacade;

    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response getAllGenres() {
        try {
            List<GenreData> genres = genreFacade.getAll();
            return ResponseUtil.createResponse(genres, HttpStatus.OK.value());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }
}
