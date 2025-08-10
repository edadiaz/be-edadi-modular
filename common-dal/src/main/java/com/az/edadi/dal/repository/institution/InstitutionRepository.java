package com.az.edadi.dal.repository.institution;

import com.az.edadi.dal.entity.institution.Institution;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstitutionRepository extends MongoRepository<Institution, String> {
    @Cacheable(cacheNames = "institution_entity")
    Optional<Institution> findById(String id);
}
