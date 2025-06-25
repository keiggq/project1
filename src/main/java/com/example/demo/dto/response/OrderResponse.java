package com.example.demo.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long employeeId;
    private String employeeName;
    private Long productId;
    private String productName;
    private Integer quantity;
    private LocalDateTime date;
    private BigDecimal totalPrice;
    private String status;
}


