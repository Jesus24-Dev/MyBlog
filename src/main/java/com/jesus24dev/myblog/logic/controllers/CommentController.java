
package com.jesus24dev.myblog.logic.controllers;

import com.jesus24dev.myblog.persistence.models.Comment;
import com.jesus24dev.myblog.persistence.services.PersistenceServices;
import java.util.ArrayList;

public class CommentController {
        PersistenceServices ps = new PersistenceServices();
    
    public void createComment(Comment comment){
        ps.commentServices.createComment(comment);
    }
    
    public Comment getComment(Long id){
       return ps.commentServices.getComment(id);
    }
    
    public ArrayList<Comment> getCommentList(){
        return (ArrayList) ps.commentServices.getCommentList();
    }
    
    public void editComment(Comment comment){
        ps.commentServices.editComment(comment);
    }
    
    public void deleteComment(Long id){
        ps.commentServices.deleteComment(id);
    }
}
