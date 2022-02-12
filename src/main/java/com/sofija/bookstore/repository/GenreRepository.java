package com.sofija.bookstore.repository;

import com.sofija.bookstore.model.GenreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreModel, Integer> {
}
