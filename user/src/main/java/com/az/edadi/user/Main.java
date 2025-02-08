package com.az.edadi.user;

import com.az.edadi.dal.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {
    private final UserRepository userRepository;

    public Main(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("api/v1/test")
    public String test() {
        return userRepository.findAll().toString();
    }
}