<%-- 
    Document   : friendList
    Created on : Dec 10, 2015, 4:20:14 PM
    Author     : Karl Lauret
--%>

<div class="row">
    <div class="col-sm-12">
        <div class="bg-primary">
            <h2>Friend List</h2>
            <c:forEach items="${friends}" var="tier" >
                <c:if test="${tier.accepted}">
                    <c:choose> 
                        <c:when test="${tier.friend.email == user.email}"> 
                            <a href="#" class="bg-info"> ${tier.owner.email} </a>
                        </c:when>
                        <c:otherwise>
                            <a href="#" class="bg-info"> ${tier.friend.email} </a>
                        </c:otherwise>
                    </c:choose>
                    <a href="removeFriend.htm?id=${tier.id}" class="bg-info"> Remove </a>
                </c:if>

            </c:forEach>  

        </div>

    </div>
</div>