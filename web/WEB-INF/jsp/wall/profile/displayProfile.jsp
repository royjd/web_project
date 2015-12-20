<%-- 
    Document   : displayProfile
    Created on : 19 déc. 2015, 10:56:28
    Author     : zakaridia
--%>
<%@page import="dao.UserEntity"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-sm-2">              
    <div class="list-group">
        <a href="#" class="list-group-item">Resumé </a>
        <a href="${pageContext.request.contextPath}/${user.username}/experience.htm" class="list-group-item">Experiences</a>
    </div>
</div>

<div class="col-sm-7">
    
    <p>
        ${user.profile.firstName} ${user.profile.lastName}
    </p>

    <p>
        Brief Description <br/>
        ${ empty user.profile.description ?
                       " Aucune description" :
                       user.profile.description
        }

    </p>

    <p>
        sexe : ${ empty user.profile.physical.gender ?
                       "Inconnue" :
                       user.profile.physical.gender
        }
    </p>

    <p>
        Height : 
        ${ empty user.profile.physical.height ?
                       "Inconnue , " :
                       user.profile.physical.height
        }

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Weight : 
        ${ empty user.profile.physical.weight ?
                       "Inconnue " :
                       user.profile.physical.weight
        }
    </p>
    <%
        String link = "";
        if ((Boolean) request.getAttribute("canModify")) {
            UserEntity u = (UserEntity) session.getAttribute("user");
            link = request.getContextPath() + "/" + u.getUsername() + "/profile/edit.htm";
            link = "<a href=\"" + link + "\"> edit</a>";
            request.setAttribute("link", link);
        }
    %>
    ${ canModify ? link : "You can not modify"}
    
    <hr>
    Experiences 
    <hr>
    <%
        String addExp = "";
        UserEntity u = (UserEntity) session.getAttribute("user");
        if ( (Boolean) request.getAttribute("canModify") && u.getProfile().getExperiences().isEmpty()) {
            addExp = request.getContextPath() + "/" + u.getUsername() + "/experience/add.htm";
            addExp = "<a href=\"" + addExp + "\"> Ajouter une experience</a>";
            request.setAttribute("addExp", addExp);
        }%>
    ${ empty user.profile.experiences ? addExp : "" }
    <c:forEach items="${user.profile.experiences}" var="tier" >
        ${tier.title}
        ${"<br>"}
        ${tier.description}
    </c:forEach>

</div>
    
<div class="col-sm-3">
    Mobile Number : 07 89 07 85 05
    <br/><br/>
    BirthDay : 25 Avril 1990 
</div>