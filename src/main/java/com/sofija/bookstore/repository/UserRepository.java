package com.sofija.bookstore.repository;

import com.sofija.bookstore.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    @Query("SELECT u FROM UserModel u WHERE u.email = ?1")
    UserModel findByEmail(String email);

    @Query("SELECT u FROM UserModel u WHERE u.email = ?1 AND u.password = ?2")
    UserModel findByEmailAndPassword(String email, String password);
}
