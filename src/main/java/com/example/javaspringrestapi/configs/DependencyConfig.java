package com.example.javaspringrestapi.configs;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DependencyConfig {
    @Bean
    public Faker getFaker(){
        return new Faker(Locale.of("it", "IT"));
    }
}
