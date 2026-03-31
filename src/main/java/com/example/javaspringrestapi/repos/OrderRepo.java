package com.example.javaspringrestapi.repos;

import com.example.javaspringrestapi.entities.Order;
import com.example.javaspringrestapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
