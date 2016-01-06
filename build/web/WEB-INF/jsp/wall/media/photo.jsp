<%-- 
    Document   : post
    Created on : Dec 28, 2015, 10:15:15 AM
    Author     : Karl Lauret
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="postMedia col-sm-12 bg-success">
    <c:forEach var="i" begin="1" end="${post.size()}" step="1">
        <c:set var="tier" value="${post[post.size()-i]}" />

        <c:if test="${tier.getClass().name == 'dao.MediaEntity'}"> 
            <div class="col-xs-12 col-sm-6 col-md-3">

                <jsp:include page="../../commun/displayPhoto.jsp" >
                    <jsp:param name="link" value="${tier.mediaType.link}" />
                    <jsp:param name="title" value="${tier.title}" />
                    <jsp:param name="body" value="${tier.body}" />
                </jsp:include>

            </div>

            <!--<form:form action="${pageContext.request.contextPath}/${username}/media/addPhoto.htm" method="POST" modelAttribute="newComment" class="form-horizontal">

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
            </form:form>-->
        </c:if> 
    </c:forEach>
</div>



<script type="text/javascript">
    $(document).ready(function () {
        $('.repplyCommentBtn').click(function () {
            $('.repplyCommentInput').remove();
            $(this).after(
                    '<div class="row repplyCommentInput">' +
                    '<form action="${pageContext.request.contextPath}/${username}/media/addPhoto.htm" method="POST" modelAttribute="newComment" class="form-horizontal">' +
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
<script>
    $(".img-overlay").hover(
            function () {
                $(this).find('.text').show();
            }, function () {
        $(this).find('.text').hide();
    }
    );
    $(".text").hover(
            function () {
                $(this).show();
            }, function () {

    }
    );
    /*
     $( "img.fade" ).hover(function() {
     $( this ).closest('.img-overlay').next('.text').fadeOut( 100 );
     $( this ).closest('.img-overlay').next('.text').fadeIn( 500 );
     });*/
</script>