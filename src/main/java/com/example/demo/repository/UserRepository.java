package com.example.demo.repository;

import com.example.demo.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Customers, Long> {
    Optional<Customers> findByEmail(String email);
}