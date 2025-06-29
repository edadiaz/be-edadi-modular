package com.az.edadi.graphql.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.az.edadi.graphql"
})
public class GraphqlConfig {
}
