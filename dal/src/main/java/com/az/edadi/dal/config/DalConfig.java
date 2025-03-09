package com.az.edadi.dal.config;

 import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.az.edadi.dal.entity")
@ComponentScan(basePackages = {
        "com.az.edadi.dal"
})
public class DalConfig {
}
