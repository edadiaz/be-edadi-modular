package com.az.edadi.dal.entity.university;

import jakarta.persistence.*;

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

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", abbrAz='" + abbrAz + '\'' +
                ", abbrEn='" + abbrEn + '\'' +
                ", nameAz='" + nameAz + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAbbrAz() {
        return abbrAz;
    }

    public void setAbbrAz(String abbrAz) {
        this.abbrAz = abbrAz;
    }

    public String getAbbrEn() {
        return abbrEn;
    }

    public void setAbbrEn(String abbrEn) {
        this.abbrEn = abbrEn;
    }

    public String getNameAz() {
        return nameAz;
    }

    public void setNameAz(String nameAz) {
        this.nameAz = nameAz;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


}
