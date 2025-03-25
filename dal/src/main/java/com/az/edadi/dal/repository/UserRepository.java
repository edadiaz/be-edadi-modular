package com.az.edadi.dal.repository;

import com.az.edadi.dal.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String lowerCase);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String lowerCase);

    List<User> findByIdIn(List<String> userIds);
}
