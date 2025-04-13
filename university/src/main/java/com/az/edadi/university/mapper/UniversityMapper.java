package com.az.edadi.university.mapper;

import com.az.edadi.dal.entity.institution.Institution;
import com.az.edadi.university.model.request.UniversityReq;
import com.az.edadi.university.model.response.UniversityRes;


public class UniversityMapper {
    public static UniversityRes toResponse(Institution university) {
        UniversityRes universityRes = new UniversityRes();
        universityRes.setId(university.getId());
        universityRes.setAbbrAz(university.getAbbrAz());
        universityRes.setAbbrEn(university.getAbbrEn());
        universityRes.setNameAz(university.getNameAz());
        universityRes.setNameEn(university.getNameEn());
        universityRes.setDescription(university.getDescription());
        universityRes.setWebSiteUrl(university.getWebSiteUrl());
        universityRes.setPhotoUrl(university.getPhotoUrl());
        universityRes.setType(university.getType());
        universityRes.setCountOfStudent(university.getCountOfStudent());
        universityRes.setLocation(university.getLocation());
        universityRes.setFoundedYear(university.getFoundedYear());
        return universityRes;

    }


    public static UniversityReq toRequest(Institution university) {
        UniversityReq universityReq = new UniversityReq();
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

    ;

    public static Institution toEntity(UniversityRes universityRes) {
        Institution university = new Institution();
        university.setNameAz(universityRes.getNameAz());
        university.setNameEn(universityRes.getNameEn());
        university.setAbbrAz(universityRes.getAbbrAz());
        university.setAbbrEn(universityRes.getAbbrEn());
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

    public static Institution toEntity(UniversityReq universityReq) {
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

    ;
}
