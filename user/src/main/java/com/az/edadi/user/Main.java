package com.az.edadi.user;

import com.az.edadi.dal.entity.user.Interest;
import com.az.edadi.dal.repository.PermissionRepository;
import com.az.edadi.dal.repository.PostRepository;
import com.az.edadi.dal.repository.UserRepository;
import com.az.edadi.dal.repository.user.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Main {
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final InterestRepository interestRepository;

    @GetMapping("api/v1/test")
    public String test() {
        Interest i = new Interest();
        i.setNameAz("test");
        i.setNameEn("test");
        i.setNameRu("test");
       return interestRepository.save(i).toString();

     }

    @GetMapping("api/v1/test2")
    public String test2() {
        long start = System.currentTimeMillis();


        return permissionRepository.findAll().toString() + " " + (System.currentTimeMillis() - start);
    }
}