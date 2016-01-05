<%-- 
    Document   : post
    Created on : Jan 4, 2016, 11:07:07 PM
    Author     : Karl Lauret
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${posts.size() !=0}"> 
    <c:forEach var="i" begin="1" end="${posts.size()}" step="1">
        <c:set var="tier" value="${posts[i-1]}" />

        <c:if test="${tier.getClass().name != 'dao.CommentEntity'}"> 
            <div id="${tier.id}" class="post">
                <div id="${tier.id}" class="post col-sm-12">
                    ${tier.homeBootstrapDisplay}
                </div>
                <div class="comment col-sm-12">
                    ${tier.wallBootstrapCommentDisplay} 
                </div>
                <form:form action="${pageContext.request.contextPath}/addComment.htm" method="POST" modelAttribute="newComment" class="form-horizontal">

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
        </c:if> 
    </c:forEach>
</c:if>  