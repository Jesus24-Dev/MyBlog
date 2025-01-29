
package database;

import com.jesus24dev.myblog.persistence.models.Comment;
import com.jesus24dev.myblog.persistence.models.Post;
import com.jesus24dev.myblog.persistence.models.Profile;
import com.jesus24dev.myblog.persistence.models.User;
import com.jesus24dev.myblog.persistence.services.PersistenceServices;
import com.jesus24dev.myblog.persistence.services.UserServices;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseTest {
    public static void main(String[] args) {
        //Create new instance of PersistenceServices
        PersistenceServices ps = new PersistenceServices(); 
        
        //Create an empty instance from Profile
        Profile profile = new Profile();    
        profile.setBiography("This is my profile biography");
        profile.setProfilePicture(null);
        ps.profileServices.createProfile(profile);
                               
        //Create an empty instance from Post
        Post post = new Post();
        
        //Create empty post's list
        List<Post> postList = new ArrayList<>();
        
        Date datePost = new Date();
        post.setDescription("This is a new post");
        post.setPublishedAt(datePost);
        post.setProfile(profile);
        ps.postServices.createPost(post);
        
        postList.add(post);
                
        //Create empty comment's list
        List<Comment> commentList = new ArrayList<>();
                                      
        //Create new comment
        Date dateComment = new Date();
        Comment comment = new Comment("This is a new comment", dateComment, profile, post);
        ps.commentServices.createComment(comment);
        
        //Add comment in comment's list
        commentList.add(comment);
                
        //Add comments list in Post
        post.setUserComments(commentList);
        ps.postServices.editPost(post);
        
        //Add comments list and posts list in Profile        
        profile.setUserComments(commentList);
        profile.setUserPosts(postList);  
        ps.profileServices.editProfile(profile);
        
        //Create new User instance
        Date date = new Date();
        User user = new User("subjecttest@gmail.com", "JohnDoe123", "John", "Doe", date, "Male", profile);        
        
        //Add user in database       
        ps.userServices.createUser(user);
    }
}
