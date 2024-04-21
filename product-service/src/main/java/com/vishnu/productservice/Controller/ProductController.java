package com.vishnu.productservice.Controller;

import com.vishnu.productservice.DTO.ProductResponseDTO;
import com.vishnu.productservice.Service.ProductService;
import com.vishnu.productservice.DTO.ProductRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequestDTO productRequest) {
            service.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getAllProducts() {
        return service.getAllProducts();
    }
}