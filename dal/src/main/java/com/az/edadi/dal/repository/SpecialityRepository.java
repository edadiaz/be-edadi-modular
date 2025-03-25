package com.az.edadi.dal.repository;

import com.az.edadi.dal.entity.speciality.Speciality;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpecialityRepository extends MongoRepository<Speciality, UUID> {
    List<Speciality> findByInstitutionId(String universityId);

}
