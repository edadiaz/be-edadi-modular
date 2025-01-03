package com.az.edadi.dal.entity.file_storage;

import com.az.edadi.dal.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Version;

@Setter
@Getter
@Entity
@Table(name = "eda_file")
public class EdaFile extends BaseEntity {

    @Column(name = "path", nullable = false, length = 100)
    private String path;

    @Column(name = "file_type", nullable = false, length = 50)
    private String fileType;

    @Column(name = "file_name", nullable = false, length = 50)
    private String fileName;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "is_used", nullable = false)
    private Boolean isUsed = false;

}
