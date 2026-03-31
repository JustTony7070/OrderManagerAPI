package com.example.javaspringrestapi.runners;

import com.example.javaspringrestapi.entities.Order;
import com.example.javaspringrestapi.entities.Product;
import com.example.javaspringrestapi.entities.User;
import com.example.javaspringrestapi.repos.OrderRepo;
import com.example.javaspringrestapi.repos.ProductRepo;
import com.example.javaspringrestapi.repos.UserRepo;
import com.example.javaspringrestapi.services.OrderService;
import com.example.javaspringrestapi.services.ProductService;
import com.example.javaspringrestapi.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakerRunner implements CommandLineRunner {
    private final ProductService productService;
    private final ProductRepo productRepo;

    private final UserService userService;
    private final UserRepo userRepo;

    private final OrderService orderService;
    private final OrderRepo orderRepo;

    public FakerRunner(ProductService productService,
                       ProductRepo productRepo,
                       UserService userService,
                       UserRepo userRepo,
                       OrderService orderService,
                       OrderRepo orderRepo) {
        this.productService = productService;
        this.productRepo = productRepo;
        this.userService = userService;
        this.userRepo = userRepo;
        this.orderService = orderService;
        this.orderRepo = orderRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        products();
        users();
        orders();
    }

    private void products(){
        if (productRepo.count() > 0)
            return;

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 20; i++)
            products.add(productService.getFake());

        productRepo.saveAll(products);
    }

    private void users(){
        if (userRepo.count() > 0)
            return;

        List<User> users = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            users.add(userService.getFake());
        }

        userRepo.saveAll(users);
    }

    private void orders(){
        if (orderRepo.count() > 0)
            return;

        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            orders.add(orderService.getFake());
        }

        orderRepo.saveAll(orders);
    }
}
