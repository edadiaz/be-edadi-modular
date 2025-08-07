package com.az.edadi.university.service;

import com.az.edadi.model.request.UniversityRequestModel;
import com.az.edadi.model.response.institution.InstitutionPageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UniversityService {
    List<InstitutionPageResponse> findAll();
    InstitutionPageResponse getUniversityById(String id);
    InstitutionPageResponse createUniversity(UniversityRequestModel universityReq);
    InstitutionPageResponse update(String id, UniversityRequestModel universityReq);
    String delete(String id);
}
