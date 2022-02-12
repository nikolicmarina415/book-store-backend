package com.sofija.bookstore.facade;

import com.sofija.bookstore.data.AuthorData;
import com.sofija.bookstore.model.AuthorModel;
import com.sofija.bookstore.service.AuthorService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorFacade {

    @Resource
    private AuthorService authorService;

    public List<AuthorData> getAll() {
        return authorService.getAll()
                .stream()
                .map(this::createAuthorData)
                .collect(Collectors.toList());
    }

    private AuthorData createAuthorData(AuthorModel authorModel) {
        AuthorData authorData = new AuthorData();
        authorData.setId(authorModel.getId());
        authorData.setFirstName(authorModel.getFirstName());
        authorData.setLastName(authorModel.getLastName());
        authorData.setDateOfBirth(authorModel.getDateOfBirth());
        return authorData;
    }
}
