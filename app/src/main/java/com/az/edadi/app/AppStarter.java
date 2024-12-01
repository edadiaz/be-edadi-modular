package com.az.edadi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(AppConfig.class)
@SpringBootApplication
public class AppStarter {


    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }
}