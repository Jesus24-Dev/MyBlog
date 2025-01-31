
package com.jesus24dev.myblog.logic.servlets.userservlets;

import com.jesus24dev.myblog.logic.controllers.ProfileController;
import com.jesus24dev.myblog.logic.controllers.UserController;
import com.jesus24dev.myblog.persistence.controllers.exceptions.PreexistingEntityException;
import com.jesus24dev.myblog.persistence.models.Profile;
import com.jesus24dev.myblog.persistence.models.User;
import jakarta.persistence.PersistenceException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;


@WebServlet(name = "SvUserCreateAccount", urlPatterns = {"/SvUserCreateAccount"})
public class SvUserCreateAccount extends HttpServlet {
    UserController userController = new UserController();
    ProfileController profileController = new ProfileController();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String email = request.getParameter("email");
            String nickname = request.getParameter("nickname");
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            String password = request.getParameter("password");
            String birthday = request.getParameter("birthday");
            String gender = request.getParameter("gender");

            LocalDate birthdayDate = LocalDate.parse(birthday);

            Profile newEmptyProfile = new Profile(null, null, null, null);
            profileController.createProfile(newEmptyProfile);
            User newUserAccount = new User(email, nickname, name, lastname, password, birthdayDate, gender, newEmptyProfile);

            try {
                userController.createUser(newUserAccount);

                HttpSession userSession = request.getSession();
                userSession.setAttribute("profile", newUserAccount.getProfile());
                response.sendRedirect("home.jsp");

            } catch (PreexistingEntityException e) {
                request.setAttribute("errorCreateAccount", e.getMessage());
                profileController.deleteProfile(newEmptyProfile.getId());
                request.getRequestDispatcher("index.jsp").forward(request, response);

            } catch (PersistenceException e) {
                request.setAttribute("errorCreateAccount", "Unexpected database error.");
                profileController.deleteProfile(newEmptyProfile.getId());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }     
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
