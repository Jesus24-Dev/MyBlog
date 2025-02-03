<%@page import="com.jesus24dev.myblog.persistence.models.Profile"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Post"%>
<%@page import="com.jesus24dev.myblog.logic.utils.CommentFunctions"%>
<%@page import="com.jesus24dev.myblog.logic.utils.HomeFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Comment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyBlog - Comments</title>
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
        .post-container {
        background-color: #f8f9fa;
        padding: 20px;
        border-radius: 10px;
        margin-bottom: 15px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      }
      .comment-box {
        background-color: #ffffff;
        border-radius: 10px;
        padding: 15px;
        margin-bottom: 10px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      }

      .btn-post {
        background-color: #19573b;
        color: white;
        font-weight: bold;
        text-align: center;
        border-radius: 10px;
      }
      .btn-btn-post {
        border: none;
        padding: 15px 0;
        color: white;
        width: 100%;
      }

      .max-h-100 {
        max-height: 100vh;
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
    <body class="overflow-hidden">

        <%
        Long idPost = Long.parseLong(request.getParameter("id"));
        Post post = CommentFunctions.getPostInfo(idPost);
        Profile profile = (Profile) request.getSession().getAttribute("profile"); 
        Long idProfileSession = profile.getId();
        
    %>  

        <div class="container-fluid ">
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
                    <a href="home.jsp" class="btn btn-light btn-custom">HOME</a>
                    <a href="SvUser" class="btn btn-light btn-custom">LOG OUT</a>
                </div>

                <div class="col-md-9 col-lg-10 py-4">
                    <div class="row">
                        <!-- Left content -->
                        <div class="col-md-8">
                          <div class="post-container">
                            <p> <%=post.getDescription() %></p>
                            <p class="text-success fw-bold"><%=CommentFunctions.getProfileName(post.getProfile().getId())%>n</p>
                          </div>
                        </div>
                        <!-- Right content -->
                        <div class="col-md-4 overflow-auto max-h-100 p-4">                         
                            <%
            ArrayList<Comment> commentList = CommentFunctions.getCommentList(idPost);
            if(!commentList.isEmpty()){
                for (Comment c : commentList){       
        %>        
        <div class="comment-box">
            <p><%=c.getDescription() %></p>
            <p class="text-success fw-bold">
                <%=CommentFunctions.getProfileName(c.getProfile().getId()) %>
            </p>
            <p>Date published: <%=c.getPublishedAt() %></p>
            <% if (idProfileSession == c.getProfile().getId()){%>
                <a href="commentEdit.jsp?id=<%=c.getId()%>" class="text-success">Edit</a>
                <a href="commentDelete.jsp?id=<%=c.getId()%>" class="text-success">Delete</a>
            <%} %>
          </div>
                                                             
                               
        <%      } 
            }%>
                          <form method="POST" action="SvComment">
                            <input type="text" name="commentText" class="form-control my-2">
                            <input type="hidden" name="id" value="<%=idPost%>">
                            <button type="submit" class="btn-btn-post btn-post">POST</button>
                        </form>                          
                        </div>
                      </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
