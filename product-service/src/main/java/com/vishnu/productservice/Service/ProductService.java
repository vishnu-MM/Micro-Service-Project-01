package com.vishnu.productservice.Service;

import com.vishnu.productservice.DTO.ProductResponseDTO;
import com.vishnu.productservice.Entity.Product;
import com.vishnu.productservice.Repository.ProductDAO;
import com.vishnu.productservice.DTO.ProductRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductDAO repository;

    public void createProduct( ProductRequestDTO productRequest) {
        Product newProduct = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        repository.save(newProduct);
        log.info("New product {} saved", newProduct.getId());
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> productList = repository.findAll();
        return productList.stream().map(this::ProductToResponse).toList();
    }

    private ProductResponseDTO ProductToResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}