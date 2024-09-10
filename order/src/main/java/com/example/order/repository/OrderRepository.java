package com.example.order.repository;

import com.example.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query(value = "SELECT * FROM Orders WHERE id = ?1", nativeQuery = true)
    Orders getOrderById(Integer orderID);
}
