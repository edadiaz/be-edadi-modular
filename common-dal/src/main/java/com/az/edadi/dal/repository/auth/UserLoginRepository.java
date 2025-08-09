package com.az.edadi.dal.repository.auth;

import com.az.edadi.dal.entity.auth.UserLogin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends MongoRepository<UserLogin, String> {

}
