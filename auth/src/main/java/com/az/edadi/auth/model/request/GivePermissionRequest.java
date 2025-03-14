package com.az.edadi.auth.model.request;

import com.az.edadi.dal.types.Permission;

public record GivePermissionRequest(String userId, Permission permission) {
}
