package com.az.edadi.dal.repository.notification;

import com.az.edadi.dal.entity.notification.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends MongoRepository<Notification,String> {

    Page<Notification> findByUserId(String userId, Pageable pageable);
}
