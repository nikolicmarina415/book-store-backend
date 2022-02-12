package com.sofija.bookstore.facade;

import com.sofija.bookstore.data.AuthorData;
import com.sofija.bookstore.data.BookData;
import com.sofija.bookstore.data.GenreData;
import com.sofija.bookstore.exception.BookException;
import com.sofija.bookstore.model.AuthorModel;
import com.sofija.bookstore.model.BookModel;
import com.sofija.bookstore.model.GenreModel;
import com.sofija.bookstore.service.BookService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookFacade {

    @Resource
    private BookService bookService;

    public List<BookData> getAll() {
        return bookService.getAll()
                .stream()
                .map(this::createBookData)
                .collect(Collectors.toList());
    }

    public void delete(int id) throws BookException {
        bookService.delete(id);
    }

    public BookData create(BookData bookData) {
        BookModel bookModel = createBookModel(bookData);
        BookModel createdBookModel = bookService.create(bookModel);
        return createBookData(createdBookModel);
    }

    public void update(BookData bookData) {
        BookModel bookModel = createBookModel(bookData);
        bookService.update(bookModel);
    }

    public BookData createBookData(BookModel bookModel) {
        BookData bookData = new BookData();
        bookData.setId(bookModel.getId());
        bookData.setGenreData(createGenreData(bookModel.getGenreModel()));
        bookData.setAuthorData(createAuthorData(bookModel.getAuthorModel()));
        bookData.setPrice(bookModel.getPrice());
        bookData.setTitle(bookModel.getTitle());
        bookData.setImagePath(bookModel.getImagePath());
        return bookData;
    }

    public BookModel createBookModel(BookData bookData) {
        BookModel bookModel = new BookModel();
        bookModel.setId(bookData.getId());
        bookModel.setAuthorModel(createAuthorModel(bookData.getAuthorData()));
        bookModel.setGenreModel(createGenreModel(bookData.getGenreData()));
        bookModel.setPrice(bookData.getPrice());
        bookModel.setTitle(bookData.getTitle());
        bookModel.setImagePath(bookData.getImagePath());
        return bookModel;
    }

    private GenreData createGenreData(GenreModel genreModel) {
        GenreData genreData = new GenreData();
        genreData.setId(genreModel.getId());
        genreData.setName(genreModel.getName());
        return genreData;
    }

    private AuthorData createAuthorData(AuthorModel authorModel) {
        AuthorData authorData = new AuthorData();
        authorData.setId(authorModel.getId());
        authorData.setFirstName(authorModel.getFirstName());
        authorData.setLastName(authorModel.getLastName());
        authorData.setDateOfBirth(authorModel.getDateOfBirth());
        return authorData;
    }

    private AuthorModel createAuthorModel(AuthorData authorData) {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(authorData.getId());
        return authorModel;
    }

    private GenreModel createGenreModel(GenreData genreData) {
        GenreModel genreModel = new GenreModel();
        genreModel.setId(genreData.getId());
        return genreModel;
    }
}
