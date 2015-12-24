<%-- 
    Document   : edit
    Created on : 19 déc. 2015, 10:37:48
    Author     : zakaridia
--%>

<%@page import="org.springframework.validation.BindingResult"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-sm-7">
    <div class="page-header ">
        <h1><small>Edit your profile</small></h1>
    </div>
    <spring:url value="/profile/edit.htm" var = "userActionUrl" />

    <form:form action="${pageContext.request.contextPath}/${editUser.username}/profile/edit.htm" method="POST" modelAttribute="editUser" class="form-horizontal">
        <c:if test="${not empty msg}">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong>${msg}</strong>
            </div>
        </c:if>

        <spring:bind path="profile.firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                    <form:input path="profile.firstName" type="text" class="form-control " id="firstName" placeholder="firstName" />
                    <form:errors path="profile.firstName" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="profile.lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                    <form:input path="profile.lastName" class="form-control" id="lastName" placeholder="lastName" />
                    <form:errors path="profile.lastName" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="profile.physical.gender">
            <div class="form-group">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-4 selectContainer">
                    <form:select path="profile.physical.gender" class="form-control">
                        <form:option value="" label="Select gender" />
                        <form:option value="M" label="Male" />
                        <form:option value="F" label="Female" />
                    </form:select>
                </div>
            </div>

        </spring:bind>

        <spring:bind path="profile.phone">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                    <form:input path="profile.phone" class="form-control" id="phone" placeholder="Mobile number" />
                    <form:errors path="profile.phone" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="profile.birthDay">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                    <form:input path="profile.birthDay" class="form-control" id="birthDay" placeholder="Birth Day ( dd / mm / yyyy )" />
                    <form:errors path="profile.birthDay" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="profile.physical.height">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-4">
                    <form:input path="profile.physical.height" class="form-control" id="height" placeholder="Height" />
                    <form:errors path="profile.physical.height" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="profile.physical.weight">
            <div class="form-group  ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-4">
                    <form:input path="profile.physical.weight" class="form-control" id="weight" placeholder="Weight" />
                    <form:errors path="profile.physical.weight" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="profile.description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                    <form:textarea path="profile.description" class="form-control" id="birthDay" placeholder="Your description ..." />
                    <form:errors path="profile.description" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary pull-right">Save Changes</button>
            </div>
        </div>
    </form:form>

</div>
<div class="col-sm-3">

</div>