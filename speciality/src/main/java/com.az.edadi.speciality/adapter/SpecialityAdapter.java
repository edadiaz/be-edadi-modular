package com.az.edadi.speciality.adapter;

import com.az.edadi.dal.entity.speciality.Speciality;
import com.az.edadi.speciality.model.SpecialityReq;
import com.az.edadi.speciality.model.SpecialityRes;
import org.springframework.stereotype.Component;

@Component
public class SpecialityAdapter {

    public SpecialityRes mapToRes(Speciality speciality) {
        return new SpecialityRes(
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

    public SpecialityReq mapToReq(Speciality speciality) {
        return new SpecialityReq(
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

    public Speciality mapToEnt(SpecialityRes specialityRes) {
        return Speciality.builder()
                .id(specialityRes.getId())
                .specialityCode(specialityRes.getSpecialityCode())
                .specialityName(specialityRes.getSpecialityName())
                .institutionId(specialityRes.getInstitutionId())
                .specialityGroup(specialityRes.getSpecialityGroup())
                .specialitySubGroup(specialityRes.getSpecialitySubGroup())
                .studyLang(specialityRes.getStudyLang())
                .city(specialityRes.getCity())
                .studyType(specialityRes.getStudyType())
                .tuitionFee(specialityRes.getTuitionFee())
                .selfFundedPassingScore(specialityRes.getSelfFundedPassingScore())
                .stateFundedPassingScore(specialityRes.getStateFundedPassingScore())
                .specialityUrl(specialityRes.getSpecialityUrl())
                .currency(specialityRes.getCurrency())
                .build();
    }

    public Speciality mapToEnt(SpecialityReq specialityReq) {
        return Speciality.builder()
                .specialityCode(specialityReq.getSpecialityCode())
                .specialityName(specialityReq.getSpecialityName())
                .institutionId(specialityReq.getInstitutionId())
                .specialityGroup(specialityReq.getSpecialityGroup())
                .specialitySubGroup(specialityReq.getSpecialitySubGroup())
                .studyLang(specialityReq.getStudyLang())
                .city(specialityReq.getCity())
                .studyType(specialityReq.getStudyType())
                .tuitionFee(specialityReq.getTuitionFee())
                .selfFundedPassingScore(specialityReq.getSelfFundedPassingScore())
                .stateFundedPassingScore(specialityReq.getStateFundedPassingScore())
                .specialityUrl(specialityReq.getSpecialityUrl())
                .currency(specialityReq.getCurrency())
                .build();
    }
}
