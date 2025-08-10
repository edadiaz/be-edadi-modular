package com.az.edadi.service.adapter.speciality;

import com.az.edadi.dal.entity.speciality.Speciality;
import com.az.edadi.model.request.speciality.SpecialityRequest;
import com.az.edadi.model.response.speciality.SpecialityPageResponse;
import org.springframework.stereotype.Component;

@Component
public class SpecialityAdapter {

    public SpecialityPageResponse mapToRes(Speciality speciality) {
        return new SpecialityPageResponse(
                speciality.getId(),
                speciality.getSpecialityCode(),
                speciality.getSpecialityName(),
                speciality.getInstitutionId(),
                speciality.getSpecialityGroup(),
                speciality.getSpecialitySubGroup(),
                speciality.getStudyLang(),
                speciality.getCity(),
                speciality.getStudyType(),
                speciality.getTuitionFee(),
                speciality.getSelfFundedPassingScore(),
                speciality.getStateFundedPassingScore(),
                speciality.getSpecialityUrl(),
                speciality.getCurrency()
        );
    }

    public SpecialityRequest mapToReq(Speciality speciality) {
        return new SpecialityRequest(
                speciality.getSpecialityCode(),
                speciality.getSpecialityName(),
                speciality.getInstitutionId(),
                speciality.getSpecialityGroup(),
                speciality.getSpecialitySubGroup(),
                speciality.getStudyLang(),
                speciality.getCity(),
                speciality.getStudyType(),
                speciality.getTuitionFee(),
                speciality.getSelfFundedPassingScore(),
                speciality.getStateFundedPassingScore(),
                speciality.getSpecialityUrl(),
                speciality.getCurrency()
        );
    }

    public Speciality mapToEnt(SpecialityPageResponse SpecialityResponse) {
        return Speciality.builder()
                .id(SpecialityResponse.getId())
                .specialityCode(SpecialityResponse.getSpecialityCode())
                .specialityName(SpecialityResponse.getSpecialityName())
                .institutionId(SpecialityResponse.getInstitutionId())
                .specialityGroup(SpecialityResponse.getSpecialityGroup())
                .specialitySubGroup(SpecialityResponse.getSpecialitySubGroup())
                .studyLang(SpecialityResponse.getStudyLang())
                .city(SpecialityResponse.getCity())
                .studyType(SpecialityResponse.getStudyType())
                .tuitionFee(SpecialityResponse.getTuitionFee())
                .selfFundedPassingScore(SpecialityResponse.getSelfFundedPassingScore())
                .stateFundedPassingScore(SpecialityResponse.getStateFundedPassingScore())
                .specialityUrl(SpecialityResponse.getSpecialityUrl())
                .currency(SpecialityResponse.getCurrency())
                .build();
    }

    public Speciality mapToEnt(SpecialityRequest SpecialityRequest) {
        return Speciality.builder()
                .specialityCode(SpecialityRequest.getSpecialityCode())
                .specialityName(SpecialityRequest.getSpecialityName())
                .institutionId(SpecialityRequest.getInstitutionId())
                .specialityGroup(SpecialityRequest.getSpecialityGroup())
                .specialitySubGroup(SpecialityRequest.getSpecialitySubGroup())
                .studyLang(SpecialityRequest.getStudyLang())
                .city(SpecialityRequest.getCity())
                .studyType(SpecialityRequest.getStudyType())
                .tuitionFee(SpecialityRequest.getTuitionFee())
                .selfFundedPassingScore(SpecialityRequest.getSelfFundedPassingScore())
                .stateFundedPassingScore(SpecialityRequest.getStateFundedPassingScore())
                .specialityUrl(SpecialityRequest.getSpecialityUrl())
                .currency(SpecialityRequest.getCurrency())
                .build();
    }
}
