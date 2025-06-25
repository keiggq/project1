package com.example.demo.service;

import com.example.demo.dto.request.EmployeeRequest;
import com.example.demo.dto.response.EmployeeResponse;
import com.example.demo.exception.DuplicateEntityException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEntityException("Email already exists");
        }
        Employee employee = employeeMapper.toEntity(request);
        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long id) {
        return employeeMapper.toResponse(
                employeeRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not found"))
        );
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (!request.getEmail().equals(employee.getEmail()) &&
                employeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEntityException("Email already taken");
        }

        employeeMapper.updateEntity(request, employee);
        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> getEmployeesByPosition(String position) {
        return employeeRepository.findByPosition(position).stream()
                .map(employeeMapper::toResponse)
                .toList();
    }
}
