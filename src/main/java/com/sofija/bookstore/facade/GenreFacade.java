package com.sofija.bookstore.facade;

import com.sofija.bookstore.data.GenreData;
import com.sofija.bookstore.model.GenreModel;
import com.sofija.bookstore.service.GenreService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreFacade {

    @Resource
    private GenreService genreService;

    public List<GenreData> getAll() {
        return genreService.getAll()
                .stream()
                .map(this::createGenreData)
                .collect(Collectors.toList());
    }

    private GenreData createGenreData(GenreModel genreModel) {
        GenreData genreData = new GenreData();
        genreData.setId(genreModel.getId());
        genreData.setName(genreModel.getName());
        return genreData;
    }
}
