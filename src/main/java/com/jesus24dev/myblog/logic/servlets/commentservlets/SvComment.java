
package com.jesus24dev.myblog.logic.servlets.commentservlets;

import com.jesus24dev.myblog.logic.controllers.CommentController;
import com.jesus24dev.myblog.logic.controllers.PostController;
import com.jesus24dev.myblog.persistence.models.Comment;
import com.jesus24dev.myblog.persistence.models.Post;
import com.jesus24dev.myblog.persistence.models.Profile;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SvComment", urlPatterns = {"/SvComment"})
public class SvComment extends HttpServlet{
    CommentController commentController = new CommentController();
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
        String commentText = request.getParameter("commentText");
        Profile profile = (Profile) session.getAttribute("profile");
        Long idPost = Long.parseLong(request.getParameter("id"));
        
        Post postCommented = postController.getPost(idPost);
        
        Comment comment = new Comment(commentText, LocalDate.now(), profile, postCommented);
        
        commentController.createComment(comment);
        
        response.sendRedirect("postComments.jsp?id=" + idPost);
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
