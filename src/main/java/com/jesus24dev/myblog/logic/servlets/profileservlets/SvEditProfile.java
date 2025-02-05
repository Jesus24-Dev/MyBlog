
package com.jesus24dev.myblog.logic.servlets.profileservlets;

import com.jesus24dev.myblog.logic.controllers.ProfileController;
import com.jesus24dev.myblog.persistence.models.Profile;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet(name = "SvEditProfile", urlPatterns = {"/SvEditProfile"})
public class SvEditProfile extends HttpServlet {
    ProfileController profileController = new ProfileController();

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

        String biography = request.getParameter("biography");
        Long id = Long.parseLong(request.getParameter("id"));
        
        ProfileController profileController = new ProfileController();
        Profile profile = profileController.getProfile(id);

        if (profile != null) {
            
            HttpSession session = request.getSession();
            
            profile.setBiography(biography.trim());             
            profileController.editProfile(profile); 
            session.removeAttribute("profile");
            session.setAttribute("profile", profile);
            response.sendRedirect("profile.jsp?id="+id);
            
        } 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
