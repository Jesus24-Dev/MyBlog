
package com.jesus24dev.myblog.logic.utils;

import com.jesus24dev.myblog.persistence.models.Post;
import com.jesus24dev.myblog.persistence.models.Profile;
import com.jesus24dev.myblog.persistence.models.User;
import com.jesus24dev.myblog.persistence.services.PersistenceServices;
import java.util.ArrayList;
import java.util.List;

public class ProfileFunctions {
    private static PersistenceServices ps = new PersistenceServices();
    
    public static ArrayList<Post> getPostList(Long id){
        Profile profile = ps.profileServices.getProfile(id);
        List<Post> postList = profile.getUserPosts();
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
    
    public static Profile profileToSee(Long id){
        return ps.profileServices.getProfile(id);
    }
}
