package com.example.demo.service;

import com.example.demo.dto.request.ManufacturerRequest;
import com.example.demo.dto.response.ManufacturerResponse;
import com.example.demo.exception.DuplicateEntityException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.ManufacturerMapper;
import com.example.demo.model.Manufacturer;
import com.example.demo.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerMapper manufacturerMapper;

    @Override
    @Transactional
    public ManufacturerResponse createManufacturer(ManufacturerRequest request) {
        if (manufacturerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEntityException("Manufacturer with this email already exists");
        }

        Manufacturer manufacturer = manufacturerMapper.toEntity(request);
        return manufacturerMapper.toResponse(manufacturerRepository.save(manufacturer));
    }

    @Override
    @Transactional(readOnly = true)
    public ManufacturerResponse getManufacturerById(Long id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Manufacturer not found"));
        return manufacturerMapper.toResponse(manufacturer);
    }

    @Override
    @Transactional
    public ManufacturerResponse updateManufacturer(Long id, ManufacturerRequest request) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Manufacturer not found"));

        if (!manufacturer.getEmail().equals(request.getEmail()) &&
                manufacturerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEntityException("Manufacturer with this email already exists");
        }

        manufacturerMapper.updateEntity(request, manufacturer);
        return manufacturerMapper.toResponse(manufacturerRepository.save(manufacturer));
    }

    @Override
    @Transactional
    public void deleteManufacturer(Long id) {
        if (!manufacturerRepository.existsById(id)) {
            throw new EntityNotFoundException("Manufacturer not found");
        }
        manufacturerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ManufacturerResponse> getAllManufacturers() {
        return manufacturerRepository.findAll().stream()
                .map(manufacturerMapper::toResponse)
                .toList();
    }
}
