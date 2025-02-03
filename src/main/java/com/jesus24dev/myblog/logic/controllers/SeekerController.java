
package com.jesus24dev.myblog.logic.controllers;

import com.jesus24dev.myblog.persistence.services.PersistenceServices;
import java.util.ArrayList;

public class SeekerController {
    PersistenceServices ps = new PersistenceServices();
    
    public ArrayList<Long> getPostId(String text){
        ArrayList<Long> idList = new ArrayList(ps.seekerController.getPostId(text));
        
        return idList;
    }
    
    public ArrayList<Long> getProfileId(String text){
        ArrayList<Long> idList = new ArrayList(ps.seekerController.getProfileId(text));
        
        return idList;
    }
}
