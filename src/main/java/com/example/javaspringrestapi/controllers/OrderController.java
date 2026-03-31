package com.example.javaspringrestapi.controllers;

import com.example.javaspringrestapi.entities.Order;
import com.example.javaspringrestapi.enums.OrderStatus;
import com.example.javaspringrestapi.repos.OrderRepo;
import com.example.javaspringrestapi.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepo orderRepo;

    public OrderController(OrderService orderService, OrderRepo orderRepo) {
        this.orderService = orderService;
        this.orderRepo = orderRepo;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(){
        return ResponseEntity.ok(orderRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createNewOrder(@RequestBody Order order){
        order.setId(null);
        order.setTotalPrice(null);
        order.setCreatedAt(null);

        return ResponseEntity.ok(orderRepo.save(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id){
        return ResponseEntity.ok(orderRepo.findById(id).get());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId){
        return ResponseEntity.ok(orderRepo.findByUserId(userId));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> CancelOrder(@PathVariable Long id){
        Order order = orderRepo.findById(id).get();
        order.setStatus(OrderStatus.CANCELLED);
        return ResponseEntity.ok(orderRepo.save(order));
    }
}
