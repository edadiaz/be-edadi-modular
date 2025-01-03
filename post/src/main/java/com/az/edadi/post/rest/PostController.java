package com.az.edadi.post.rest;

import com.az.edadi.post.model.request.PostReq;
import com.az.edadi.post.model.response.PostRes;
import com.az.edadi.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;
    @PostMapping
    ResponseEntity<PostRes> addPost(@RequestBody PostReq postReq) {
        log.info("Post request received: {}", postReq);
        return ResponseEntity.ok(postService.addPost(postReq));
    }
}
