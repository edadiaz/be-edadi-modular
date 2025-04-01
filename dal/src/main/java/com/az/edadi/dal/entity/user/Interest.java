package com.az.edadi.dal.entity.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "interest")
public class Interest {
    @Id
    private String id;
    private String nameAz;
    private String nameEn;
    private String nameRu;
}
