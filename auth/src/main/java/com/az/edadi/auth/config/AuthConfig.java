package com.az.edadi.auth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.az.edadi.auth"
})
public class AuthConfig {
}
