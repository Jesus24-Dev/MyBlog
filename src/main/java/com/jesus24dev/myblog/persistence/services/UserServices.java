
package com.jesus24dev.myblog.persistence.services;

import com.jesus24dev.myblog.persistence.controllers.UserJpaController;
import com.jesus24dev.myblog.persistence.controllers.exceptions.NonexistentEntityException;
import com.jesus24dev.myblog.persistence.controllers.exceptions.PreexistingEntityException;
import com.jesus24dev.myblog.persistence.models.User;
import jakarta.persistence.PersistenceException;
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
    
    public User getUserByNickname(String nickname) {
        List<User> users = userJpaController.findUserEntities();
        for (User user : users) {
            if (user.getNickname().equals(nickname)) {
                return user; 
            }
        }
        return null; 
    }
    
    public void createUser(User user) throws PreexistingEntityException{
        if (getUser(user.getEmail()) != null) {
            throw new PreexistingEntityException("Email already registered.");
        }
        if (getUserByNickname(user.getNickname()) != null) {
            throw new PreexistingEntityException("Nickname already exists.");
        }

        try {
            userJpaController.create(user);
        } catch (Exception ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Error while persisting user.");
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
