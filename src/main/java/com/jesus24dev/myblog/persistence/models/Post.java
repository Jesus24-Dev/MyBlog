
package com.jesus24dev.myblog.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Date publishedAt;
    
    @ManyToOne
    private Profile profile;
    
    @OneToMany(mappedBy="post")
    private List<Comment> userComments;

    public Post() {
    }

    public Post(Long id, String description, Date publishedAt, Profile profile, List<Comment> userComments) {
        this.id = id;
        this.description = description;
        this.publishedAt = publishedAt;
        this.profile = profile;
        this.userComments = userComments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }
               
}
