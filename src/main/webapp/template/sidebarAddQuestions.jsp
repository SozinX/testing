<style><%@include file="../style/sidebar.css"%></style>
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="sidebar">
     <hr style="margin-top: -6px;">
         <div class="form-group column center my-5">
                 <div class="col-sm-12 mt-2">
                    <button id="form-btn" onclick="location.href = '/edit/${requestScope.currentTest.id}';" type="button" class="btn btn-success btn-lg btn-block"><fmt:message key="SidebarAddQuestionEdit" /></button>
                 </div>
                 <div class="col-sm-12 mt-2">
                    <button id="form-btn" onclick="location.href = '/change/${requestScope.currentTest.id}?question=1';" type="button" class="btn btn-success btn-lg btn-block"><fmt:message key="SidebarAddQuestionChange" /></button>
                 </div>
        </div>
</div>
