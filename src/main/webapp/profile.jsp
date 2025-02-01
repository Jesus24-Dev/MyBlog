<%@page import="java.util.ArrayList"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Post"%>
<%@page import="com.jesus24dev.myblog.logic.utils.ProfileFunctions"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Profile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <h1>Profile</h1>
        <h2>Profile info</h2>
        
        <%if(profileToSee.getProfilePicture() != null){%>
           <img src="<%=profileToSee.getProfilePicture() %>">
        <%} else {%>
            <img src="<%=imageUrl%>">
        <%}%> 
        <h3><%=name%></h3>
        <p> 
            <% if(profileToSee.getBiography() != null){%>
            <%=profileToSee.getBiography() %>
            <% }else {%>
                <i>Not biography yet</i>
            <%}%>
        </p>
        <h2>Posts</h2>
         <%
            ArrayList<Post> postList = ProfileFunctions.getPostList(idProfileToSee);
            for (Post p : postList){       
        %>        
        <p><%=p.getDescription() %></p>
        <p><%=ProfileFunctions.getProfileName(p.getProfile().getId()) %></p>
        
        <% if(isEqual){%>
            <a href="postEdit.jsp?id=<%=p.getId()%>">Edit post</a>
            <a href="postDelete.jsp?id=<%=p.getId()%>">Delete post</a>
        <% }%>                      
        <a href="postComments.jsp?id=<%=p.getId()%>">Comments: <%=p.getUserComments().size() %></a>
        <p>Date published: <%=p.getPublishedAt() %></p>        
        <%}%>  
    </body>
</html>
