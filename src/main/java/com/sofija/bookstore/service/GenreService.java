package com.sofija.bookstore.service;

import com.sofija.bookstore.model.GenreModel;
import com.sofija.bookstore.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GenreService {

    @Resource
    private GenreRepository genreRepository;

    public List<GenreModel> getAll() {
        return genreRepository.findAll();
    }
}
