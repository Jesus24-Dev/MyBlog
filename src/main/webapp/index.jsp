
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyBlog - Start</title>
    </head>
    <body>
        <h1>MyBlog</h1>
        
        <%
            String errorSignInMessage = (String) request.getAttribute("errorSignIn");
            String errorCreateAccountMessage = (String) request.getAttribute("errorCreateAccount");
        %>
        
        <h2>Sign in</h2>
        <form action="SvUser" method="POST">
            <div>
                <label for="email">Email</label>
                <input type="email" name="email" id="email">
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" name="password" id="password">
            </div>
            
            <% if (errorSignInMessage != null) {%>
            <i><%= errorSignInMessage%></i>
            <%}%>           
            <button type="submit">Sign in</button>
        </form>
            
        <h2>Create Account</h2>
        <form action="SvUserCreateAccount" method="POST">
            <div>
                <label for="email">Email</label>
                <input type="email" name="email" id="email">
            </div>
            <div>
                <label for="nickname">Nickname</label>
                <input type="text" name="nickname" id="nickname">
            </div>
            <div>
                <label for="name">Name</label>
                <input type="text" name="name" id="name">
            </div>
            <div>
                <label for="lastname">Lastname</label>
                <input type="text" name="lastname" id="lastname">
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" name="password" id="password">
            </div>
            <div>
                <label for="birthday">Birthday</label>
                <input type="date" name="birthday" id="birthday">
            </div>
            <div>
                <label for="gender">Gender</label>
                <input type="radio" name="gender" id="gender" value="MALE"> Male
                <input type="radio" name="gender" id="gender" value="FEMALE"> Female
            </div>
            <% if (errorCreateAccountMessage != null) {%>
            <i><%= errorCreateAccountMessage%></i>
            <%}%>    
            <button type="submit">Create account</button>
        </form>
    </body>
</html>
