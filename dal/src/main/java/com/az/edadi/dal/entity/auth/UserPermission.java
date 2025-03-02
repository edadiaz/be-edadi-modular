package com.az.edadi.dal.entity.auth;

import com.az.edadi.dal.entity.BaseEntity;
import com.az.edadi.dal.types.Permission;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "user_permission")
public class UserPermission extends BaseEntity {
    private UUID userId;

    @Enumerated(EnumType.STRING)
    private Permission permission;
}
