package com.example.demo.mapper;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        if (request == null) {
            return null;
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setWarrantyPeriod(request.getWarrantyPeriod());
        return product;
    }

    public void updateEntity(ProductRequest request, Product entity) {
        if (request == null || entity == null) {
            return;
        }

        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getPrice() != null && request.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            entity.setPrice(request.getPrice());
        }
        if (request.getQuantity() != null) {
            entity.setQuantity(request.getQuantity());
        }
        if (request.getWarrantyPeriod() != null) {
            entity.setWarrantyPeriod(request.getWarrantyPeriod());
        }
    }

    public ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }

        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setManufacturerId(product.getManufacturer().getId());
        response.setManufacturerName(product.getManufacturer().getName());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setWarrantyPeriod(product.getWarrantyPeriod());
        return response;
    }
}
