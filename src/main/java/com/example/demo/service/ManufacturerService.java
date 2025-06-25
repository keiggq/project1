package com.example.demo.service;

import com.example.demo.dto.request.ManufacturerRequest;
import com.example.demo.dto.response.ManufacturerResponse;
import java.util.List;

public interface ManufacturerService {
    ManufacturerResponse createManufacturer(ManufacturerRequest request);
    ManufacturerResponse getManufacturerById(Long id);
    ManufacturerResponse updateManufacturer(Long id, ManufacturerRequest request);
    void deleteManufacturer(Long id);
    List<ManufacturerResponse> getAllManufacturers();
}
