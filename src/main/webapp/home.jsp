<%-- 
    Document   : home
    Created on : 30 ene 2025, 10:08:15
    Author     : Jesus24-Dev
--%>

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
    </head>
    <body>
        <%Profile profile = (Profile) request.getSession().getAttribute("profile"); %>
        <h1>Welcome. Profile id: <%=profile.getId() %></h1>
        
        
        <!-- Load profile info -->
        
        <img src="<%=profile.getProfilePicture() %>">
        <a href="SvProfile?id=<%=profile.getId() %>"><%=HomeFunctions.getProfileFulllName(profile.getId()) %></a>
        <a href="newPost.jsp">POST</a>
        <a href="searchPost.jsp">SEARCH</a>
        
        <!-- Load posts -->        
        <%
            ArrayList<Post> postList = HomeFunctions.getPostList();
            for (Post p : postList){       
        %>        
        <p><%=p.getDescription() %></p>
        <p><%=HomeFunctions.getProfileName(p.getProfile().getId()) %></p>
        <a href="postComments.jsp?id=<%=p.getId()%>">Comments: <%=p.getUserComments().size() %></a>
        <p>Date published: <%=p.getPublishedAt() %></p>        
        <%}%>  
        
        <a href="SvUser">Sign out</a>
    </body>
</html>
