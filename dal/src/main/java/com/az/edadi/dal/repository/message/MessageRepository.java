package com.az.edadi.dal.repository.message;

import com.az.edadi.dal.entity.message.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
