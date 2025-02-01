
package com.jesus24dev.myblog.persistence.services;

import com.jesus24dev.myblog.persistence.controllers.SeekerController;
import java.util.ArrayList;
import java.util.List;

public class SeekerServices {
    SeekerController seekerController = new SeekerController();
    
    public ArrayList<Long> getPostId(String text){
        ArrayList<Long> idList = new ArrayList(seekerController.getPostId(text));
        
        return idList;
    }
    
    public ArrayList<String> getUsersEmail(String text){
        ArrayList<String> emailList = new ArrayList(seekerController.getUsersEmail(text));
        
        return emailList;
    }
}
