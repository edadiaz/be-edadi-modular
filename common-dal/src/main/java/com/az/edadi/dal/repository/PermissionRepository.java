package com.az.edadi.dal.repository;

import com.az.edadi.dal.entity.auth.UserPermission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends MongoRepository<UserPermission, String> {
    List<UserPermission> findByUserId(String userId);
 }
