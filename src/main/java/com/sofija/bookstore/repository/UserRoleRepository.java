package com.sofija.bookstore.repository;

import com.sofija.bookstore.model.UserRoleModel;
import com.sofija.bookstore.model.UserRoleModelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleModel, UserRoleModelId> {
}
