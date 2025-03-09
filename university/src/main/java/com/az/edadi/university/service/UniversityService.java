package com.az.edadi.university.service;

import com.az.edadi.university.model.request.UniversityReq;
import com.az.edadi.university.model.response.UniversityRes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UniversityService {
    List<UniversityRes> findAll();
    UniversityRes getUniversityById(String id);
    UniversityRes createUniversity(UniversityReq universityReq);
    UniversityRes update(String id,UniversityReq universityReq);
    String delete(String id);
}
