package com.sofija.bookstore.repository;

import com.sofija.bookstore.model.OrderStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusModel, Integer> {

    @Query("SELECT os FROM OrderStatusModel os WHERE os.name = ?1")
    OrderStatusModel findByName(String name);
}
