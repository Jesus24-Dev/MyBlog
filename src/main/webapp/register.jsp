<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>MyBlog - Register</title>
    <style>
      .error-message {
        color: red;
        font-size: 0.875em;
        margin-top: 0.25rem;
      }
    </style>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>

  <% String errorCreateAccountMessage = (String)
  request.getAttribute("errorCreateAccount"); %>

  <body>
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h3 class="text-center">Create Account</h3>
            </div>
            <div class="card-body">
              <form action="SvUserCreateAccount" method="POST">
                <div class="mb-3">
                  <label for="email" class="form-label">Email</label>
                  <input
                    type="email"
                    class="form-control"
                    id="email"
                    name="email"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="nickname" class="form-label">Nickname</label>
                  <input
                    type="text"
                    class="form-control"
                    id="nickname"
                    name="nickname"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="name" class="form-label">Name</label>
                  <input
                    type="text"
                    class="form-control"
                    id="name"
                    name="name"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="lastname" class="form-label">Lastname</label>
                  <input
                    type="text"
                    class="form-control"
                    id="lastname"
                    name="lastname"
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
                <div class="mb-3">
                  <label for="birthday" class="form-label">Birthday</label>
                  <input
                    type="date"
                    class="form-control"
                    id="birthday"
                    name="birthday"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label class="form-label">Gender</label>
                  <div>
                    <div class="form-check form-check-inline">
                      <input
                        class="form-check-input"
                        type="radio"
                        name="gender"
                        id="male"
                        value="male"
                      />
                      <label class="form-check-label" for="male">Male</label>
                    </div>
                    <div class="form-check form-check-inline">
                      <input
                        class="form-check-input"
                        type="radio"
                        name="gender"
                        id="female"
                        value="female"
                      />
                      <label class="form-check-label" for="female"
                        >Female</label
                      >
                    </div>
                    <div class="form-check form-check-inline"></div>
                  </div>
                </div>
                <% if (errorCreateAccountMessage != null) {%>
                <div class="mb-3">
                  <i class="error-message"><%= errorCreateAccountMessage%></i>
                </div>
                <%}%>
                <button type="submit" class="btn btn-success w-100">
                  Create Account
                </button>
              </form>
              <div class="text-center mt-3">
                <a href="index.jsp" class="text-decoration-none text-success"
                  >Already have an account? Login</a
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
