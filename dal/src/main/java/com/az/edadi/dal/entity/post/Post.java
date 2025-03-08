package com.az.edadi.dal.entity.post;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Document(collection = "post")
public class Post {

    @Id
    private String id;
    private String text;
    private LocalDate localDate;
    private UUID userId;
    private String tag;

}
