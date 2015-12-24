<%-- 
    Document   : displayProfile
    Created on : 19 déc. 2015, 10:56:28
    Author     : zakaridia
--%>
<%@page import="dao.UserEntity"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-sm-7">
    <c:if test="${not empty msg}">
        <div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h2>${user.profile.firstName} ${user.profile.lastName}</h2>       


    <h3>
        Gender :  

        <small>
            <c:choose>
                <c:when test="${empty user.profile.physical.gender}">
                    Not define
                </c:when>
                <c:when test="${user.profile.physical.gender=='M'}">
                    Male
                </c:when>
                <c:otherwise>
                    Female
                </c:otherwise>
            </c:choose>
        </small>   
    </h3>


    <h3>
        Height : <small>${ empty user.profile.physical.height ? "Not define" : user.profile.physical.height}</small>

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Weight : <small>${ empty user.profile.physical.weight ? "Not define" : user.profile.physical.weight}</small>
    </h3>

    <hr>
    <h3>Brief Description</h3>
    <p class="well">
        ${ empty user.profile.description ? "No description" :user.profile.description}
    </p>

    <c:if  test="${canModify}">
        <div class="pull-right">
            <a href="${pageContext.request.contextPath}/${user.username}/profile/edit.htm"  class="btn btn-info" role="button">Edit profile</a>
        </div>
        <div class='clearfix'>

        </div>
    </c:if>

    <hr>
    <h3>Experiences</h3>
    <hr>

        <c:forEach items="${user.profile.experiences}" var="experience" >
        <div class="media">
            <div class="media-body">
                <h3 class="media-heading">${experience.title}</h3>

                    ${experience.description}
                    <p class="text-muted">
                    <small>
                        On ${experience.realisationDate}<br>

                        <c:if test="${not empty experience.localisation.street}">
                            ${experience.localisation.street}<br>
                        </c:if>
                        ${experience.localisation.zipcode} ${experience.localisation.city} - ${experience.localisation.stat} 
                    </small>
                    </p>
            </div>

            <c:if test="${canModify}">
                <div class="pull-right">
                    <a href="${pageContext.request.contextPath}/${user.username}/experience/manage/${experience.id}.htm"  class="btn btn-info" role="button">Edit</a>
                </div>
            </c:if>
            <div class="clearfix"></div>
            <hr>
        </div>
    </c:forEach>

    <c:if test="${not empty user.profile.experiences}">
        <a href="${pageContext.request.contextPath}/${user.username}/experience.htm"  class="btn btn-info" role="button">See more</a>
    </c:if>

    <c:if test="${canModify}">
        <a href="${pageContext.request.contextPath}/${user.username}/experience/manage/0.htm"  class="pull-right btn btn-success" role="button">Add experience</a>
    </c:if>

</div>

<div class="col-sm-3 padding-25">

    <h4>
        Email : <small>${user.email}</small>
    </h4>
    <hr>

    <h4>
        @<small>${user.username}</small>
    </h4>
    <hr>

    <h4>
        Mobile Number : <small>${ empty user.profile.phone ? "Not define" : user.profile.phone}</small>
    </h4>
    <hr>

    <h4>
        Birth day : <small>Not define</small>
    </h4>

</div>