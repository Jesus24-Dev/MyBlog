<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>MyBlog - Start</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <style>
      .error-message {
        color: red;
        font-size: 0.875em;
        margin-top: 0.25rem;
      }
    </style>
  </head>
  <body>
    <% String errorSignInMessage = (String) request.getAttribute("errorSignIn");
    %>

    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h3 class="text-center">ACCOUNT LOGIN</h3>
            </div>
            <div class="card-body">
              <form action="SvUser" method="POST">
                <div class="mb-3">
                  <label for="emaiil" class="form-label">Email</label>
                  <input
                    type="email"
                    class="form-control"
                    id="email"
                    name="email"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Password</label>
                  <input
                    type="password"
                    class="form-control"
                    id="password"
                    name="password"
                    required
                  />
                </div>
                <button type="submit" class="btn btn-success w-100">
                  Login
                </button>
              </form>
              <% if (errorSignInMessage != null) {%>
              <div class="mb-3">
                <i class="error-message"><%= errorSignInMessage%></i>
              </div>
              <%}%>
              <div class="text-center mt-3">
                <a href="register.jsp" class="text-decoration-none text-success"
                  >Register now</a
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
