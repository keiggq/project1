package com.example.demo.service;

import com.example.demo.dto.request.OrderRequest;
import com.example.demo.dto.response.OrderResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + id));
        return orderMapper.toResponse(order);
    }

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + request.getCustomerId()));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + request.getEmployeeId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + request.getProductId()));

        // Проверка наличия достаточного количества товара
        if (product.getQuantity() < request.getQuantity()) {
            throw new IllegalStateException("Not enough product quantity in stock");
        }

        // Создание заказа
        Order order = new Order();
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setProduct(product);
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(calculateTotalPrice(product.getPrice(), request.getQuantity()));
        order.setStatus("CREATED");

        // Обновление количества товара
        product.setQuantity(product.getQuantity() - request.getQuantity());
        productRepository.save(product);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponse updateOrder(Long id, OrderRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + id));

        // Обновление только тех полей, которые могут изменяться
        if (request.getQuantity() != null) {
            // Логика обновления количества товара
            updateProductQuantity(order, request.getQuantity());
            order.setQuantity(request.getQuantity());
            order.setTotalPrice(calculateTotalPrice(order.getProduct().getPrice(), request.getQuantity()));
        }
        if (request.getStatus() != null) {
            order.setStatus(request.getStatus());
        }

        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toResponse(updatedOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + id));

        // Возврат товара на склад при отмене заказа
        Product product = order.getProduct();
        product.setQuantity(product.getQuantity() + order.getQuantity());
        productRepository.save(product);

        orderRepository.delete(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status).stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponse updateOrderStatus(Long id, String newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + id));

        // Дополнительная логика при изменении статуса
        if ("CANCELLED".equals(newStatus)) {
            // Возврат товара на склад при отмене заказа
            Product product = order.getProduct();
            product.setQuantity(product.getQuantity() + order.getQuantity());
            productRepository.save(product);
        }

        order.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toResponse(updatedOrder);
    }

    // Вспомогательные методы
    private BigDecimal calculateTotalPrice(BigDecimal price, Integer quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    private void updateProductQuantity(Order order, Integer newQuantity) {
        int quantityDifference = newQuantity - order.getQuantity();
        Product product = order.getProduct();

        if (product.getQuantity() < quantityDifference) {
            throw new IllegalStateException("Not enough product quantity in stock");
        }

        product.setQuantity(product.getQuantity() - quantityDifference);
        productRepository.save(product);
    }
}