package com.az.edadi.speciality.service.impl;

import com.az.edadi.model.response.speciality.SpecialityPageResponse;
import com.az.edadi.service.service.NullFinder;
import com.az.edadi.dal.entity.speciality.Speciality;
import com.az.edadi.dal.repository.SpecialityRepository;
import com.az.edadi.service.adapter.speciality.SpecialityAdapter;
import com.az.edadi.model.request.speciality.SpecialityRequest;
import com.az.edadi.speciality.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialityServiceImpl implements SpecialityService {
    private final SpecialityAdapter specialityAdapter;
    private final SpecialityRepository specialityRepository;
    @Override
    public SpecialityPageResponse create(SpecialityRequest specialityReq) {
        Speciality speciality = specialityAdapter.mapToEnt(specialityReq);
        specialityRepository.save(speciality);
        return specialityAdapter.mapToRes(speciality);
    }

    @Override
    public SpecialityPageResponse getSpecialityById(UUID id) {
        Speciality speciality = specialityRepository.findById(id).orElseThrow();
        return specialityAdapter.mapToRes(speciality);
    }

    @Override
    public SpecialityPageResponse update(UUID id, SpecialityRequest specialityReq) {
        Speciality speciality = specialityRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(speciality,specialityReq,NullFinder.getNullFieldNames(specialityReq));
        return specialityAdapter.mapToRes(speciality);
    }

    @Override
    public String delete(UUID id) {
        Speciality speciality = specialityRepository.findById(id).orElseThrow();
        specialityRepository.deleteById(id);
        return speciality.getSpecialityName() + " is deleted";
    }

    @Override
    public List<SpecialityPageResponse> findAll() {
        return specialityRepository.findAll()
                .stream()
                .map(specialityAdapter::mapToRes).collect(Collectors.toList());
    }

    @Override
    public List<SpecialityPageResponse> findByInstitutionId(String id) {
        return specialityRepository.findByInstitutionId(id)
                .stream()
                .map(specialityAdapter::mapToRes)
                .collect(Collectors.toList());
    }
}
