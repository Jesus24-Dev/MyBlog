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
        .profile-img {
            border-radius: 50%;
            width: 100px;
            height: 100px;
            object-fit: cover;
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
        .profile-img2 {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            object-fit: cover;
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
        <%Long idProfileToSee = Long.parseLong(request.getParameter("id"));
        Profile profile = (Profile) request.getSession().getAttribute("profile"); 
        boolean isEqual = false;
        Profile profileToSee;
        if(idProfileToSee != profile.getId()){
                profileToSee = ProfileFunctions.profileToSee(idProfileToSee);
            } else {
                profileToSee = profile;
                isEqual = true;
            }
        
        String imageUrl = "https://static.vecteezy.com/system/resources/previews/005/544/718/non_2x/profile-icon-design-free-vector.jpg";
                   
        String name = ProfileFunctions.getProfileName(profileToSee.getId());
        %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3 col-lg-2 sidebar d-flex flex-column align-items-center py-4">
                    <img
                    src="<%=profile.getProfilePicture() %>"
                    alt="Profile"
                    class="profile-img mb-3"
                    />
                    <a href="SvProfile?id=<%=profile.getId() %>" class="mb-4 h5 text-decoration-none text-light"><%=HomeFunctions.getProfileFulllName(profile.getId()) %></a>
                    <a href="newPost.jsp" class="btn btn-light btn-custom">POST</a>
                    <a href="home.jsp" class="btn btn-light btn-custom">HOME</a>
                    <a href="searchPost.jsp" class="btn btn-light btn-custom">SEARCH</a>
                    <a href="SvUser" class="btn btn-light btn-custom">LOG OUT</a>
                </div>
                <div class="col-md-9 col-lg-10 py-4">
                    <div class="d-flex flex-column align-items-center mb-5 border-green">
                        <%if(profileToSee.getProfilePicture() != null){%>
                            <img class="profile-img mb-3" src="<%=profileToSee.getProfilePicture() %>">
                         <%} else {%>
                             <img class="profile-img mb-3" src="<%=imageUrl%>">
                         <%}%> 
                        <h4 class="mb-1"><%=name%></h4>
                        <p> 
                            <% if(profileToSee.getBiography() != null){%>
                            <%=profileToSee.getBiography() %>
                            <% }else {%>
                                <i>Not biography yet</i>
                            <%}%>
                        </p>
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
