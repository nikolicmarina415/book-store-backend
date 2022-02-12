package com.sofija.bookstore.repository;

import com.sofija.bookstore.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

    @Query("SELECT o FROM OrderModel o WHERE o.userModel.id = ?1")
    List<OrderModel> findAllByUserId(int userId);

    @Transactional
    @Modifying
    @Query("UPDATE OrderModel SET status_id = :status_id WHERE id = :id")
    void completeOrder(@Param("id") int id, @Param("status_id") int status_id);
}
