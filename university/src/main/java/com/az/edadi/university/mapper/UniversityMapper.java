package com.az.edadi.university.mapper;

import com.az.edadi.dal.entity.university.University;
import com.az.edadi.university.model.request.UniversityReq;
import com.az.edadi.university.model.response.UniversityRes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UniversityMapper {
    UniversityMapper INSTANCE = Mappers.getMapper(UniversityMapper.class);
    UniversityRes toResponse(University university);
    UniversityReq toRequest(University university);
    University toEntity(UniversityRes universityRes);
    University toEntity(UniversityReq universityReq);
}
