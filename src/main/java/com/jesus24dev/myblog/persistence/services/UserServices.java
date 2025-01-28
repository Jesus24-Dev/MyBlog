
package com.jesus24dev.myblog.persistence.services;

import com.jesus24dev.myblog.persistence.controllers.UserJpaController;
import com.jesus24dev.myblog.persistence.controllers.exceptions.NonexistentEntityException;
import com.jesus24dev.myblog.persistence.models.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServices {
    UserJpaController userJpaController = new UserJpaController();
    
    public List<User> getUserList(){
        return userJpaController.findUserEntities();
    }
    
    public User getUser(String id){
        return userJpaController.findUser(id);
    }
    
    public void createUser(User user){
        try {
            userJpaController.create(user);
        } catch (Exception ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editUser(User user){
        try {
            userJpaController.edit(user);
        } catch (Exception ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteUser(String id){
        try {
            userJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
