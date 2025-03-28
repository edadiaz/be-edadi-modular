package com.az.edadi.user;

import com.az.edadi.dal.repository.PermissionRepository;
import com.az.edadi.dal.repository.PostRepository;
import com.az.edadi.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Main {
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    @GetMapping("api/v1/test")
    public String test() {
        long start = System.currentTimeMillis();


        return userRepository.findAll().toString()+" "+(System.currentTimeMillis()-start);
     }

    @GetMapping("api/v1/test2")
    public String test2() {
        long start = System.currentTimeMillis();


        return permissionRepository.findAll().toString()+" "+(System.currentTimeMillis()-start);
    }
}