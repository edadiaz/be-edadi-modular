package com.az.edadi.dal.entity.institution;

import com.az.edadi.dal.types.InstitutionType;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "institution")
public class Institution {
    @Id
    private String id;
    private InstitutionType institutionType;
    private String abbrAz;
    private String abbrEn;
    private String nameAz;
    private String nameEn;
    private String photoUrl;
    private Integer foundedYear;
    private String webSiteUrl;
    private Integer countOfStudent;
    private String  type;
    private String description;
    private String location;

}
