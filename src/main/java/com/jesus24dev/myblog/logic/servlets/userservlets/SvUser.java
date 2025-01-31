
package com.jesus24dev.myblog.logic.servlets.userservlets;

import com.jesus24dev.myblog.logic.controllers.UserController;
import com.jesus24dev.myblog.persistence.models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SvUser", urlPatterns = {"/SvUser"})
public class SvUser extends HttpServlet {
    UserController userController = new UserController();
    
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
        
            String email = request.getParameter("email");
            String password = request.getParameter("password");            
            User user = userController.getUser(email);
            
            if(user == null){
                request.setAttribute("errorSignIn", "user not founded");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                if (user.getPassword().equals(password)){
                    HttpSession userSession = request.getSession();
                    userSession.setAttribute("profile", user.getProfile());
                    response.sendRedirect("home.jsp");
                } else {
                    request.setAttribute("errorSignIn", "password don't match");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }                
            }        
    }
        
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
