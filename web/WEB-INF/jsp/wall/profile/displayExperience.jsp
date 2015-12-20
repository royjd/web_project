<%-- 
    Document   : displayExperience
    Created on : 20 déc. 2015, 11:00:15
    Author     : Diawara
--%>

<%@page import="java.util.List"%>
<%@page import="dao.ExperienceEntity"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-sm-2">              
    <div class="list-group">
        <a href="#" class="list-group-item">Resumé </a>
        <a href="${pageContext.request.contextPath}/${username}/experience.htm" class="list-group-item">Experiences</a>
    </div>
</div>

<div class="col-sm-8">
        <%
        String addExp = "";
        addExp = request.getContextPath() + "/" + (String)request.getAttribute("username") + "/experience/add.htm";
        addExp = "<a href=\"" + addExp + "\"> Ajouter une experience</a>";
        request.setAttribute("addExp", addExp);
        
        /*String editLink = request.getContextPath() + "/" + (String)request.getAttribute("username") + "/experience/edit.htm";
        editLink = "<a href=\"" + editLink + "\"> Edit </a>";
        
        String removeLink = request.getContextPath() + "/" + (String)request.getAttribute("username") + "/experience/remove.htm";
        removeLink = "<a href=\"" + removeLink + "\"> Remove </a>";
        request.setAttribute("editLink", editLink+removeLink);*/
        %>
    ${ empty experiences && canModify ? addExp : empty experiences ? "No experiences" : "" }
    
    <%
        List<ExperienceEntity> experiences = (List<ExperienceEntity>)request.getAttribute("experiences");
        String editLink = request.getContextPath() + "/" + (String)request.getAttribute("username") + "/experience/edit/";
        String removeLink = request.getContextPath() + "/" + (String)request.getAttribute("username") + "/experience/remove/";
        for(ExperienceEntity experience : experiences){
            String link_edit = editLink + experience.getId() + ".htm";
            link_edit = "<a href=\"" + link_edit + "\"> Edit </a>";
            request.setAttribute("editLink", link_edit); 
            String link_remove = removeLink + experience.getId() + ".htm";
            link_remove = "<a href=\"" + link_remove + "\"> Remove </a>";
            request.setAttribute("link", link_edit + link_remove); 
            request.setAttribute("experience", experience);
    %>
            ${experience.title}
            ${"<br>"}
            ${experience.description}
            <br/>
            ${experience.localisation.zipcode} ${experience.localisation.street} ${experience.localisation.city} ${experience.localisation.stat}
            <br>
            ${canModify ? link : ""}
            <hr>
    <%}
    %>

</div>
    
<div class="col-sm-2">
    ${canModify?  addExp : "can not Modify" }
</div>