
<%@page import="com.jesus24dev.myblog.persistence.models.Profile"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jesus24dev.myblog.logic.utils.HomeFunctions"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyBlog - Home</title>
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
        <%Profile profile = (Profile) request.getSession().getAttribute("profile"); %>
             
        <!-- Load profile info -->
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
                <a href="searchPost.jsp" class="btn btn-light btn-custom">SEARCH</a>
                <a href="SvUser" class="btn btn-light btn-custom">LOG OUT</a>
            </div>

            <!-- Posts -->         
            <div class="col-md-9 col-lg-10 py-4 overflow-auto">
                <!-- Load posts -->  
                <%
                    ArrayList<Post> postList = HomeFunctions.getPostList();
                    for (Post p : postList){       
                %>        
                <div class="post-card p-3">
                        <p><%=p.getDescription() %></p>
                        <p><a href="postComments.jsp?id=<%=p.getId()%>" class="text-muted">Comments: <%=p.getUserComments().size() %></a></p>
                        <p>Date published: <%=p.getPublishedAt() %></p>
                        <p><a href="SvProfile?id=<%=p.getId() %>" class="text-success fw-bold mb-0"><%=HomeFunctions.getProfileName(p.getProfile().getId()) %></a></p>
                        
                </div>        
                    <%}%>   
            </div>
        </div>        
    </div> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
