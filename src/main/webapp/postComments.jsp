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
    HttpSession mySession = request.getSession(false);

    
    if (mySession == null || mySession.getAttribute("profile") == null) {
        response.sendRedirect("errorpages/error403.jsp"); 
        return;
    }
    
    
    String id = request.getParameter("id"); 
    Long idPost;
    
    if (id == null || id.trim().isEmpty()) {
        response.sendRedirect("errorpages/error404.jsp");
        return;
    }
    
    try {
        idPost = Long.parseLong(id);
    } catch (NumberFormatException e) {
        response.sendRedirect("errorpages/error404.jsp");
        return;
    }

    Post post = CommentFunctions.getPostInfo(idPost);
    
    if (post == null) {
        response.sendRedirect("errorpages/error404.jsp");
        return;
    }

    Profile profile = (Profile) request.getSession().getAttribute("profile"); 

    if (profile == null) {
        response.sendRedirect("errorpages/error404.jsp");
        return;
    }

    Long idProfileSession = profile.getId();       
%>


        <div class="container-fluid ">
            <div class="row">
                <div class="col-md-3 col-lg-2 sidebar d-flex flex-column align-items-center py-4">
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
                            <a href="profile.jsp?id=<%=post.getProfile().getId()%>" class="text-success fw-bold"><%=CommentFunctions.getProfileName(post.getProfile().getId())%></a>
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
            <a href="profile.jsp?id=<%=c.getProfile().getId()%>" class="text-success fw-bold">
                <%=CommentFunctions.getProfileName(c.getProfile().getId()) %>
            </a>
            <p>Date published: <%=c.getPublishedAt() %></p>
            <% if (idProfileSession == c.getProfile().getId()){%>
                <a href="crudpages/editComment.jsp?id=<%=c.getId()%>&post=<%=post.getId()%>" class="text-success">Edit</a>
                <a href="SvEditComment?id=<%=c.getId()%>&post=<%=post.getId()%>" class="text-success">Delete</a>
            <%} %>
          </div>
                                                             
                               
        <%      } 
            }%>
                          <form method="POST" action="SvComment">
                            <input type="text" name="commentText" class="form-control my-2" id="post">
                            <p class="text-success fw-bold"><span id="textMax">0</span>/75</p>
                            <input type="hidden" name="id" value="<%=idPost%>">
                            <button type="submit" class="btn-btn-post btn-post post-button">POST</button>
                        </form>                          
                        </div>
                      </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script>
      const textPost = document.getElementById("post");
      const textMax = document.getElementById("textMax");
      const button = document.querySelector(".post-button");
      const maxLength = 75;
      let count = 0;

      const updateCounterAndButton = () => {
        count = textPost.value.length;
        textMax.textContent = count;

        if (count > maxLength) {
          button.disabled = true;
        } else {
          button.disabled = false;
        }
      };

      textPost.addEventListener("input", (event) => {
        if (
          event.inputType === "insertText" ||
          event.inputType === "deleteContentBackward"
        ) {
          updateCounterAndButton();
        }
      });

      textPost.addEventListener("paste", (event) => {
        setTimeout(() => {
          updateCounterAndButton();
        }, 0);
      });
      </script>
    </body>
</html>
