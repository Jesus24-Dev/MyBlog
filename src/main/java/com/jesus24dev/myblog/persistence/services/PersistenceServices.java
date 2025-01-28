
package com.jesus24dev.myblog.persistence.services;

public class PersistenceServices {
    public UserServices userServices = new UserServices();
    public ProfileServices profileServices = new ProfileServices();
    public PostServices postServices = new PostServices();
    public CommentServices commentServices = new CommentServices();

    public PersistenceServices() {
    }
    
    
}
