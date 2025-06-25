package com.example.demo.mapper;

import com.example.demo.dto.request.EmployeeRequest;
import com.example.demo.dto.response.EmployeeResponse;
import com.example.demo.model.Employee;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {
        if (request == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setPosition(request.getPosition());
        employee.setHireDate(request.getHireDate());
        employee.setSalary(request.getSalary());
        return employee;
    }

    public void updateEntity(EmployeeRequest request, Employee entity) {
        if (request == null || entity == null) {
            return;
        }

        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getPosition() != null) {
            entity.setPosition(request.getPosition());
        }
        if (request.getHireDate() != null) {
            entity.setHireDate(request.getHireDate());
        }
        if (request.getSalary() != null && request.getSalary().compareTo(BigDecimal.ZERO) > 0) {
            entity.setSalary(request.getSalary());
        }
    }

    public EmployeeResponse toResponse(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setPosition(employee.getPosition());
        response.setHireDate(employee.getHireDate());
        response.setSalary(employee.getSalary());
        response.setYearsOfService(calculateYearsOfService(employee.getHireDate()));
        return response;
    }

    private int calculateYearsOfService(LocalDate hireDate) {
        if (hireDate == null) return 0;
        return Period.between(hireDate, LocalDate.now()).getYears();
    }
}
