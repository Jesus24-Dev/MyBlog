<%@page import="com.jesus24dev.myblog.logic.utils.HomeFunctions"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyBlog - Search</title>
    </head>
    <body>
        <%
        ArrayList<Post> postList = (ArrayList) request.getAttribute("postList");
        %>
               
        <p>Post</p>
        <a href="searchProfile.jsp">Profile </a>
        
        <form action="SvPost" method="GET">
            <input type="text" name="text">
            <button type="submit">Search</button>            
        </form>
        
        <%if (postList != null) {%>       
            <% if (!postList.isEmpty()){%>

            <%
                for (Post p : postList){       
            %>        
                <p><%=p.getDescription() %></p>
                <p><%=HomeFunctions.getProfileName(p.getProfile().getId()) %></p>
                <a href="postComments.jsp?id=<%=p.getId()%>">Comments: <%=p.getUserComments().size() %></a>
                <p>Date published: <%=p.getPublishedAt() %></p>        
                <%} %>

            <%} else { %>
                <h2>No results founded</h2>
            <%} %>
        <%}%>
    </body>
</html>
