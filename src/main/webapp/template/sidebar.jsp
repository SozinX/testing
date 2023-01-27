<style><%@include file="../style/sidebar.css"%></style>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="sidebar">
     <hr style="margin-top: -2px;">
     <form action="/tests" method="get">
     <input hidden name="page" value="1">
         <div class="form-group column"">
             <div class="col-sm-12 mt-5">
                 <label class="form-label" for="test-name">Test name</label>
                 <input name="test-name" type="text" class="form-control" placeholder="Search by test name...">
             </div>
             <div class="col-sm-12 mt-2">
                 <label class="form-label" for="test-subject">Test subject</label>
                 <input name="test-subject" type="text" id="test" class="form-control" placeholder="Search by test subject...">
             </div>
             <div class="col-sm-12 mt-2">
                 <label class="form-label" for="test-level">Test level</label>
                 <select name="test-level" class="form-select form-select-md mb-3" aria-label=".form-select-lg example">
                   <option value="0"selected>Level...</option>
                   <option value="1">Easy</option>
                   <option value="2">Medium</option>
                   <option value="3">Hard</option>
                 </select>
             </div>
             <div class="col-sm-12 mt-2">
              <label class="form-label" for="test-sort">Sorting by</label>
             <select name="test-sort" class="form-select" size="5" multiple aria-label="multiple select example">
               <option value="name"selected>Name</option>
               <option value="subject">Subject</option>
               <option value="finished">Popularity</option>
               <option value="created">Date</option>
               <option value="level">Level</option>
             </select>
             </div>
             <div class="col-sm-12 mt-2">
                 <label class="form-label" for="test-order">Order by</label>
                 <div class="input-group-prepend">
                   <input value="1" type="radio" class="btn-check" name="test-order" id="1primary-outlined" autocomplete="off" checked>
                   <label id="label-radio" class="btn btn-outline-success col-6" for="1primary-outlined">ASC</label>
                   <input value="0" type="radio" class="btn-check" name="test-order" id="primary-outlined" autocomplete="off">
                   <label id="label-radio" class="btn btn-outline-success col-6" for="primary-outlined">DESC</label>
                   </div>
             </div>
             <div class="col-sm-12 mt-2">
                 <button id="form-btn" type="submit" class="btn btn-success btn-lg btn-block">FIND</button>
             </div>
        </div>
        </form>
</div>
