package com.az.edadi.dal.convertor;

import com.az.edadi.dal.types.AcademicDegree;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AcademicDegreeConverter implements AttributeConverter<AcademicDegree, String> {

    @Override
    public String convertToDatabaseColumn(AcademicDegree attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public AcademicDegree convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return AcademicDegree.valueOf(dbData);
    }

}
