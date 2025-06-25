package com.example.demo.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private String position; // Должность
    private LocalDate hireDate; // Дата приема на работу
    private BigDecimal salary; // Зарплата
    private int yearsOfService;
}
