<%-- 
    Document   : notification
    Created on : Dec 10, 2015, 4:38:17 PM
    Author     : Karl Lauret
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h1> Notification </h1>
            <c:forEach items="${notifs}" var="tier" >
                <p class="col-xs-12">${tier.message.content}</p>
                <div class="col-xs-12 bg-primary"></div>
            </c:forEach>
        </div>
    </div>
</div>
