package com.az.edadi.dal.repository;

import com.az.edadi.dal.entity.User;
import com.az.edadi.dal.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String lowerCase);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String lowerCase);
}
