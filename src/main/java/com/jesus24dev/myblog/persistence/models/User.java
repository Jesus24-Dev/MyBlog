
package com.jesus24dev.myblog.persistence.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class User implements Serializable {
    
    @Id
    private String email;
    
    @Column(unique = true)
    private String nickname;
    
    private String name;
    private String lastname;
    private String password;
    private LocalDate birthday;
    private String gender;
    
    @OneToOne
    private Profile profile;

    public User() {
    }

    public User(String email, String nickname, String name, String lastname, String password, LocalDate birthday, String gender, Profile profile) {
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.profile = profile;
    }
   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    
}
