package com.sofija.bookstore.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genres")
public class GenreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public GenreModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreModel genreModel = (GenreModel) o;
        return id == genreModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
