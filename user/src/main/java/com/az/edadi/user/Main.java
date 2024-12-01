package com.az.edadi.user;

import com.az.edadi.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {
    private final UserRepository userRepository;

    @Autowired
    public Main(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String test() {
        return userRepository.findAll().toString();
    }
}