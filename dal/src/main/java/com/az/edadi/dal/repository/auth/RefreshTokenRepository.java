package com.az.edadi.dal.repository.auth;

import com.az.edadi.dal.entity.auth.RefreshToken;
import com.az.edadi.dal.entity.auth.UserLogin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    RefreshToken findByTokenId(String tokenId);
}
