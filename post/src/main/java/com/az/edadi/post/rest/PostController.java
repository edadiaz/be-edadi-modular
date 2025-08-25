package com.az.edadi.post.rest;

import com.az.edadi.model.request.post.PostFilterRequest;
import com.az.edadi.model.request.post.PostRequest;
import com.az.edadi.model.response.post.PostResponse;
import com.az.edadi.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    ResponseEntity<PostResponse> addPost(@RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(postService.addPost(postRequest));
    }

    @GetMapping
    ResponseEntity<PostResponse> getPostList(@ModelAttribute PostFilterRequest filterRequest) {
        return ResponseEntity.ok(postService.getPostList(filterRequest));
    }

    @GetMapping("{postId}")
    ResponseEntity<PostResponse> getPost(@PathVariable String postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @DeleteMapping("{postId}")
    ResponseEntity<HttpStatus> deletePost(@PathVariable String postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }


}
