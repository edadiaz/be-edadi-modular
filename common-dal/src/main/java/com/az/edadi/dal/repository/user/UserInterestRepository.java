package com.az.edadi.dal.repository.user;

import com.az.edadi.dal.entity.user.UserInterest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestRepository extends MongoRepository<UserInterest, String> {
    List<UserInterest> findByUserId(String userId);
}
