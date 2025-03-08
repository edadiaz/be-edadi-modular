package com.az.edadi.dal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.az.edadi.dal.no_sql.repository")
public class MongoConfig {
}
