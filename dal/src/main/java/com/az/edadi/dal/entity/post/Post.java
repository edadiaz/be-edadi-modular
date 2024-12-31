package com.az.edadi.dal.entity.post;

import com.az.edadi.dal.entity.BaseEntity;
import com.az.edadi.dal.entity.User;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "post")
public class Post extends BaseEntity {
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getText(), post.getText()) && Objects.equals(getUser(), post.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getText(), getUser());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
