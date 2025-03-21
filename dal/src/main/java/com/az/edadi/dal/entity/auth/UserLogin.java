package com.az.edadi.dal.entity.auth;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "login")
public class UserLogin {
    @Id
    private String id;
    private String userId;
    private String ip;
    private String tokenId;
    private String fingerPrint;
    private LocalDate loginDate;
}
