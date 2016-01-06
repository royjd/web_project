<%-- 
    Document   : search
    Created on : Dec 8, 2015, 2:09:46 PM
    Author     : Karl Lauret
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h1>SEARCH</h1>

            <c:forEach items="${searchResult}" var="tier" >
                ${tier.email}      

                <a href="addFriend.htm?id=${tier.id}" class="bg-info"> Add_Friend </a>
            </c:forEach> 
        </div>
    </div>
</div>
