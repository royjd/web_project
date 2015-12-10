<%-- 
    Document   : wall
    Created on : Dec 10, 2015, 4:37:32 PM
    Author     : Karl Lauret
--%>
<%@ include file="wall/header.jsp" %>
<%@ include file="wall/navBar.jsp" %>
<div class="container">
    <div class="col-sm-2">
        <%@ include file="wall/leftSideBar.jsp" %>
    </div>
    <div class="col-sm-8">
        <jsp:include page="wall/info.jsp" flush="true" />
    </div>
    <div class="col-sm-2">
        <jsp:include page="wall/rightSideBar.jsp" flush="true"/>
    </div>
</div>