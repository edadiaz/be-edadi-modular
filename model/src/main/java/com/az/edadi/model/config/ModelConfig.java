package com.az.edadi.model.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.az.edadi.model"
})
public class ModelConfig {
}
