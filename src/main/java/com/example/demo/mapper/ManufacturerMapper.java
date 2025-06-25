package com.example.demo.mapper;

import com.example.demo.dto.request.ManufacturerRequest;
import com.example.demo.dto.response.ManufacturerResponse;
import com.example.demo.model.Manufacturer;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerMapper {

    public Manufacturer toEntity(ManufacturerRequest request) {
        if (request == null) {
            return null;
        }

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(request.getName());
        manufacturer.setPhone(request.getPhone());
        manufacturer.setEmail(request.getEmail());
        manufacturer.setAddress(request.getAddress());
        return manufacturer;
    }

    public void updateEntity(ManufacturerRequest request, Manufacturer entity) {
        if (request == null || entity == null) {
            return;
        }

        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getPhone() != null) {
            entity.setPhone(request.getPhone());
        }
        if (request.getEmail() != null) {
            entity.setEmail(request.getEmail());
        }
        if (request.getAddress() != null) {
            entity.setAddress(request.getAddress());
        }
    }

    public ManufacturerResponse toResponse(Manufacturer manufacturer) {
        if (manufacturer == null) {
            return null;
        }

        ManufacturerResponse response = new ManufacturerResponse();
        response.setId(manufacturer.getId());
        response.setName(manufacturer.getName());
        response.setPhone(manufacturer.getPhone());
        response.setEmail(manufacturer.getEmail());
        response.setAddress(manufacturer.getAddress());
        return response;
    }
}
