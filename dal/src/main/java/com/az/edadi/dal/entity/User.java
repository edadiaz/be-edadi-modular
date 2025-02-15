package com.az.edadi.dal.entity;

import com.az.edadi.dal.convertor.AcademicDegreeConverter;
import com.az.edadi.dal.entity.post.Post;
import com.az.edadi.dal.types.AcademicDegree;
import com.az.edadi.dal.types.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class User extends BaseEntity {
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    private LocalDate birthday;


    private AcademicDegree academic_degree;
    private UUID university_id;
    private UUID speciality_id;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    private List<Post> postList;

    public User() {
    }

    public User(String username, String email, String name, List<Post> postList) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.postList = postList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getName(), user.getName()) && Objects.equals(getPostList(), user.getPostList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUsername(), getEmail(), getName(), getPostList());
    }

}
