package com.az.edadi.speciality.adapter;

import com.az.edadi.dal.entity.speciality.Speciality;
import com.az.edadi.speciality.model.SpecialityReq;
import com.az.edadi.speciality.model.SpecialityRes;
import org.springframework.stereotype.Component;

@Component
public class SpecialityAdapter {

    public SpecialityRes mapToRes(Speciality speciality) {
        return new SpecialityRes(
                speciality.getSpecialityCode(),
                speciality.getSpecialityName(),
                speciality.getInstitutionId(),
                speciality.getSpecialityGroup(),
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
                .specialityCode(specialityRes.getSpecialityCode())
                .specialityName(specialityRes.getSpecialityName())
                .specialityGroup(specialityRes.getSpecialityGroup())
                .studyLang(specialityRes.getStudyLang())
                .city(specialityRes.getCity())
                .studyType(specialityRes.getStudyType())
                .tuitionFee(specialityRes.getTuitionFee())
                .selfFundedPassingScore(specialityRes.getSelfFundedPassingScore())
                .stateFundedPassingScore(specialityRes.getStateFundedPassingScore())
                .specialityUrl(specialityRes.getSpecialityUrl())
                .currency(specialityRes.getCurrency())
                .institutionId(specialityRes.getInstitutionId())
                .build();
    }

    public Speciality mapToEnt(SpecialityReq specialityReq) {
        return Speciality.builder()
                .specialityName(specialityReq.getSpecialityName())
                .specialityGroup(specialityReq.getSpecialityGroup())
                .selfFundedPassingScore(specialityReq.getSelfFundedPassingScore())
                .stateFundedPassingScore(specialityReq.getStateFundedPassingScore())
                .tuitionFee(specialityReq.getTuitionFee())
                .currency(specialityReq.getCurrency())
                .institutionId(specialityReq.getInstitutionId())
                .specialityCode(specialityReq.getSpecialityCode())
                .studyLang(specialityReq.getStudyLang())
                .city(specialityReq.getCity())
                .studyType(specialityReq.getStudyType())
                .specialityUrl(specialityReq.getSpecialityUrl())
                .build();
    }

}
