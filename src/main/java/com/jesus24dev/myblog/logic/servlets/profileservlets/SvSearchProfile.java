
package com.jesus24dev.myblog.logic.servlets.profileservlets;

import com.jesus24dev.myblog.logic.controllers.ProfileController;
import com.jesus24dev.myblog.logic.controllers.SeekerController;
import com.jesus24dev.myblog.persistence.models.Post;
import com.jesus24dev.myblog.persistence.models.Profile;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "SvSearchProfile", urlPatterns = {"/SvSearchProfile"})
public class SvSearchProfile extends HttpServlet {
    SeekerController seekerController = new SeekerController();
    ProfileController profileController = new ProfileController();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String text = request.getParameter("text");
        
        if (text.trim() != ""){
            ArrayList<Long> profileId = seekerController.getProfileId(text);
            ArrayList<Profile> profileList = new ArrayList();
        
            for(Long id: profileId){
                Profile profile = profileController.getProfile(id);
                profileList.add(profile);
            }
            request.setAttribute("profileList", profileList);
        } else {
            request.setAttribute("profileList", null);
        }
                             
        request.getRequestDispatcher("searchProfile.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
