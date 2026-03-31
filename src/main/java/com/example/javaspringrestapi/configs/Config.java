package com.example.javaspringrestapi.configs;

import com.example.javaspringrestapi.entities.Order;
import com.example.javaspringrestapi.entities.Product;
import com.example.javaspringrestapi.entities.User;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.math.BigDecimal;

@Configuration
public class Config {
    @Autowired
    private Faker f;

    // Product
    @Bean
    @Scope("prototype")
    @Primary
    public Product getProduct() {
        return new Product();
    }

    @Bean(name = "fakeProduct")
    @Scope("prototype")
    public Product getFakeProduct() {
        return Product.builder()
                .name(f.commerce().productName())
                .price(BigDecimal.valueOf(f.number().randomDouble(2, 10, 1000)))
                .description(f.lorem().sentence(30))
                .build();
    }

    // User
    @Bean
    @Primary
    @Scope("prototype")
    public User getUser() {
        return new User();
    }

    @Bean(name = "fakeUser")
    @Scope("prototype")
    public User getFakeUser() {
        return User.builder()
                .username(f.name().username())
                .password(f.internet().password())
                .email(f.internet().emailAddress())
                .build();
    }

    // Order
    @Bean
    @Scope("prototype")
    public Order getOrder() {
        return new Order();
    }
}
