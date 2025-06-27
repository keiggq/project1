package com.example.demo.dto.request;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class OrderRequest {
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @Positive(message = "Quantity must be positive")
    @Min(value = 1, message = "Minimum quantity is 1")
    private Integer quantity;

    @PositiveOrZero(message = "Total price cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Invalid price format")
    private BigDecimal totalPrice;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "PENDING|PROCESSING|COMPLETED|CANCELLED",
            message = "Invalid status. Allowed: PENDING, PROCESSING, COMPLETED, CANCELLED")
    private String status;
}
