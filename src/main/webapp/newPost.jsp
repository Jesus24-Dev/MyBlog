<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page
import="com.jesus24dev.myblog.persistence.models.Profile"%> <%@page
import="com.jesus24dev.myblog.logic.utils.HomeFunctions"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>MyBlog - New Post</title>
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
      .create-post-container {
        background-color: #ffffff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        max-width: 600px;
        margin: 50px auto;
      }
      .post-button {
        background-color: #19573b;
        color: white;
        font-weight: bold;
        border: none;
        border-radius: 10px;
        padding: 10px 20px;
      }
      .post-button:hover {
        background-color: #164d34;
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
    <% HttpSession mySession = request.getSession(false); if (mySession == null
    || mySession.getAttribute("profile") == null) {
    response.sendRedirect("errorpages/error403.jsp"); return; } Profile profile
    = (Profile) request.getSession().getAttribute("profile"); %>

    <div class="container-fluid">
      <div class="row">
        <div
          class="col-md-3 col-lg-2 sidebar d-flex flex-column align-items-center py-4"
        >
          <a
            href="SvProfile?id=<%=profile.getId() %>"
            class="mb-4 h5 text-decoration-none text-light"
            ><%=HomeFunctions.getProfileFulllName(profile.getId()) %></a
          >
          <a href="home.jsp" class="btn btn-light btn-custom">HOME</a>
          <a href="searchPost.jsp" class="btn btn-light btn-custom">SEARCH</a>
          <a href="SvUser" class="btn btn-light btn-custom">LOG OUT</a>
        </div>

        <div class="col-md-9 col-lg-10">
          <form action="SvPost" method="POST" class="create-post-container">
            <h6>Create post</h6>
            <textarea
              name="descriptionPost"
              class="form-control mt-3 mb-4"
              rows="8"
              placeholder="Write your post here..."
            >
            </textarea>
            <div class="d-flex justify-content-end">
              <button class="post-button">POST</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <a href="home.jsp">Go to home</a>
  </body>
</html>
