
package com.jesus24dev.myblog.logic.controllers;

import com.jesus24dev.myblog.persistence.models.Post;
import com.jesus24dev.myblog.persistence.services.PersistenceServices;
import java.util.ArrayList;

public class PostController {
        PersistenceServices ps = new PersistenceServices();
    
    public void createPost(Post post){
        ps.postServices.createPost(post);
    }
    
    public Post getPost(Long id){
       return ps.postServices.getPost(id);
    }
    
    public ArrayList<Post> getPostList(){
        return (ArrayList) ps.postServices.getPostList();
    }
    
    public void editPost(Post post){
        ps.postServices.editPost(post);
    }
    
    public void deletePost(Long id){
        ps.postServices.deletePost(id);
    }
}
