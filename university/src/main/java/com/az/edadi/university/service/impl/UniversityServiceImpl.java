package com.az.edadi.university.service.impl;

import com.az.edadi.model.request.UniversityRequestModel;
import com.az.edadi.model.response.institution.InstitutionPageResponse;
import com.az.edadi.service.adapter.university.UniversityAdapter;
import com.az.edadi.service.service.NullFinder;
import com.az.edadi.dal.entity.institution.Institution;
import com.az.edadi.dal.repository.UniversityRepository;
import com.az.edadi.university.exception.UniversityNotFoundException;
import com.az.edadi.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversityAdapter universityAdapter;

    private final UniversityRepository universityRepository;
    private final MessageSource messageSource;
    @Override
    public InstitutionPageResponse createUniversity(UniversityRequestModel universityReq) {
        Institution university = universityAdapter.toEntity(universityReq);
        universityRepository.save(university);
        return universityAdapter.toResponse(university);
    }

    @Override
    public InstitutionPageResponse getUniversityById(String id) {
        Institution university = universityRepository.findById(id)
                .orElseThrow(() -> new UniversityNotFoundException(getMessage("university.not.found")));
        return universityAdapter.toResponse(university);
    }

    @Transactional
    @Override
    public InstitutionPageResponse update(String id, UniversityRequestModel universityReq) {
        Institution university = universityRepository.
                findById(id)
                .orElseThrow(()-> new UniversityNotFoundException(getMessage("university.not.found")));
        BeanUtils.copyProperties(university,universityReq, NullFinder.getNullFieldNames(universityReq));
        return universityAdapter.toResponse(universityRepository.save(university));
    }

    @Override
    public String delete(String id) {
        Institution university = universityRepository.
                findById(id)
                .orElseThrow(()-> new UniversityNotFoundException(getMessage("university.not.found")));
        universityRepository.deleteById(id);
        return university.getNameAz() + " is deleted";
    }


    @Override
    public List<InstitutionPageResponse> findAll() {
        return universityRepository.findAll()
                .stream()
                .map(universityAdapter::toResponse)
                .collect(Collectors.toList());
    }

    private String getMessage(String key){
        return messageSource.getMessage(key,null, Locale.getDefault());
    }

}
