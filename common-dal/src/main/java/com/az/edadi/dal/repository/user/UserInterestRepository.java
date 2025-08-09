package com.az.edadi.dal.repository.user;

import com.az.edadi.dal.entity.user.UserInterest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterestRepository extends MongoRepository<UserInterest, String> {
}
