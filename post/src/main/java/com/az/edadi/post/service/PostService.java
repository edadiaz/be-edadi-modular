package com.az.edadi.post.service;

import com.az.edadi.post.model.request.PostRequest;
import com.az.edadi.post.model.response.PostRes;

public interface PostService {
    PostRes addPost(PostRequest postRequest);
}
