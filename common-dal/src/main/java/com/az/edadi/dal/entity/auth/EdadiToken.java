package com.az.edadi.dal.entity.auth;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "edadi_token")
public class EdadiToken {

    @Id
    private String id;

    @Indexed(unique = true)
    private String tokenId;

    private String userId;
    private boolean isActive;
    private Date startDate;
    private Date endDate;
    private String ip;
    private String fingerPrint;
}
