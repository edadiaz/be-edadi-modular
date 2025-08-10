package com.az.edadi.speciality.service;

import com.az.edadi.model.request.speciality.SpecialityRequest;
import com.az.edadi.model.response.speciality.SpecialityPageResponse;
import java.util.List;
import java.util.UUID;

public interface SpecialityService {
    SpecialityPageResponse create(SpecialityRequest specialityReq);
    SpecialityPageResponse getSpecialityById(UUID id);
    SpecialityPageResponse update(UUID id, SpecialityRequest specialityReq);
    String delete(UUID id);
    List<SpecialityPageResponse> findAll();
    List<SpecialityPageResponse> findByInstitutionId(String id);


}
