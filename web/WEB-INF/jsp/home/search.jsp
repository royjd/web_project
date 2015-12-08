<%-- 
    Document   : search
    Created on : Dec 8, 2015, 2:09:46 PM
    Author     : Karl Lauret
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
    <c:forEach items="${searchResult}" var="tier" >
        ${tier.email}      
        
    <a href="addFriend.htm?id=${tier.id}"> Add_Friend </a>
    </c:forEach>   

</body>
</html>
