package com.az.edadi.dal.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.az.edadi.dal.repository")  //
@EntityScan(basePackages = "com.az.edadi.dal.entity")  // Add this to scan the entity package in dal
public class DalConfig {
}
