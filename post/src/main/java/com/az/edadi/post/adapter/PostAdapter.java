package com.az.edadi.post.adapter;

import com.az.edadi.common.util.AuthUtils;
import com.az.edadi.dal.entity.post.Post;
import com.az.edadi.model.request.post.PostRequest;
import com.az.edadi.model.response.post.PostResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostAdapter {
   public Post map(PostRequest request) {
        var post = new Post();
        post.setContent(request.getContent());
        post.setParentId(request.getParentId());
        post.setParentType(request.getParentEntityType());
        post.setUserId(AuthUtils.getCurrentUserId());
        post.setLikeCount(0);
        post.setCommentCount(0);
        return post;
    }

    public PostResponse map(Post post) {
        var postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setContent(post.getContent());
        post.setUserId(post.getUserId());
        postResponse.setLikeCount(post.getLikeCount());
        postResponse.setCommentCount(post.getCommentCount());
        postResponse.setCreatedDate(LocalDate.from(post.getCreatedTs()));
        return postResponse;
    }
}
