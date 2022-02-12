package com.sofija.bookstore.service;

import com.sofija.bookstore.model.AuthorModel;
import com.sofija.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthorService {

    @Resource
    private AuthorRepository authorRepository;

    public List<AuthorModel> getAll() {
        return authorRepository.findAll();
    }
}
