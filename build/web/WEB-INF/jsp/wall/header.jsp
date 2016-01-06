<%-- 
    Document   : header
    Created on : Dec 10, 2015, 6:10:20 PM
    Author     : Karl Lauret
--%>

<div class="row">
    <div style="width:100%;height:315px; background-color: red">
        <c:if test="${canModify}">
            <h4>Define profile picture Cover<button id="addCover" class="btn btn-primary btn-sm glyphicon glyphicon-plus"></button></h4>
        </c:if>

        <div id="addCoverDiv" style="display:none">
            <form enctype="multipart/form-data" method="post" class="form-horizontal" role="form" id="pictureProfileForm" action="${pageContext.request.contextPath}/${username}/media/addPicture.htm">
                <span class="btn btn-default btn-file">
                    Choose profile picture <input type="file" name="file"/>
                </span>
                <input name="type" type ="hidden" value="cover"/>

                <div class="form-group">        
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-success">Submit</button>
                    </div>
                </div>
            </form>
        </div>
        <c:choose>
            <c:when test="${empty user.profile.pictureCover}">

            </c:when>
            <c:otherwise>
                <div class="img-overlay">
                    <img src="${pageContext.request.contextPath}/resources/img/${user.profile.pictureCover.mediaType.link}" class="img-responsive"/>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
