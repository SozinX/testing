<style><%@include file="../style/sidebar.css"%></style>
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="sidebar">
     <hr style="margin-top: -2px;">
     <form action="/tests" method="get">
     <input hidden name="page" value="1">
         <div class="form-group column"">
             <div class="col-sm-12 mt-5">
                 <label class="form-label" for="test-name"><fmt:message key="SidebarTestName" /></label>
                 <input name="test-name" type="text" class="form-control" placeholder=<fmt:message key="SidebarTestNamePlaceholder" />>
             </div>
             <div class="col-sm-12 mt-2">
                 <label class="form-label" for="test-subject"><fmt:message key="SidebarTestSubject" /></label>
                 <input name="test-subject" type="text" id="test" class="form-control" placeholder=<fmt:message key="SidebarTestSubjectPlaceholder" />>
             </div>
             <div class="col-sm-12 mt-2">
                 <label class="form-label" for="test-level"><fmt:message key="SidebarTestLevel" /></label>
                 <select name="test-level" class="form-select form-select-md mb-3" aria-label=".form-select-lg example">
                   <option value="0"selected><fmt:message key="SidebarTestLevel" />...</option>
                   <option value="1"><fmt:message key="SidebarTestLevelEasy" /></option>
                   <option value="2"><fmt:message key="SidebarTestLevelMedium" /></option>
                   <option value="3"><fmt:message key="SidebarTestLevelHard" /></option>
                 </select>
             </div>
             <div class="col-sm-12 mt-2">
              <label class="form-label" for="test-sort"><fmt:message key="SidebarTestSorting" /></label>
             <select name="test-sort" class="form-select" size="5" multiple aria-label="multiple select example">
               <option value="name"selected><fmt:message key="SidebarTestSortingName" /></option>
               <option value="subject"><fmt:message key="SidebarTestSortingSubject" /></option>
               <option value="finished"><fmt:message key="SidebarTestSortingPopularity" /></option>
               <option value="created"><fmt:message key="SidebarTestSortingDate" /></option>
               <option value="level"><fmt:message key="SidebarTestSortingLevel" /></option>
             </select>
             </div>
             <div class="col-sm-12 mt-2">
                 <label class="form-label" for="test-order"><fmt:message key="SidebarTestOrder" /></label>
                 <div class="input-group-prepend">
                   <input value="1" type="radio" class="btn-check" name="test-order" id="1primary-outlined" autocomplete="off" checked>
                   <label id="label-radio" class="btn btn-outline-success col-6" for="1primary-outlined"><fmt:message key="SidebarASC" /></label>
                   <input value="0" type="radio" class="btn-check" name="test-order" id="primary-outlined" autocomplete="off">
                   <label id="label-radio" class="btn btn-outline-success col-6" for="primary-outlined"><fmt:message key="SidebarDESC" /></label>
                   </div>
             </div>
             <div class="col-sm-12 mt-2">
                 <button id="form-btn" type="submit" class="btn btn-success btn-lg btn-block"><fmt:message key="SidebarTestFind" /></button>
             </div>
        </div>
        </form>
</div>
