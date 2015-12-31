<%-- 
    Document   : friendRequest
    Created on : Dec 10, 2015, 4:18:29 PM
    Author     : Karl Lauret
--%>
<div class="row">
    <div class="col-sm-12">
        <div class="bg-primary">
            <h2>Friend Request</h2>
            <c:forEach items="${friendsToAccept}" var="tier" >
                <a href="#" class="bg-info"> @${tier.owner.username} </a>
                <a href="acceptFriendship.htm?id=${tier.owner.id}" class="bg-info"> Accept </a>
                <a href="deniedFriendship.htm?id=${tier.owner.id}" class="bg-info"> Denied </a>

            </c:forEach>  
        </div>
    </div>
</div>

