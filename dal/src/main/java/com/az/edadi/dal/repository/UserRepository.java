package com.az.edadi.dal.repository;

import com.az.edadi.dal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUsername(String lowerCase);

    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean existsByEmail(String lowerCase);
}
