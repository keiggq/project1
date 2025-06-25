package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByStatus(String status);

    @Query("SELECT o FROM Order o WHERE o.product.id = :productId")
    List<Order> findByProductId(Long productId);

    @Query("SELECT o FROM Order o WHERE o.employee.id = :employeeId")
    List<Order> findByEmployeeId(Long employeeId);
}
