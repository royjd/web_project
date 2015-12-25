<%-- 
    Document   : editExperience
    Created on : 20 déc. 2015, 12:52:43
    Author     : Diawara
--%>

<div class="col-sm-2">              
    <div class="list-group">
        <a href="#" class="list-group-item">Resumé </a>
        <a href="#" class="list-group-item">Experiences</a>
    </div>
</div>

<div class="col-sm-7">
    <h3>Edit an experience</h3>
    <form  action ="${pageContext.request.contextPath}/${user.username}/experience/edit.htm " method="POST">
        <input name = "id" value="${experience.id}" hidden/>
        <input name = "localisation.id" value="${experience.localisation.id}" hidden/>
        
        <input name="title" type = "text" value="${experience.title}" placeholder="${empty experience.title ? "Experience tile" : "" }"/>
        <br/><br/>
        <input name="localisation.city" value="${experience.localisation.city}" placeholder="${empty experience.localisation.city ? "City " : "" }" size="15"/>
        <input name="localisation.stat" value="${experience.localisation.stat}" placeholder="${empty experience.localisation.stat ? "Stat " : "" }" size="15"/>
        <br/><br/>
        <input name="localisation.street" value="${experience.localisation.street}" placeholder="${empty experience.localisation.street ? "street " : "" }" size="15"/>
        <input name="localisation.zipcode" value="${experience.localisation.zipcode}" placeholder="${empty experience.localisation.zipcode ? "zipcode " : "" }" size="15"/>
        <br/><br/>
        <textarea name="description" placeholder="${empty experience.description ? "Experience description" : "" }">${experience.description}</textarea>
        <br/><br/>
        <input type="submit" value="Validate" />
    </form>
    
</div>
    
<div class="col-sm-3">
</div>
