package com.az.edadi.service.adapter.university;

import com.az.edadi.dal.entity.institution.Institution;
import com.az.edadi.model.request.UniversityRequestModel;
import com.az.edadi.model.response.university.UniversityPageResponse;
import org.springframework.stereotype.Service;

@Service
public class UniversityAdapter {
    public UniversityPageResponse toResponse(Institution university) {
        UniversityPageResponse universityRes = new UniversityPageResponse();
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
    public static Institution toEntity(UniversityPageResponse universityRes) {
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
}
