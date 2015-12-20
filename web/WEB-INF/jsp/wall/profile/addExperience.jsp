<%-- 
    Document   : addExperience
    Created on : 20 déc. 2015, 00:38:34
    Author     : Diawara
--%>

<div class="col-sm-2">              
    <div class="list-group">
        <a href="#" class="list-group-item">Resumé </a>
        <a href="#" class="list-group-item">Experiences</a>
    </div>
</div>

<div class="col-sm-7">
    <h3>Add an experience</h3>
    <form  action ="${pageContext.request.contextPath}/${user.username}/experience/add.htm " method="POST">
        <input name="title" type = "text" value="${newExperience.title}" placeholder="${empty newExperience.title ? "Experience tile" : "" }"/>
        <br/><br/>
        <input name="localisation.city" value="${newExperience.localisation.city}" placeholder="${empty newExperience.localisation.city ? "City " : "" }" size="15"/>
        <input name="localisation.stat" value="${newExperience.localisation.stat}" placeholder="${empty newExperience.localisation.stat ? "Stat " : "" }" size="15"/>
        <br/><br/>
        <input name="localisation.street" value="${newExperience.localisation.street}" placeholder="${empty newExperience.localisation.street ? "street " : "" }" size="15"/>
        <input name="localisation.zipcode" value="${newExperience.localisation.zipcode}" placeholder="${empty newExperience.localisation.zipcode ? "zipcode " : "" }" size="15"/>
        <br/><br/>
        <textarea name="description" placeholder="${empty newExperience.description ? "Experience description" : "" }">${newExperience.description}</textarea>
        <br/><br/>
        <input type="submit" value="Add" />
    </form>
    
</div>
    
<div class="col-sm-3">
</div>