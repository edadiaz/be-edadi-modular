package com.az.edadi.university.service;

import com.az.edadi.model.request.UniversityRequestModel;
import com.az.edadi.model.response.university.UniversityPageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UniversityService {
    List<UniversityPageResponse> findAll();
    UniversityPageResponse getUniversityById(String id);
    UniversityPageResponse createUniversity(UniversityRequestModel universityReq);
    UniversityPageResponse update(String id, UniversityRequestModel universityReq);
    String delete(String id);
}
