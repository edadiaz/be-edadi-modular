package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.model.request.GivePermissionRequest;
import com.az.edadi.auth.service.PermissionService;
import com.az.edadi.dal.entity.auth.UserPermission;
import com.az.edadi.dal.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public void givePermissionToUser(GivePermissionRequest request) {
        UserPermission userPermission = new UserPermission();
        userPermission.setUserId(request.userId());
        userPermission.setPermission(request.permission());
        permissionRepository.save(userPermission);
    }
}
