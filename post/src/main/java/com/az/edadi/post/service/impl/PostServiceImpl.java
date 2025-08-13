package com.az.edadi.post.service.impl;

import com.az.edadi.dal.repository.PostRepository;
import com.az.edadi.model.request.post.PostFilterRequest;
import com.az.edadi.model.request.post.PostRequest;
import com.az.edadi.model.response.post.PostResponse;
import com.az.edadi.post.service.PostService;
import com.az.edadi.service.adapter.post.PostAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostAdapter postAdapter;

    @Override
    public PostResponse addPost(PostRequest postRequest) {
        var post = postAdapter.map(postRequest);
        postRepository.save(post);
        return postAdapter.map(post);
    }

    @Override
    public PostResponse getPost(String postId) {
        return postRepository.findById(postId).map(postAdapter::map).orElseThrow();
    }

    @Override
    public PostResponse getPostList(PostFilterRequest filterRequest) {
        return null;
    }
}
