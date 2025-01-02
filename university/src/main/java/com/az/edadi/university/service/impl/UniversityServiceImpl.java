package com.az.edadi.university.service.impl;

import com.az.edadi.dal.repository.UniversityRepository;
import com.az.edadi.university.model.res.UniversityRes;
import com.az.edadi.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Override
    public List<UniversityRes> getAllUniversities() {
        return universityRepository.findAll()
                .stream()
                .map(university -> new UniversityRes(university.getId().toString(),
                        university.getNameAz(),
                        university.getNameEn(),
                        university.getAbbrAz(),
                        university.getAbbrEn(),
                        university.getPhotoUrl())).toList();
    }
}
