package com.az.edadi.speciality.service;

import com.az.edadi.speciality.model.SpecialityReq;
import com.az.edadi.speciality.model.SpecialityRes;

import java.util.List;
import java.util.UUID;

public interface SpecialityService {
    SpecialityRes create(SpecialityReq specialityReq);
    SpecialityRes getSpecialityById(UUID id);
    SpecialityRes update(UUID id, SpecialityReq specialityReq);
    String delete(UUID id);
    List<SpecialityRes> findAll();
    List<SpecialityRes> findByInstitutionId(String id);


}
