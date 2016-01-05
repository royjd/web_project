<%-- 
    Document   : post
    Created on : Dec 28, 2015, 10:15:15 AM
    Author     : Karl Lauret
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="${pageContext.request.contextPath}/resources/js/limitlessPostScroll.js"></script>
<div class="row">
    <h2>Add a news</h2>
    <form method="post" class="form-horizontal" role="form" id="newsForm" action="${pageContext.request.contextPath}/${username}/addNews.htm">
        <div class="form-group">
            <div class="col-sm-12">
                <input  type="TEXT" class="form-control" name="title" rows="5" id="title" placeholder="Title here"></input>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-12">
                <textarea  type="TEXT" class="form-control" name="body" rows="5" id="body" placeholder="Type your message here"></textarea>
            </div>
        </div> 
        <div class="form-group">        
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-success pull-right">Submit</button>
            </div>
        </div>
    </form>


    <c:forEach var="i" begin="1" end="${post.size()}" step="1">
        <c:set var="tier" value="${post[i-1]}" />

        <c:if test="${tier.getClass().name != 'dao.CommentEntity'}"> 
            <div id="${tier.id}" class="post">
                <div  class= "col-sm-12">
                    ${tier.homeBootstrapDisplay}
                </div>
                <div class="comment col-sm-12">
                    ${tier.wallBootstrapCommentDisplay} 
                </div>
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
                            <div class="col-sm-12">
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
            </div>
            <div id="loadmore" path="/fanfare/ajax_wall_post/${username}/" class="loadmore">LOADING OMG</div>

        </c:if> 
    </c:forEach>



</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('.repplyCommentBtn').click(function () {
            $('.repplyCommentInput').remove();
            $(this).after(
                    '<div class="row repplyCommentInput">' +
                    '<form action="${pageContext.request.contextPath}/${username}/addComment.htm" method="POST" modelAttribute="newComment" class="form-horizontal">' +
                    '<input style="display:none" path="postMain.id" name="postMainId" id="postMainId" value="' + $(this).attr("mainid") + '"/>' +
                    '<input style="display:none" path="postParent.id" name="postParentId" id="postParentId" value="' + $(this).attr("parentid") + '"/>' +
                    '<div class="col-xs-12">' +
                    '<input path="body" class="form-control" id="bodyCommet" name="bodyCommet" placeholder="Type your comment here!" />' +
                    '</div>' +
                    '<div class="form-group">' +
                    '<div class="col-xs-12">' +
                    '<button type="submit" class="btn btn-success pull-right">Send</button>' +
                    '</div>' +
                    '</div>' +
                    '</form>' +
                    '</div>'

                    );

        });
    });
</script>