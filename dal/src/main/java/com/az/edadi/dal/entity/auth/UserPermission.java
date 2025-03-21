package com.az.edadi.dal.entity.auth;

import com.az.edadi.dal.types.Permission;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "user_permission")
  public class UserPermission {

    @Id
    private String id;
    private String userId;
    private Permission permission;
    private LocalDate givenDate;
    private String authorId;

}
