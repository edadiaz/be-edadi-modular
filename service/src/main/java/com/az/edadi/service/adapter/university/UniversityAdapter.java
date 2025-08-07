package com.az.edadi.service.adapter.university;

import com.az.edadi.dal.entity.institution.Institution;
import com.az.edadi.model.request.UniversityRequestModel;
import com.az.edadi.model.response.institution.InstitutionPageResponse;
import com.az.edadi.model.response.institution.InstitutionSummaryResponse;
import com.az.edadi.service.service.Translator;
import org.springframework.stereotype.Service;

@Service
public class UniversityAdapter {
    public InstitutionPageResponse toResponse(Institution university) {
        InstitutionPageResponse universityRes = new InstitutionPageResponse();
        universityRes.setId(university.getId());
        //todo add localization to abbr and name
        universityRes.setAbbr(university.getAbbrAz());
        universityRes.setName(university.getNameAz());
        universityRes.setDescription(university.getDescription());
        universityRes.setWebSiteUrl(university.getWebSiteUrl());
        universityRes.setPhotoUrl(university.getPhotoUrl());
        universityRes.setType(university.getType());
        universityRes.setCountOfStudent(university.getCountOfStudent());
        universityRes.setLocation(university.getLocation());
        universityRes.setFoundedYear(university.getFoundedYear());
        return universityRes;

    }


    public static UniversityRequestModel toRequest(Institution university) {
        UniversityRequestModel universityReq = new UniversityRequestModel();
        universityReq.setAbbrAz(university.getAbbrAz());
        universityReq.setAbbrEn(university.getAbbrEn());
        universityReq.setNameAz(university.getNameAz());
        universityReq.setNameEn(university.getNameEn());
        universityReq.setDescription(university.getDescription());
        universityReq.setWebSiteUrl(university.getWebSiteUrl());
        universityReq.setPhotoUrl(university.getPhotoUrl());
        universityReq.setType(university.getType());
        universityReq.setCountOfStudent(university.getCountOfStudent());
        universityReq.setLocation(university.getLocation());
        universityReq.setFoundedYear(university.getFoundedYear());
        return universityReq;

    }


    //todo fix this
    public static Institution toEntity(InstitutionPageResponse universityRes) {
        Institution university = new Institution();
        university.setNameAz(universityRes.getName());
        university.setNameEn(universityRes.getName());
        university.setAbbrAz(universityRes.getAbbr());
        university.setAbbrEn(universityRes.getAbbr());
        university.setDescription(universityRes.getDescription());
        university.setCountOfStudent(universityRes.getCountOfStudent());
        university.setFoundedYear(universityRes.getFoundedYear());
        university.setType(universityRes.getType());
        university.setLocation(universityRes.getLocation());
        university.setWebSiteUrl(universityRes.getWebSiteUrl());
        university.setPhotoUrl(universityRes.getPhotoUrl());
        return university;
    }

    ;

    public  Institution toEntity(UniversityRequestModel universityReq) {
        Institution university = new Institution();
        university.setNameAz(universityReq.getNameAz());
        university.setNameEn(universityReq.getNameEn());
        university.setAbbrAz(universityReq.getAbbrAz());
        university.setAbbrEn(universityReq.getAbbrEn());
        university.setDescription(universityReq.getDescription());
        university.setCountOfStudent(universityReq.getCountOfStudent());
        university.setFoundedYear(universityReq.getFoundedYear());
        university.setType(universityReq.getType());
        university.setLocation(universityReq.getLocation());
        university.setWebSiteUrl(universityReq.getWebSiteUrl());
        university.setPhotoUrl(universityReq.getPhotoUrl());
        return university;
    }

    InstitutionSummaryResponse toSummaryResponse(Institution institution) {
        InstitutionSummaryResponse response = new InstitutionSummaryResponse();
        response.setAbbr(getAbbr(institution));
        response.setAbbr(getName(institution));
        response.setId(institution.getId());
        response.setPhotoUrl(institution.getPhotoUrl());
        return response;
    }

    private String getAbbr(Institution institution) {
        var currentLang = Translator.getCurrentLanguage();
      return  switch (currentLang){
            case "en" -> institution.getAbbrEn();
            case "az" -> institution.getAbbrAz();
          default ->  institution.getAbbrEn();
        };
    }

    private String getName(Institution institution) {
        var currentLang = Translator.getCurrentLanguage();
        return  switch (currentLang){
            case "en" -> institution.getNameEn();
            case "az" -> institution.getNameAz();
            default ->  institution.getAbbrEn();
        };
    }
}
