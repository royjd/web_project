<%-- 
    Document   : init
    Created on : 11 d�c. 2015, 17:38:16
    Author     : zakaridia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/post/media.css"/>
<div class = "panel panel-default">
    <div class ="panel-heading">
        Medias
    </div>
    <div class="panel-content">
        <div class="row">
            <div class="col-sm-2">              
                <div class="list-group padding-25">
                    <c:choose> 
                        <c:when test="${user.username == username}"> 
                            <a href="${pageContext.request.contextPath}/${username}/media/photo.htm" class="list-group-item">Your Photos</a> 
                            <a href="${pageContext.request.contextPath}/${username}/media/video.htm" class="list-group-item">Your Videos</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/${username}/media/photo.htm" class="list-group-item">${username}'s Photos</a>  
                            <a href="${pageContext.request.contextPath}/${username}/media/video.htm" class="list-group-item">${username}'s Videos</a>
                        </c:otherwise>
                    </c:choose> 
                    <a href="${pageContext.request.contextPath}/${username}/media/album.htm" class="list-group-item">Albums</a>


                </div>
            </div>
            <div class="col-sm-10 padding-25">
                <jsp:include page="${mediaContent}.jsp"/>
            </div>


        </div>
    </div>
</div>