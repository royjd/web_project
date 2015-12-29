<%-- 
    Document   : post
    Created on : Dec 28, 2015, 10:15:15 AM
    Author     : Karl Lauret
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form method="post" class="form-horizontal" role="form" id="newsForm" action="${pageContext.request.contextPath}/${username}/addNews.htm">
    <div class="form-group">
        <label class="control-label col-sm-2" for="message">Title:</label>
        <div class="col-sm-10">
            <input  type="TEXT" class="form-control" name="title" rows="5" id="title" placeholder="Title here"></input>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <textarea  type="TEXT" class="form-control" name="body" rows="5" id="body" placeholder="Type your message here"></textarea>
        </div>
    </div> 
    <div class="form-group">        
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success pull-right">Submit</button>
        </div>
    </div>
</form>
<row>


    <c:forEach var="i" begin="1" end="${post.size()}" step="1">
        <c:set var="tier" value="${post[post.size()-i]}" />

        <c:if test="${tier.getClass().name != 'dao.CommentEntity'}"> 
            <p class="col-sm-12 bg-primary">
                ${tier.homeBootstrapDisplay}
                ${tier.homeBootstrapDisplayComments} 
            </p>
            <form:form action="${pageContext.request.contextPath}/${username}/addComment.htm" method="POST" modelAttribute="newComment" class="form-horizontal">

                <spring:bind path="postMain.id">
                    <form:hidden path="postMain.id" id="postMainId" value="${tier.id}"/>
                </spring:bind>

                <spring:bind path="postParent.id">
                    <form:hidden path="postParent.id" id="postParentId" value="${tier.id}"/>
                </spring:bind>

                <spring:bind path="postParent.id">
                    <form:hidden path="postParent.id" id="postParentId"/>
                </spring:bind>


                <spring:bind path="body">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label"></label>
                        <div class="col-sm-10">
                            <form:input path="body" class="form-control" id="bodyCommet" placeholder="Type your comment here!" />
                            <form:errors path="body" class="control-label" />
                        </div>
                    </div>
                </spring:bind>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success pull-right">Send</button>
                    </div>
                </div>
            </form:form>
        </c:if> 
    </c:forEach>



</row>
<script type="text/javascript">
    $(document).ready(function () {
        $('.repplyCommentBtn').click(function () {
            $(this).after(
            '<form action="${pageContext.request.contextPath}/${username}/addComment.htm" method="POST" modelAttribute="newComment" class="form-horizontal">'+

                    '<input style="display:none" path="postMain.id" name="postMainId" id="postMainId" value="'+$(this).attr("mainid")+'"/>'+


                   '<input style="display:none" path="postParent.id" name="postParentId" id="postParentId" value="'+$(this).attr("parentid")+'"/>'+

                        '<div class="col-sm-10">'+
                            '<input path="body" class="form-control" id="bodyCommet" name="bodyCommet" placeholder="Type your comment here!" />'+
                        '</div>'+

                '<div class="form-group">'+
                    '<div class="col-sm-offset-2 col-sm-10">'+
                        '<button type="submit" class="btn btn-success pull-right">Send</button>'+
                    '</div>'+
                '</div>'+
            '</form>'
                
            );

        });
    });
</script>