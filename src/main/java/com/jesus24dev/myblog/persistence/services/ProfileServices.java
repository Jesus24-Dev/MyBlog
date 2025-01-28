
package com.jesus24dev.myblog.persistence.services;

import com.jesus24dev.myblog.persistence.controllers.ProfileJpaController;
import com.jesus24dev.myblog.persistence.controllers.exceptions.NonexistentEntityException;
import com.jesus24dev.myblog.persistence.models.Profile;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileServices {
    ProfileJpaController profileJpaController = new ProfileJpaController();
    
    public List<Profile> getProfileList(){
        return profileJpaController.findProfileEntities();
    }
    
    public Profile getProfile(Long id){
        return profileJpaController.findProfile(id);
    }
    
    public void createProfile(Profile profile){
        try {
            profileJpaController.create(profile);
        } catch (Exception ex) {
            Logger.getLogger(ProfileServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editProfile(Profile profile){
        try {
            profileJpaController.edit(profile);
        } catch (Exception ex) {
            Logger.getLogger(ProfileServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteProfile(Long id){
        try {
            profileJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProfileServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
