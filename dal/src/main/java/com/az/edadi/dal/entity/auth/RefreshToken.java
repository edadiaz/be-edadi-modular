package com.az.edadi.dal.entity.auth;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "refresh_token")
public class RefreshToken {

    @Id
    private String id;
    private String userId;
    private String tokenId;
    private Boolean isActive;
    private Date startDate;
    private Date endDate;
    private String ip;
    private String fingerPrint;
}
