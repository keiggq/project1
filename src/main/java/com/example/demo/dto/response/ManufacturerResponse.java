package com.example.demo.dto.response;

import lombok.Data;

@Data
public class ManufacturerResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
}
