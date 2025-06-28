package com.az.edadi.dal.repository.message;

import com.az.edadi.dal.entity.message.ConversationUser;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationUserRepository extends MongoRepository<ConversationUser, String> {
    @Aggregation(pipeline = {
            "{ '$match': { 'userId': { '$in': [?0, ?1] } } }",
            "{ '$group': { '_id': '$conversationId', 'users': { '$addToSet': '$userId' }, 'documents': { '$push': '$$ROOT' } } }",
            "{ '$match': { 'users': { '$all': [?0, ?1] } } }",
            "{ '$unwind': '$documents' }",
            "{ '$replaceRoot': { 'newRoot': '$documents' } }"
    })
    List<ConversationUser> findConversationIdForUsers(String currentUserId, String targetUserId);

    @Aggregation(pipeline = {
            "{ $match: { 'userId': ?0 } }",
            "{ $addFields: {conversationId: { $toObjectId: '$conversationId' } } }",
            "{ $lookup: { from: 'conversation', localField: 'conversationId', foreignField: '_id', as: 'conversation' } }",
            "{ $unwind: '$conversation' }",
            "{ $sort: { 'conversation.createdDate': -1 }}",
            "{ $skip:  ?1}",
            "{ $limit: ?2 }"
    })
    List<ConversationUser> findRecentConversations(String userId, int offset, int size);

    List<ConversationUser> findByConversationId(String conversationId);



}
