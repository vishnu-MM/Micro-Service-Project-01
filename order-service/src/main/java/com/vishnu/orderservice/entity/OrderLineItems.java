package com.vishnu.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_line_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productCode;
    private BigDecimal price;
    private Integer quantity;
}
