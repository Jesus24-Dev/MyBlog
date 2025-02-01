<%@page import="com.jesus24dev.myblog.persistence.models.Profile"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Post"%>
<%@page import="com.jesus24dev.myblog.logic.utils.CommentFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Comment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyBlog - Comments</title>
    </head>
    <body>
        <%
            Long idPost = Long.parseLong(request.getParameter("id"));
            Post post = CommentFunctions.getPostInfo(idPost);
            Profile profile = (Profile) request.getSession().getAttribute("profile"); 
            Long idProfileSession = profile.getId();
            
        %>   
        
        <h2>Post</h2> 
        <!--Load post info -->
        <p> <%=post.getDescription() %></p>
        <h3><%=CommentFunctions.getProfileName(post.getProfile().getId())%></h3>
        
        <h2>Comments post</h2>       
        <!-- Load Comments -->        
        <%
            ArrayList<Comment> commentList = CommentFunctions.getCommentList(idPost);
            if(!commentList.isEmpty()){
                for (Comment c : commentList){       
        %>        
                <p><%=c.getDescription() %></p>
                <p><%=CommentFunctions.getProfileName(c.getProfile().getId()) %></p>
                
               <% if (idProfileSession == c.getProfile().getId()){%>
                    <a href="commentEdit.jsp?id=<%=c.getId()%>">Edit</a>
                    <a href="commentDelete.jsp?id=<%=c.getId()%>">Delete</a>
                <%} %>
                
                <p>Date published: <%=c.getPublishedAt() %></p>
                
        <%      } 
            }%> 
            
            
        <h2>Add new comment </h2>
        <form method="POST" action="SvComment">
            <input type="text" name="commentText">
            <input type="hidden" name="id" value="<%=idPost%>">
            <button type="submit">Send</button>
        </form>
            
        <a href="home.jsp">Go to home</a>
    </body>
</html>
