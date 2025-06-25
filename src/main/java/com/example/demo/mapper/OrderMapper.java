package com.example.demo.mapper;

import com.example.demo.dto.response.OrderResponse;
import com.example.demo.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setQuantity(order.getQuantity());
        response.setDate(order.getDate());
        response.setTotalPrice(order.getTotalPrice());
        response.setStatus(order.getStatus());

        if (order.getCustomer() != null) {
            response.setCustomerId(order.getCustomer().getId());
            response.setCustomerName(order.getCustomer().getName());
        }

        if (order.getEmployee() != null) {
            response.setEmployeeId(order.getEmployee().getId());
            response.setEmployeeName(order.getEmployee().getName());
        }

        if (order.getProduct() != null) {
            response.setProductId(order.getProduct().getId());
            response.setProductName(order.getProduct().getName());
        }

        return response;
    }
}