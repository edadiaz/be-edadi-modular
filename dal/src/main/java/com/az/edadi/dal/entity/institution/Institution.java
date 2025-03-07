package com.az.edadi.dal.entity.institution;

import com.az.edadi.dal.types.InstitutionType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "institution")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
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

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", abbrAz='" + abbrAz + '\'' +
                ", abbrEn='" + abbrEn + '\'' +
                ", nameAz='" + nameAz + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", foundedYear=" + foundedYear +
                ", webSiteUrl='" + webSiteUrl + '\'' +
                ", countOfStudent=" + countOfStudent +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }


}
