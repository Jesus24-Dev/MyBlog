
package com.jesus24dev.myblog.logic.servlets.profileservlets;

import com.jesus24dev.myblog.logic.controllers.ProfileController;
import com.jesus24dev.myblog.persistence.models.Profile;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SvProfile", urlPatterns = {"/SvProfile"})
public class SvProfile extends HttpServlet {
    ProfileController profileController = new ProfileController();
       
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            Long id = Long.parseLong(request.getParameter("id"));
            Profile profile = (Profile) session.getAttribute("profile");
            Profile profileToTest = profileController.getProfile(id);
            if (profileToTest == null) {
                request.getRequestDispatcher("errorpages/error404.jsp").forward(request, response);
            } else {
                response.sendRedirect("profile.jsp?id="+id);
            }
            
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
