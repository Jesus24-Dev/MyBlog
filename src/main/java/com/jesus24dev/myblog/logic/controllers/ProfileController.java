
package com.jesus24dev.myblog.logic.controllers;

import com.jesus24dev.myblog.persistence.models.Profile;
import com.jesus24dev.myblog.persistence.services.PersistenceServices;
import java.util.ArrayList;

public class ProfileController {
    PersistenceServices ps = new PersistenceServices();
    
    public void createProfile(Profile profile){
        ps.profileServices.createProfile(profile);
    }
    
    public Profile getProfile(Long id){
       return ps.profileServices.getProfile(id);
    }
    
    public ArrayList<Profile> getProfileList(){
        return (ArrayList) ps.profileServices.getProfileList();
    }
    
    public void editProfile(Profile profile){
        ps.profileServices.editProfile(profile);
    }
    
    public void deleteProfile(Long id){
        ps.profileServices.deleteProfile(id);
    }
}
