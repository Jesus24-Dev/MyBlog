
package com.jesus24dev.myblog.logic.servlets.postservlets;

import com.jesus24dev.myblog.logic.controllers.PostController;
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

@WebServlet(name = "SvPost", urlPatterns = {"/SvPost"})
public class SvPost extends HttpServlet{
    PostController postController = new PostController();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
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
