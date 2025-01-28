
package com.jesus24dev.myblog.persistence.services;

import com.jesus24dev.myblog.persistence.controllers.CommentJpaController;
import com.jesus24dev.myblog.persistence.controllers.exceptions.NonexistentEntityException;
import com.jesus24dev.myblog.persistence.models.Comment;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentServices {
    CommentJpaController commentJpaController = new CommentJpaController();
    
    public List<Comment> getCommentList(){
        return commentJpaController.findCommentEntities();
    }
    
    public Comment getComment(Long id){
        return commentJpaController.findComment(id);
    }
    
    public void createComment(Comment comment){
        try {
            commentJpaController.create(comment);
        } catch (Exception ex) {
            Logger.getLogger(CommentServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editComment(Comment comment){
        try {
            commentJpaController.edit(comment);
        } catch (Exception ex) {
            Logger.getLogger(CommentServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteComment(Long id){
        try {
            commentJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CommentServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
