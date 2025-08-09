package com.az.edadi.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.az.edadi.service"
})
public class ServiceConfig {
}
