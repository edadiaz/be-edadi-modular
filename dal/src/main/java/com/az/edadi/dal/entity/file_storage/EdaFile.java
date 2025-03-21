package com.az.edadi.dal.entity.file_storage;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "edaFile")
public class EdaFile  {

    @Id
    private String id;

    private String path;

    private String fileType;

    private String fileName;

    private Long size;

    private Boolean isUsed;

}
