package com.vishnu.orderservice.service;

import com.vishnu.orderservice.DTO.InventoryResponse;
import com.vishnu.orderservice.DTO.OrderLineItemsDTO;
import com.vishnu.orderservice.DTO.OrderRequestDTO;
import com.vishnu.orderservice.configuration.InventoryInterface;
import com.vishnu.orderservice.entity.Order;
import com.vishnu.orderservice.entity.OrderLineItems;
import com.vishnu.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
//    private final WebClient.Builder webClientBuilder;
    @Autowired
    private final InventoryInterface inventoryInterface;

    public String placeOrder(OrderRequestDTO orderRequest) {
        Order order = new Order();
        order.setOrderLineItems(orderRequest.getOrderLineItemsDTOList().stream().map(this::mapToDto).toList());

        List<Long> productCodes = orderRequest
                .getOrderLineItemsDTOList().stream().map(OrderLineItemsDTO::getProductCode).toList();

//        InventoryResponse[] inventoryResponses = webClientBuilder
//                .build()
//                .get()
//                .uri(
//                    "http://inventory-service/api/inventory",
//                    uriBuilder -> uriBuilder.queryParam("productCodeList",productCodes ).build()
//                )
//                .retrieve()
//                .bodyToMono(InventoryResponse[].class)
//                .block();
        List<InventoryResponse> inventoryResponses = inventoryInterface.isInStock(productCodes);
        System.out.println(inventoryResponses);

        orderRepository.save(order);
        return "Success";
    }

    private OrderLineItems mapToDto(OrderLineItemsDTO orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setProductCode(orderLineItemsDto.getProductCode());
        return orderLineItems;
    }
}