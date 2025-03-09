package com.az.edadi.dal.entity.file_storage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
