package com.example.demo.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @Pattern(regexp = "^\\+?[0-9\\s()-]+$", message = "Invalid phone format")
    private String phone;

    @Email(message = "Invalid email format")
    private String email;

    private String discountCard; // Опциональное поле

    @Min(value = 0, message = "Discount rate cannot be negative")
    @Max(value = 100, message = "Discount rate cannot exceed 100")
    private Double discountRate;
}
