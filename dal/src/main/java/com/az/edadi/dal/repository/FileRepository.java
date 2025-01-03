package com.az.edadi.dal.repository;

import com.az.edadi.dal.entity.file_storage.EdaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FileRepository extends JpaRepository<EdaFile, UUID> {
}
