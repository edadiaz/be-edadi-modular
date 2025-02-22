package com.az.edadi.dal.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
 public class DynamoDbConfiguration {
    @Value("${aws.access}")
    private String awsId;

    @Value("${aws.secret}")
    private String awsKey;

    @Value("${aws.region}")
    private String region;
    @Bean
    public DynamoDbClient dynamoDbClient() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                awsId,
                awsKey
        );
        return DynamoDbClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

}