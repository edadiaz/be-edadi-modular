package com.az.edadi.dal.repository.auth;

import com.az.edadi.dal.entity.auth.EdadiToken;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdadiTokenRepository extends MongoRepository<EdadiToken, String> {
    @Cacheable(value = "edadi_token", key = "#tokenId")
    EdadiToken findByTokenId(String tokenId);
}
