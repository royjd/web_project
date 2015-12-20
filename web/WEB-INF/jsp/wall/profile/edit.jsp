<%-- 
    Document   : edit
    Created on : 19 déc. 2015, 10:37:48
    Author     : zakaridia
--%>

<div class="col-sm-2">              
    <div class="list-group">
        <a href="#" class="list-group-item disabled">Resumé </a>
        <a href="#" class="list-group-item">Experiences</a>
    </div>
</div>

<div class="col-sm-7">
    <h3>Edition du profile</h3>
    <form action ="${pageContext.request.contextPath}/profile/edit.htm" method="POST">
        <input name = "id" value="${editUser.id}" hidden/>
        <input name = "profile.id" value="${editUser.profile.id}" hidden/>
        <input name = "physical.id" value="${editUser.profile.physical.id}" hidden/>
        <br/>
        <input name="profile.firstName" type="text" value="${editUser.profile.firstName}" />
        <input name="profile.lastName" type="text" value="${editUser.profile.lastName}"/>
        <br/><br/>
        <input name="profile.phone" type ="text" placeholder="${empty editUser.profile.phone ? "Mobile Number" : "" }" value="${editUser.profile.phone}"/>
        <br/><br/>

        <input name="" />
        <br/><br/>
        <select name ="profile.physical.gender">

            ${ empty editUser.profile.physical.gender ? 
               "<option value=\"\"  >Select Gender</option>" : ""
            }
            
            <option value="M"  ${editUser.profile.physical.gender=="M" ? "selected" : ""}>Homme</option>
            <option value="F"  ${editUser.profile.physical.gender=="F" ? "selected" : ""}>Femme</option>
        </select>
        <br/><br/>
        <input name="profile.physical.height" value="${editUser.profile.physical.height}" placeholder="${empty editUser.profile.physical.height ? "Height" : editUser.profile.physical.height}" />
        <input name="profile.physical.weight" value="${editUser.profile.physical.weight}" placeholder="${empty editUser.profile.physical.weight ? "Weight" : editUser.profile.physical.weight}" />
        <br/><br/>
        <textarea name="profile.description" placeholder="${empty editUser.profile.description ? "Your description" : ""}">${editUser.profile.description}</textarea>
        <br/><br/>
        <input type="submit">
    </form>
</div>
    
<div class="col-sm-3">

</div>