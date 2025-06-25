package com.example.demo.service;

import com.example.demo.dto.request.EmployeeRequest;
import com.example.demo.dto.response.EmployeeResponse;
import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);
    EmployeeResponse getEmployeeById(Long id);
    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);
    void deleteEmployee(Long id);
    List<EmployeeResponse> getAllEmployees();
    List<EmployeeResponse> getEmployeesByPosition(String position);
}
