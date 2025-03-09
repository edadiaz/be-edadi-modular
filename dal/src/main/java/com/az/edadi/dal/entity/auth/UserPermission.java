package com.az.edadi.dal.entity.auth;

import com.az.edadi.dal.types.Permission;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
  public class UserPermission {

    @Id
    private String id;

    private String userId;
    private Permission permission;
}
