
package database;

import com.jesus24dev.myblog.persistence.controllers.exceptions.PreexistingEntityException;
import com.jesus24dev.myblog.persistence.models.Comment;
import com.jesus24dev.myblog.persistence.models.Post;
import com.jesus24dev.myblog.persistence.models.Profile;
import com.jesus24dev.myblog.persistence.models.User;
import com.jesus24dev.myblog.persistence.services.PersistenceServices;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class DatabaseTest {
    public static void main(String[] args) throws PreexistingEntityException {
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
        
        LocalDate datePost = LocalDate.now();
        post.setDescription("This is a new post");
        post.setPublishedAt(datePost);
        post.setProfile(profile);
        ps.postServices.createPost(post);
        
        postList.add(post);
                
        //Create empty comment's list
        List<Comment> commentList = new ArrayList<>();
                                      
        //Create new comment
        LocalDate dateComment = LocalDate.now();
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
        LocalDate date = LocalDate.of(2004, 4, 24);
        User user = new User("subjecttest@gmail.com", "JohnDoe123", "John", "Doe","123456", date, "Male", profile);        
        
        //Add user in database       
        ps.userServices.createUser(user);
    }
}
