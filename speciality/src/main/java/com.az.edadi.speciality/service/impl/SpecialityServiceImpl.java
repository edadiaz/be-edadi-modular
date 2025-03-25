package com.az.edadi.speciality.service.impl;

import com.az.edadi.common_service.service.NullFinder;
import com.az.edadi.dal.entity.speciality.Speciality;
import com.az.edadi.dal.repository.SpecialityRepository;
import com.az.edadi.speciality.adapter.SpecialityAdapter;
import com.az.edadi.speciality.model.SpecialityReq;
import com.az.edadi.speciality.model.SpecialityRes;
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
    public SpecialityRes create(SpecialityReq specialityReq) {
        Speciality speciality = specialityAdapter.mapToEnt(specialityReq);
        specialityRepository.save(speciality);
        return specialityAdapter.mapToRes(speciality);
    }

    @Override
    public SpecialityRes getSpecialityById(UUID id) {
        Speciality speciality = specialityRepository.findById(id).orElseThrow();
        return specialityAdapter.mapToRes(speciality);
    }

    @Override
    public SpecialityRes update(UUID id, SpecialityReq specialityReq) {
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
    public List<SpecialityRes> findAll() {
        return specialityRepository.findAll()
                .stream()
                .map(specialityAdapter::mapToRes).collect(Collectors.toList());
    }

    @Override
    public List<SpecialityRes> findByInstitutionId(String id) {
        return specialityRepository.findByInstitutionId(id)
                .stream()
                .map(specialityAdapter::mapToRes)
                .collect(Collectors.toList());
    }
}
