
package com.jesus24dev.myblog.logic.utils;

import com.jesus24dev.myblog.persistence.models.Comment;
import com.jesus24dev.myblog.persistence.models.Post;
import com.jesus24dev.myblog.persistence.models.User;
import com.jesus24dev.myblog.persistence.services.PersistenceServices;
import java.util.ArrayList;
import java.util.List;

public class CommentFunctions {
    private static PersistenceServices ps = new PersistenceServices();
    
    public static ArrayList<Comment> getCommentList(Long id){       
        Post post = ps.postServices.getPost(id);
        List<Comment> commentList = post.getUserComments();
        return new ArrayList(commentList);
    }
    
    public static String getProfileName(Long id){
        ArrayList<User> userList = new ArrayList(ps.userServices.getUserList());
        String username = "";
        for(User u : userList){
            if(u.getProfile().getId() == id){
                username = u.getNickname();
                break;
            }
        }
        return username;
    }
    
    public static Post getPostInfo(Long id){
        return ps.postServices.getPost(id);
    }
}
