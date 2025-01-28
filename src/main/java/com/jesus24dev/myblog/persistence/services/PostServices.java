
package com.jesus24dev.myblog.persistence.services;

import com.jesus24dev.myblog.persistence.controllers.PostJpaController;
import com.jesus24dev.myblog.persistence.controllers.exceptions.NonexistentEntityException;
import com.jesus24dev.myblog.persistence.models.Post;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostServices {
    PostJpaController postJpaController = new PostJpaController();
    
    public List<Post> getPostList(){
        return postJpaController.findPostEntities();
    }
    
    public Post getPost(Long id){
        return postJpaController.findPost(id);
    }
    
    public void createPost(Post post){
        try {
            postJpaController.create(post);
        } catch (Exception ex) {
            Logger.getLogger(PostServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editPost(Post post){
        try {
            postJpaController.edit(post);
        } catch (Exception ex) {
            Logger.getLogger(PostServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletePost(Long id){
        try {
            postJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PostServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
