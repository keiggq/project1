package com.example.demo.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Long manufacturerId;
    private String manufacturerName; // Дополнительное поле для удобства
    private BigDecimal price;
    private Integer quantity;
    private Integer warrantyPeriod; // Гарантия в месяцах
}
