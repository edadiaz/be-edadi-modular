package com.az.edadi.post.service;

import com.az.edadi.model.request.post.PostFilterRequest;
import com.az.edadi.model.request.post.PostRequest;
import com.az.edadi.model.response.post.PostResponse;

public interface PostService {

    PostResponse addPost(PostRequest postRequest);

    PostResponse getPost(String postId);

    PostResponse getPostList(PostFilterRequest filterRequest);

    void deletePost(String postId);
}
