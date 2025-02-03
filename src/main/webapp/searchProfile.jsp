
<%@page import="com.jesus24dev.myblog.logic.utils.HomeFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Profile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyBlog - Search</title>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>MyBlog - Search</title>
            <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
        />
        <style>
          body {
            background-color: #f8f9fa;
          }
          .sidebar {
            background-color: #1e5631;
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
          .card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
          }
          .tab-btn {
            background-color: #d3e9d7;
            color: #1e5631;
          }
          .tab-btn.active {
            background-color: #1e5631;
            color: white;
          }
          .navbar-toggler {
            border: none;
          }
          .navbar-toggler:focus {
            box-shadow: none;
          }
          .sidebar {
                background-color: #2c5f2d;
                color: white;
                min-height: 100vh;
            }
            .profile-img {
                border-radius: 50%;
                width: 100px;
                height: 100px;
                object-fit: cover;
            }
            .btn-custom {
                width: 75%;
                margin-bottom: 0.5rem;
            }
            .post-card {
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                margin-bottom: 1rem;
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
        ArrayList<Profile> profileList = (ArrayList) request.getAttribute("profileList");
        Profile profile = (Profile) request.getSession().getAttribute("profile"); 
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
                <a href="SvUser" class="btn btn-light btn-custom">LOG OUT</a>
            </div>
            <div class="col-md-9 col-lg-10 py-4">
            <form action="SvSearchProfile" method="GET" class="row mb-4">
                <div class="col-12 col-md-8 mb-2 mb-md-0">
                  <input type="text" name="text" class="form-control" placeholder="Search" />
                </div>
                <div class="col-12 col-md-4 text-start">
                  <button type="submit" class="btn btn-success w-100">SEARCH</button>
                </div>
            </form>

            <div class="row mb-4">
                <div class="col text-center">
                    <a href="searchPost.jsp" class="btn tab-btn w-100 w-md-25 mb-2 mb-md-0">
                        POST
                    </a>
                </div>
                <div class="col text-center">
                    <a href="searchProfile.jsp" class="btn tab-btn active w-100 w-md-25">PROFILE</a>
                </div>
            </div>
            <div class="col-md-9 col-lg-10 py-4 overflow-auto">
                <%if (profileList != null) {%>       
                    <% if (!profileList.isEmpty()){%>

                    <%
                        for (Profile p : profileList){       
                    %>        
                            <div class="post-card p-3">
                                
                                <div class="d-flex justify-content-between">
                                  <a href="SvProfile?id=<%=p.getId() %>" class="fw-bold text-success"><%=HomeFunctions.getProfileName(p.getId()) %></a>
                                  <% if(p.getBiography() != null){%>
                                    <%=p.getBiography() %>
                                    <% }else {%>
                                        <i>Not biography yet</i>
                                    <%}%>                               
                                </div>
                              </div>
                        <%} %>
                    <%} else { %>
                        <h2>No results founded</h2>
                    <%} %>
                <%}%>
                </div>   
            </div>               
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
