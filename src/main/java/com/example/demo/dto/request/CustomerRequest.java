package com.example.demo.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String discountCard;

    @DecimalMin(value = "0.0", message = "Discount rate cannot be negative")
    @DecimalMax(value = "1.0", message = "Discount rate cannot exceed 100%")
    private Double discountRate;
}
