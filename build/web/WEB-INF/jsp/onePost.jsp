<%-- 
    Document   : onePost
    Created on : Jan 5, 2016, 11:28:18 AM
    Author     : Karl Lauret
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
<c:if test="${post.getClass().name != 'dao.CommentEntity'}"> 
    <div id="${post.id}" class="post">
        <div id="${post.id}" class="post col-sm-12">
            ${post.homeBootstrapDisplay}
        </div>
        <div class="comment col-sm-12">
            ${post.wallBootstrapCommentDisplay} 
        </div>
        <form:form action="${pageContext.request.contextPath}/notification/${urlForComment}/addComment.htm" method="POST" modelAttribute="newComment" class="form-horizontal">

            <spring:bind path="postMain.id">
                <form:hidden path="postMain.id" id="postMainId" value="${post.id}"/>
            </spring:bind>

            <spring:bind path="postParent.id">
                <form:hidden path="postParent.id" id="postParentId" value="${post.id}"/>
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
</c:if> 
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('.repplyCommentBtn').click(function () {
            $('.repplyCommentInput').remove();
            $(this).after(
                    '<div class="row repplyCommentInput">' +
                    '<form action="${pageContext.request.contextPath}/notification/${urlForComment}/addComment.htm" method="POST" modelAttribute="newComment" class="form-horizontal">' +
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
        $("#addAnews").click(function () {
            $("#addAnewsDiv").toggle();
            if ($(this).hasClass("glyphicon-plus")) {

                $(this).removeClass("glyphicon-plus btn-primary").addClass("btn-danger glyphicon-remove");
            } else {
                $(this).removeClass("btn-danger glyphicon-remove").addClass("glyphicon-plus btn-primary");
            }
        });

        

    });



</script>