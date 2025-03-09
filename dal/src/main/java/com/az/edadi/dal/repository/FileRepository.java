package com.az.edadi.dal.repository;

import com.az.edadi.dal.entity.file_storage.EdaFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<EdaFile, String> {
}
