package com.az.edadi.user;

import com.az.edadi.dal.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Main {
    private final PostRepository postRepository;

    @GetMapping("api/v1/test")
    public String test() {
        long start = System.currentTimeMillis();


        return postRepository.findAll().toString()+" "+(System.currentTimeMillis()-start);
     }
}