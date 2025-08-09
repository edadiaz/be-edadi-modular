package com.az.edadi.dal.repository;

import com.az.edadi.dal.entity.institution.Institution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends MongoRepository<Institution, String> {
}
