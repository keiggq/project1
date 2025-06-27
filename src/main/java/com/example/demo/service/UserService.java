package com.example.demo.service;


import com.example.demo.dto.request.AuthRequest;
import com.example.demo.entity.Customers;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository buyerRepository;

    public UserService(UserRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public Customers register(RegisterRequest request) {
        Customers customer = new Customers();
        customer.setName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setPassword(request.getPassword());
        return buyerRepository.save(customer);
    }

    public AuthResponse login(AuthRequest request) {
        Customers buyer = buyerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!buyer.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Неверный пароль");
        }

        return new AuthResponse(buyer.getId(), buyer.getName());
    }
}
