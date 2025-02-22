package com.az.edadi.dal.entity.university;

import com.az.edadi.dal.constants.UniversityType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

@Entity
@Table(name = "university")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
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

    public UUID getId() {
        return id;
    }

    public String getAbbrAz() {
        return abbrAz;
    }

    public String getAbbrEn() {
        return abbrEn;
    }

    public String getNameAz() {
        return nameAz;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public Integer getCountOfStudent() {
        return countOfStudent;
    }

    public String  getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAbbrAz(String abbrAz) {
        this.abbrAz = abbrAz;
    }

    public void setAbbrEn(String abbrEn) {
        this.abbrEn = abbrEn;
    }

    public void setNameAz(String nameAz) {
        this.nameAz = nameAz;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }

    public void setCountOfStudent(Integer countOfStudent) {
        this.countOfStudent = countOfStudent;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
