package com.example.demo.mapper;

import com.example.demo.dto.request.CustomerRequest;
import com.example.demo.dto.response.CustomerResponse;
import com.example.demo.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest request) {
        if (request == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setPhone(formatPhoneForStorage(request.getPhone()));
        customer.setEmail(request.getEmail());
        customer.setDiscountCard(request.getDiscountCard());
        customer.setDiscountRate(request.getDiscountRate());
        return customer;
    }

    public void updateEntity(CustomerRequest request, Customer entity) {
        if (request == null || entity == null) {
            return;
        }

        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getPhone() != null) {
            entity.setPhone(formatPhoneForStorage(request.getPhone()));
        }
        if (request.getEmail() != null) {
            entity.setEmail(request.getEmail());
        }
        if (request.getDiscountCard() != null) {
            entity.setDiscountCard(request.getDiscountCard());
        }
        if (request.getDiscountRate() != null) {
            entity.setDiscountRate(request.getDiscountRate());
        }
    }

    public CustomerResponse toResponse(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setPhone(formatPhoneForDisplay(customer.getPhone()));
        response.setEmail(customer.getEmail());
        response.setDiscountCard(customer.getDiscountCard());
        response.setDiscountRate(customer.getDiscountRate());
        return response;
    }

    private String formatPhoneForStorage(String phone) {
        if (phone == null) return null;
        return phone.replaceAll("[^0-9+]", "");
    }

    private String formatPhoneForDisplay(String phone) {
        if (phone == null) return null;
        String digits = phone.replaceAll("[^0-9]", "");
        if (digits.startsWith("7") && digits.length() == 11) {
            return String.format("+7 (%s) %s-%s-%s",
                    digits.substring(1, 4),
                    digits.substring(4, 7),
                    digits.substring(7, 9),
                    digits.substring(9));
        }
        return phone;
    }
}
