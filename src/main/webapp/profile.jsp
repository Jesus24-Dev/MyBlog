<%@page import="java.util.ArrayList"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Post"%>
<%@page import="com.jesus24dev.myblog.logic.utils.ProfileFunctions"%>
<%@page import="com.jesus24dev.myblog.logic.utils.HomeFunctions"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Profile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
        rel="stylesheet"
        />
        <style>
        .sidebar {
            background-color: #2c5f2d;
            color: white;
            min-height: 100vh;
        }
        .sidebar .btn {
        color: #1e5631;
        background-color: white;
        border: none;
        margin-bottom: 1rem;
      }
      .border-green {
        border-bottom: 3px solid #2c5f2d;
      }
      .sidebar .btn:hover {
        background-color: #d3e9d7;
      }
        .post-card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            margin-bottom: 1rem;
        }
        .btn-custom {
            width: 75%;
            margin-bottom: 0.5rem;
        }
        @media (max-width: 768px) {
            .sidebar {
            min-height: auto;
            height: auto;
            }
            .btn-custom {
            width: 100%;
            }
        }
        </style>
    </head>
    <body>
<%
    
    HttpSession mySession = request.getSession(false);

    
    if (mySession == null || mySession.getAttribute("profile") == null) {
        response.sendRedirect("errorpages/error403.jsp"); 
        return;
    }
    
    String id = request.getParameter("id"); 
    Long idProfileToSee;

    if (id == null || id.trim().isEmpty()) {
        response.sendRedirect("errorpages/error404.jsp");
        return;
    }
    
    try {
        idProfileToSee = Long.parseLong(id);
    } catch (NumberFormatException e) {
        response.sendRedirect("errorpages/error404.jsp");
        return;
    }
    
    Profile profile = (Profile) request.getSession().getAttribute("profile"); 
    boolean isEqual = false;
    Profile profileToSee;
    
    if (idProfileToSee != profile.getId()) {
        profileToSee = ProfileFunctions.profileToSee(idProfileToSee);
        if (profileToSee == null) {
            response.sendRedirect("errorpages/error404.jsp");
            return;
        }
    } else {
        profileToSee = profile;
        isEqual = true;
    }
    String name = ProfileFunctions.getProfileName(profileToSee.getId());

%>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3 col-lg-2 sidebar d-flex flex-column align-items-center py-4">
                    <a href="SvProfile?id=<%=profile.getId() %>" class="mb-4 h5 text-decoration-none text-light"><%=HomeFunctions.getProfileFulllName(profile.getId()) %></a>
                    <a href="newPost.jsp" class="btn btn-light btn-custom">POST</a>
                    <a href="home.jsp" class="btn btn-light btn-custom">HOME</a>
                    <a href="searchPost.jsp" class="btn btn-light btn-custom">SEARCH</a>
                    <a href="SvUser" class="btn btn-light btn-custom">LOG OUT</a>
                </div>
                <div class="col-md-9 col-lg-10 py-4">
                    <div class="d-flex flex-column align-items-center mb-5 border-green">
                        <h4 class="mb-1"><%=name%></h4>
                        <p> 
                            <% if(profileToSee.getBiography() != null){%>
                            <%=profileToSee.getBiography() %>
                            <% }else {%>
                                <i>Not biography yet</i>
                            <%}%>
                        </p>
                        
                        <% if(isEqual){%>
                            <p><a href="crudpages/editBiography.jsp?id=<%=profileToSee.getId()%>" class="text-success text-decoration-none">Edit biography</a></p>
                        <%}%> 
                    </div>
                    <div class="row">
                        <div class="col-md-9 col-lg-10 py-4 overflow-auto d-flex flex-column-reverse">
                            <!-- Load posts -->  
                            <%
                                ArrayList<Post> postList = ProfileFunctions.getPostList(idProfileToSee);
                                for (Post p : postList){       
                            %>      
                            <div class="post-card p-3">
                                <p><%=p.getDescription() %></p>
                                    <p><a href="postComments.jsp?id=<%=p.getId()%>" class="text-muted">Comments: <%=p.getUserComments().size() %></a></p>
                                    <p>Date published: <%=p.getPublishedAt() %></p>
                                    <% if(isEqual){%>
                                        <a href="postEdit.jsp?id=<%=p.getId()%>" class="text-decoration-none text-success">Edit post</a>
                                        <a href="postDelete.jsp?id=<%=p.getId()%>" class="text-decoration-none text-success">Delete post</a>
                                    <% }%>    
                            </div>        
                                <%}%>   
                        </div>
                    </div>
                </div>                
            </div>
        </div>                      
    </body>
</html>
