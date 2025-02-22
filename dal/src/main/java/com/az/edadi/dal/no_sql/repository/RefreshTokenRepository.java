package com.az.edadi.dal.no_sql.repository;


import com.az.edadi.dal.no_sql.table.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final DynamoDbClient dynamoDbClient;
    private static final String TABLE_NAME = "RefreshToken";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public void saveToken(RefreshToken refreshToken) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("userId", AttributeValue.builder().s(refreshToken.getUserId()).build()); // Primary Key
        item.put("tokenId", AttributeValue.builder().s(refreshToken.getTokenId()).build());
        item.put("startDate", AttributeValue.builder().s(refreshToken.getStartDate().toString()).build());
        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();
        dynamoDbClient.putItem(request);
    }

    public RefreshToken findByTokenId(String tokenId) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("tokenId", AttributeValue.builder().s(tokenId).build()); // Primary Key
        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();
        GetItemResponse response = dynamoDbClient.getItem(request);
        return Optional.ofNullable(response.item()).map(this::mapToRefreshToken).orElseThrow();
    }

    RefreshToken mapToRefreshToken(Map<String, AttributeValue> item) {
        return new RefreshToken(
                item.get("tokenId").s(),
                item.get("userId").s(),
                LocalDate.parse(item.get("startDate").s(), formatter)
        );
    }
}
