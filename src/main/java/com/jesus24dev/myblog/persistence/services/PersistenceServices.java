
package com.jesus24dev.myblog.persistence.services;

import com.jesus24dev.myblog.persistence.controllers.SeekerController;

public class PersistenceServices {
    public UserServices userServices = new UserServices();
    public ProfileServices profileServices = new ProfileServices();
    public PostServices postServices = new PostServices();
    public CommentServices commentServices = new CommentServices();
    public SeekerController seekerController = new SeekerController();

    public PersistenceServices() {
    }
    
    
}
