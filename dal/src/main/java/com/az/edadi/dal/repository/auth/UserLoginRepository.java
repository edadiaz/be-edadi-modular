package com.az.edadi.dal.repository.auth;

import com.az.edadi.dal.entity.auth.UserLogin;
import com.az.edadi.dal.entity.auth.UserPermission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLoginRepository extends MongoRepository<UserLogin, String> {

}
