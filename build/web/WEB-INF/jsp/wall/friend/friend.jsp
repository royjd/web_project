<%-- 
    Document   : friend
    Created on : 25 déc. 2015, 22:34:41
    Author     : Diawara
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class = "panel panel-default">
    <div class ="panel-heading">
        <h4> List friends </h4>
        <ul class="list-inline">
            <li><a href="#">All friends</a></li>
            <li><a href="#">Birth Day</a></li>
        </ul>
    </div>
    <div class="panel-content">

        <c:forEach items="${friends}" var="var">
            <c:choose> 
                <c:when test="${var.friend.username == username}"> 
                    <c:set var="friend" scope="session" value="${var.owner}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="friend" scope="session" value="${var.friend}"/>
                </c:otherwise>
            </c:choose>
            <div class="panel panel-default" id="friend">
                <div class="col-sm-4 btn-info">
                    <br><br><br>Photo de profile<br><br><br><br>
                </div>
                <div class="col-sm-8">
                    <h3> 
                        <a href="${pageContext.request.contextPath}/${friend.username}.htm"  class="" >${friend.profile.firstName} ${friend.profile.lastName}</a><br/>
                        <a href="${pageContext.request.contextPath}/${friend.username}.htm"  class="" ><small>@${friend.username}</small></a>

                    </h3>
                    <p class="text-muted">

                        <% int nbFriend = 0;
                            request.setAttribute("nbFriend", nbFriend);%>
                        <c:forEach items="${var.friend.friends}" var="aFriend">
                            <c:forEach items="${user.friends}" var="me">
                                <c:if test="${me.friend.id == aFriend.friend.id}">
                                    <% nbFriend += 1;%>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                        <%= nbFriend%> friend(s) on common
                    </p>
                    <a href="${pageContext.request.contextPath}/${friend.username}.htm"  class="btn btn-info" >See wall</a>
                    <c:if test="${canRemove}">
                        <a href="${pageContext.request.contextPath}/${friend.username}/removeFriend.htm?id=${friend.id}"  class="pull-right btn btn-danger" >Remove</a>
                    </c:if>
                </div>

            </div>


        </c:forEach>

    </div>
</div>