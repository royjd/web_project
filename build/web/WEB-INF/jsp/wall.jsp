<%-- 
    Document   : wall
    Created on : Dec 10, 2015, 4:37:32 PM
    Author     : Karl Lauret
--%>
<%@ include file="wall/header.jsp" %>
<%@ include file="wall/navBar.jsp" %>
<div class="container">
    <div class="col-sm-12">
        <jsp:include page="wall/${wallContent}.jsp" flush="true" />
    </div>
</div>