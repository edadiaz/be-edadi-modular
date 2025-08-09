package com.az.edadi.dal.repository.user;

import com.az.edadi.dal.entity.user.Interest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends MongoRepository<Interest, String> {
}
