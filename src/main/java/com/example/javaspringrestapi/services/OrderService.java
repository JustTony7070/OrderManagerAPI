package com.example.javaspringrestapi.services;

import com.example.javaspringrestapi.entities.Order;
import com.example.javaspringrestapi.entities.Product;
import com.example.javaspringrestapi.entities.User;
import com.example.javaspringrestapi.enums.OrderStatus;
import com.example.javaspringrestapi.repos.ProductRepo;
import com.example.javaspringrestapi.repos.UserRepo;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    private final ObjectProvider<Order> objectProvider;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    public OrderService(ObjectProvider<Order> objectProvider, UserRepo userRepo, ProductRepo productRepo) {
        this.objectProvider = objectProvider;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public Order getCustom(User user, List<Product> products, OrderStatus orderStatus) {
        Order o = objectProvider.getObject();
        o.setUser(user);
        o.setProducts(products);
        o.setStatus(orderStatus);
        return o;
    }

    public Order getFake() {
        Random r = new Random();
        List<User> users = userRepo.findAll();

        User u = users.get(r.nextInt(0, users.size()));
        List<Product> products = productRepo.findAll();

        List<Product> randomProducts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            randomProducts.add(products.get(r.nextInt(0, products.size())));
        }

        Order o = objectProvider.getObject();
        o.setUser(u);
        o.setProducts(randomProducts);
        return o;
    }
}
