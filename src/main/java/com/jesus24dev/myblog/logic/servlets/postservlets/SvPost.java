
package com.jesus24dev.myblog.logic.servlets.postservlets;

import com.jesus24dev.myblog.logic.controllers.PostController;
import com.jesus24dev.myblog.logic.controllers.SeekerController;
import com.jesus24dev.myblog.persistence.models.Post;
import com.jesus24dev.myblog.persistence.models.Profile;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "SvPost", urlPatterns = {"/SvPost"})
public class SvPost extends HttpServlet{
    PostController postController = new PostController();
    SeekerController seekerController = new SeekerController();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String text = request.getParameter("text");
        
        if (text.trim() != ""){
            ArrayList<Long> postId = seekerController.getPostId(text);
            ArrayList<Post> postList = new ArrayList();
        
            for(Long id: postId){
                Post post = postController.getPost(id);
                postList.add(post);
            }
            request.setAttribute("postList", postList);
        } else {
            request.setAttribute("postList", null);
        }
                             
        request.getRequestDispatcher("searchPost.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        HttpSession session = request.getSession();
        String descriptionPost = request.getParameter("descriptionPost");
        Profile profile = (Profile) session.getAttribute("profile");
        
        Post post = new Post(descriptionPost, LocalDate.now(), profile, null);
        postController.createPost(post);
        
        response.sendRedirect("home.jsp");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
