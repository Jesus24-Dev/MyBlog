
package com.jesus24dev.myblog.logic.controllers;

import com.jesus24dev.myblog.persistence.models.User;
import com.jesus24dev.myblog.persistence.services.PersistenceServices;
import java.util.ArrayList;

public class UserController {
    PersistenceServices ps = new PersistenceServices();
    
    public void createUser(User user){
        ps.userServices.createUser(user);
    }
    
    public User getUser(String email){
       return ps.userServices.getUser(email);
    }
    
    public ArrayList<User> getUserList(){
        return (ArrayList) ps.userServices.getUserList();
    }
    
    public void editUser(User user){
        ps.userServices.editUser(user);
    }
    
    public void deleteUser(String email){
        ps.userServices.deleteUser(email);
    }
}
