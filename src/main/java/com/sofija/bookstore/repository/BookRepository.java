package com.sofija.bookstore.repository;

import com.sofija.bookstore.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE BookModel SET title = :title," +
            " image_path = :image_path," +
            " price = :price," +
            " author_id = :author_id," +
            " genre_id = :genre_id" +
            " WHERE id = :id")
    void update(@Param("title") String title,
                @Param("image_path") String image_path,
                @Param("price") double price,
                @Param("author_id") int author_id,
                @Param("genre_id") int genre_id,
                @Param("id") int id);
}
