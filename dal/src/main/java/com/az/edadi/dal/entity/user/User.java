package com.az.edadi.dal.entity.user;

import com.az.edadi.dal.types.AcademicDegree;
import com.az.edadi.dal.types.Gender;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Data
@Document(collection = "user")
public class User  {
    @Id
    private String id;
    private String username;
    private String email;
    private String name;
    private String password;
    private Gender gender;
    private LocalDate birthday;
    private AcademicDegree academicDegree;
    private String universityId;
    private String specialityId;

}
