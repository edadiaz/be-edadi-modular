package com.az.edadi.dal.repository;

import com.az.edadi.dal.entity.institution.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UniversityRepository extends JpaRepository<Institution, UUID> {
}
