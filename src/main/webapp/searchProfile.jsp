
<%@page import="com.jesus24dev.myblog.logic.utils.HomeFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jesus24dev.myblog.persistence.models.Profile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyBlog - Search</title>
    </head>
    <body>
        <%
            ArrayList<Profile> profileList = (ArrayList) request.getAttribute("profileList");
        %>
           
        <a href="searchPost.jsp">Post</a>
        <p>Profile</p>
               
        <form action="SvSearchProfile" method="GET">
            <input type="text" name="text">
            <button type="submit">Search</button>            
        </form>
        
        <%if (profileList != null) {%>       
            <% if (!profileList.isEmpty()){%>

            <%
                for (Profile p : profileList){       
            %>      
                <p><%=HomeFunctions.getProfileName(p.getId()) %></p>
                <p> 
                    <% if(p.getBiography() != null){%>
                    <%=p.getBiography() %>
                    <% }else {%>
                        <i>Not biography yet</i>
                    <%}%>
                </p>  
                
                <%} %>

            <%} else { %>
                <h2>No results founded</h2>
            <%} %>
        <%}%>
    </body>
</html>
