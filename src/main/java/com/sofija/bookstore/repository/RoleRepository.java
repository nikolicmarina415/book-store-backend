package com.sofija.bookstore.repository;

import com.sofija.bookstore.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Integer> {

    @Query("SELECT r FROM RoleModel r WHERE r.name = ?1")
    RoleModel findByName(String name);
}
