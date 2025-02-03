
package com.jesus24dev.myblog.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Profile implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String biography;
    private byte[] profilePicture;
    
    @OneToMany(mappedBy="profile")
    private List<Post> userPosts;
    
    @OneToMany(mappedBy="profile")
    private List<Comment> userComments;

    public Profile() {
    }

    public Profile(String biography, byte[] profilePicture, List<Post> userPosts, List<Comment> userComments) {
        this.biography = biography;
        this.profilePicture = profilePicture;
        this.userPosts = userPosts;
        this.userComments = userComments;
    }

    public Long getId() {
        return id;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public List<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }
    
    
}
