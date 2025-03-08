package com.az.edadi.dal.no_sql.repository;

import com.az.edadi.dal.entity.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends MongoRepository<Post, String> {
}
