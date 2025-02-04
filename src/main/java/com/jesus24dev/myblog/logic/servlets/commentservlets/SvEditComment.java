
package com.jesus24dev.myblog.logic.servlets.commentservlets;

import com.jesus24dev.myblog.logic.controllers.CommentController;
import com.jesus24dev.myblog.persistence.models.Comment;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEditComment", urlPatterns = {"/SvEditComment"})
public class SvEditComment extends HttpServlet {
    CommentController commentController = new CommentController();
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
            
            String postId = request.getParameter("postId");
            String commentId = request.getParameter("id");
            String description = request.getParameter("description");
            Comment comment = commentController.getComment(Long.parseLong(commentId));
            comment.setDescription(description);
            
            commentController.editComment(comment);
            
            response.sendRedirect("/MyBlog/postComments.jsp?id=" + postId);
            
            
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
