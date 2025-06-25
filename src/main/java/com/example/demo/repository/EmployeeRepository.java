package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;  // Добавленный импорт
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Employee> findByPosition(String position);
    List<Employee> findByHireDateAfter(LocalDate date);  // Теперь будет работать
    List<Employee> findBySalaryGreaterThan(BigDecimal salary);
}