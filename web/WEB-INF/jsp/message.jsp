<%-- 
    Document   : message
    Created on : Dec 10, 2015, 4:37:59 PM
    Author     : Karl Lauret
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <form method="post" class="form-horizontal" role="form" action="sendMessage.htm">
                <div class="form-group">
                    <input type="TEXT" class="form-control" name="message" id="message" placeholder="message">
                </div>
                <div class="form-group">
                    <input type="TEXT" class="form-control" name="subject" id="subject" placeholder="subject">
                </div>

                <button type="submit" class="btn btn-success">Send</button>
            </form>
            <c:forEach items="${groupList}" var="groupMessage">
                <c:forEach items="${groupMessage.value}" var="tier" >
                    ${tier.user.email}
                </c:forEach>
                 <a href="/fanfare/message/${groupMessage.key}.htm">See messages</a>
            </c:forEach>
            <c:forEach items="${messages}" var="tier" >
                ${tier.content}
            </c:forEach>
        </div>
    </div>
</div>