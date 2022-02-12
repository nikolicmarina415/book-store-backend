package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.BookData;
import com.sofija.bookstore.exception.BookException;
import com.sofija.bookstore.facade.BookFacade;
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
@RequestMapping("/books")
@CrossOrigin
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Resource
    private BookFacade bookFacade;

    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response createBook(@RequestBody BookData bookData) {
        try {
            BookData createdBookData = bookFacade.create(bookData);
            return ResponseUtil.createResponse(createdBookData, HttpStatus.CREATED.value(), "Book successfully created");
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response getAllBooks() {
        try {
            List<BookData> books = bookFacade.getAll();
            return ResponseUtil.createResponse(books, HttpStatus.OK.value());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response updateBook(@RequestBody BookData bookData) {
        try {
            bookFacade.update(bookData);
            return ResponseUtil.createResponse(HttpStatus.OK.value(), "Book successfully updated");
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response deleteBook(@PathVariable int id) {
        try {
            bookFacade.delete(id);
            return ResponseUtil.createResponse(HttpStatus.NO_CONTENT.value(), "Book successfully deleted");
        } catch (BookException ex) {
            return ResponseUtil.createResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }
}
