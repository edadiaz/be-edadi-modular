package com.az.edadi.dal.entity.notification;

import com.az.edadi.dal.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "notification")
@EqualsAndHashCode(callSuper = true)
public class Notification extends BaseEntity {
    private String userId;
}
