<%-- 
    Document   : signUp
    Created on : Dec 6, 2015, 10:14:00 AM
    Author     : Karl Lauret
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
    <div class="col-sm-12 bg-success">
        <h2>Friend Request</h2>
        <c:forEach items="${friendToAccept}" var="tier" >
            ${tier.email}      
            <a href="acceptFriendship.htm?id=${tier.id}" class="bg-info"> Accept </a>
            <a href="deniedFriendship.htm?id=${tier.id}" class="bg-info"> Denied </a>
        </c:forEach>
        <h2>Friend List</h2>
        <c:forEach items="${friends}" var="tier" >
            <c:if test="${tier.accepted}">
                <c:choose> 
                    <c:when test="${tier.friend.email == email}"> 
                        <a href="#" class="bg-info"> ${tier.owner.email} </a>
                    </c:when>
                    <c:otherwise>
                        <a href="#" class="bg-info"> ${tier.friend.email} </a>
                    </c:otherwise>
                </c:choose>
                <a href="removeFriend.htm?id=${tier.id}" class="bg-info"> Remove </a>
            </c:if>

        </c:forEach>  
    </div>
</div>