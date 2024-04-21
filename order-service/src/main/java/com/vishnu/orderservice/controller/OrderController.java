package com.vishnu.orderservice.controller;

import com.vishnu.orderservice.DTO.OrderRequestDTO;
import com.vishnu.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name="inventory")
    @Retry(name="inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequestDTO orderRequest){
//        service.placeOrder(orderRequest);
        return CompletableFuture.supplyAsync(() -> service.placeOrder(orderRequest));
    }

    public CompletableFuture<String> fallbackMethod(OrderRequestDTO orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(()->"Oops! Some thing went wrong, try after sometimes");
    }
}