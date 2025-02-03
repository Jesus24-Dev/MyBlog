
package com.jesus24dev.myblog.logic.utils;

import com.jesus24dev.myblog.persistence.models.Comment;
import com.jesus24dev.myblog.persistence.models.Post;
import com.jesus24dev.myblog.persistence.models.User;
import com.jesus24dev.myblog.persistence.services.PersistenceServices;
import java.util.ArrayList;
import java.util.List;

public class HomeFunctions {
    private static PersistenceServices ps = new PersistenceServices();
    
    public static ArrayList<Post> getPostList(){
        List<Post> postList = ps.postServices.getPostList();
        return new ArrayList(postList);
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
    
    public static String getProfileFulllName(Long id){
        ArrayList<User> userList = new ArrayList(ps.userServices.getUserList());
        String username = "";
        for(User u : userList){
            if(u.getProfile().getId() == id){
                username = u.getName() + " " + u.getLastname();
                break;
            }
        }
        return username;
    }
}
