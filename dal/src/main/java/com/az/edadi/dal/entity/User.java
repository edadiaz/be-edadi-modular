package com.az.edadi.dal.entity;

import com.az.edadi.dal.entity.post.Post;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "\"user\"")
public class User extends BaseEntity{
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
