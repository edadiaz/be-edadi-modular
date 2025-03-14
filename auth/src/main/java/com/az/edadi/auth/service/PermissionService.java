package com.az.edadi.auth.service;

import com.az.edadi.auth.model.request.GivePermissionRequest;

public interface PermissionService {
    void givePermissionToUser(GivePermissionRequest request);
}
