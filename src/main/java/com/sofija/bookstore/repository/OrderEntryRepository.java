package com.sofija.bookstore.repository;

import com.sofija.bookstore.model.OrderEntryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntryRepository extends JpaRepository<OrderEntryModel, Integer> {
}
