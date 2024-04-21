package com.vishnu.orderservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDTO {
//    private Long id;
    private Long productCode;
    private BigDecimal price;
    private Integer quantity;
}
