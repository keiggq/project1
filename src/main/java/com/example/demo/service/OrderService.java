package com.example.demo.service;

import com.example.demo.dto.request.OrderRequest;
import com.example.demo.dto.response.OrderResponse;
import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
    OrderResponse createOrder(OrderRequest request);
    OrderResponse updateOrder(Long id, OrderRequest request);
    void deleteOrder(Long id);
    List<OrderResponse> getOrdersByCustomer(Long customerId);
    List<OrderResponse> getOrdersByStatus(String status);
    OrderResponse updateOrderStatus(Long id, String newStatus);
}
