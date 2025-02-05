
package com.jesus24dev.myblog.logic.servlets.postservlets;

import com.jesus24dev.myblog.logic.controllers.PostController;
import com.jesus24dev.myblog.persistence.models.Post;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvCrudPost", urlPatterns = {"/SvCrudPost"})
public class SvCrudPost extends HttpServlet {
    PostController postController = new PostController();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String profileId = request.getParameter("profileId");
            String postId = request.getParameter("id");
            postController.deletePost(Long.parseLong(postId));
            
            response.sendRedirect("/MyBlog/profile.jsp?id=" + profileId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String postId = request.getParameter("id");
            String profileId = request.getParameter("profileId");
            String description = request.getParameter("description");
            Post post = postController.getPost(Long.parseLong(postId));
            
            
            post.setDescription(description);           
            postController.editPost(post);
            
            response.sendRedirect("/MyBlog/profile.jsp?id=" + profileId);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
