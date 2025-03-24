package com.az.edadi.dal.repository.message;

import com.az.edadi.dal.entity.message.ConversationUser;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationUserRepository extends MongoRepository<ConversationUser, String> {
    @Aggregation(pipeline = {
            "{ $match: { userId: { $in: [?0, ?1] } } }",
            "{ $group: { _id: '$conversationId', users: { $push: '$userId' } } }",
            "{ $match: { $expr: { $eq: [{ $size: '$users' }, 2] } } }",
            "{ $match: { users: { $all: [?0, ?1] } } }"
    })
    List<ConversationUser> findConversationIdForUsers(String currentUserId, String targetUserId);
}
