package com.example.demo.controller;
import com.example.demo.dto.request.ManufacturerRequest;
import com.example.demo.dto.response.ManufacturerResponse;
import com.example.demo.service.ManufacturerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<ManufacturerResponse>> getAllManufacturers() {
        return ResponseEntity.ok(manufacturerService.getAllManufacturers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerResponse> getManufacturerById(@PathVariable Long id) {
        return ResponseEntity.ok(manufacturerService.getManufacturerById(id));
    }

    @PostMapping
    public ResponseEntity<ManufacturerResponse> createManufacturer(
            @Valid @RequestBody ManufacturerRequest request) {
        return new ResponseEntity<>(
                manufacturerService.createManufacturer(request),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManufacturerResponse> updateManufacturer(
            @PathVariable Long id,
            @Valid @RequestBody ManufacturerRequest request) {
        return ResponseEntity.ok(manufacturerService.updateManufacturer(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
        manufacturerService.deleteManufacturer(id);
        return ResponseEntity.noContent().build();
    }
}
