package com.sofija.bookstore.service;

import com.sofija.bookstore.exception.BookException;
import com.sofija.bookstore.model.AuthorModel;
import com.sofija.bookstore.model.BookModel;
import com.sofija.bookstore.model.GenreModel;
import com.sofija.bookstore.repository.AuthorRepository;
import com.sofija.bookstore.repository.BookRepository;
import com.sofija.bookstore.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private GenreRepository genreRepository;

    @Resource
    private AuthorRepository authorRepository;

    public List<BookModel> getAll() {
        return bookRepository.findAll();
    }

    public BookModel getById(int id) {
        return bookRepository.findById(id)
                .orElse(null);
    }

    public void delete(int id) throws BookException {
        if (getById(id) == null) {
            throw new BookException("Not found");
        }
        bookRepository.deleteById(id);
    }

    public BookModel create(BookModel bookModel) {
        GenreModel genreModel = genreRepository.getById(bookModel.getGenreModel().getId());
        AuthorModel authorModel = authorRepository.getById(bookModel.getAuthorModel().getId());
        bookModel.setGenreModel(genreModel);
        bookModel.setAuthorModel(authorModel);
        return bookRepository.save(bookModel);
    }

    public void update(BookModel bookModel) {
        bookRepository.update(
                bookModel.getTitle(),
                bookModel.getImagePath(),
                bookModel.getPrice(),
                bookModel.getAuthorModel().getId(),
                bookModel.getGenreModel().getId(),
                bookModel.getId()
        );
    }
}
