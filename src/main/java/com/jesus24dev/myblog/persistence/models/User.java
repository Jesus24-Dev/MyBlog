
package com.jesus24dev.myblog.persistence.models;

import jakarta.persistence.Entity;
import java.util.Date;

@Entity
public class User {
    
    private String email;
    private String nickname;
    private String name;
    private String lastname;
    private Date birthday;
    private String gender;
    
}
