package com.example.demo.dto.response;

import lombok.Data;

@Data
public class CustomerResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String discountCard; // Номер карты лояльности
    private Double discountRate; // Процент скидки (0.1 = 10%)
}
