package com.vishnu.productservice.Repository;

import com.vishnu.productservice.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {}