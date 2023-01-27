<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<style><%@include file="../style/settings.css"%></style>
<head>
    <title>Quick Test: Edit test</title>
</head>
<div class="wrapper bg-white mt-sm-5 ">
    <h4 class="pb-4 d-flex align-items-center justify-content-center">Test editing</h4>
     <section class="vh-10">
              <div class="container py-1 h-10">
                <div class="row d-flex align-items-center justify-content-center h-10">
                  <div class="col-md-10 col-lg-10 col-xl-10 offset-xl-1">
                    <form action="/edit/${requestScope.currentTest.id}" method="post">
                    <div class="form-outline mb-2">
                       <input name="test-name" type="text" id="form1Example12" class="form-control form-control-lg" value="${requestScope.currentTest.name}"/>
                       <label class="form-label" for="form1Example12">Test name</label>
                    </div>
                      <div class="form-outline mb-2">
                        <input name="test-subject" type="text" id="form1Example13" class="form-control form-control-lg" value="${requestScope.currentTest.subject}"/>
                        <label class="form-label" for="form1Example13">Test subject</label>
                      </div>
                      <div class="form-outline mb-2">
                          <input name="test-time" type="text" id="form1Example13" class="form-control form-control-lg" value="${requestScope.currentTest.time}"/>
                          <label class="form-label" for="form1Example13">Test time(minutes)</label>
                       </div>
                      <div class="form-outline mb-2">
                         <select name="test-level" class="form-select form-select-md" aria-label=".form-select-lg example">
                         <c:choose>
                               <c:when test="${requestScope.currentTest.level.id == '1'}">
                                   <option value="1" selected>Easy</option>
                                   <option value="2">Medium</option>
                                   <option value="3">Hard</option>
                               </c:when>
                               <c:when test="${requestScope.currentTest.level.id == '2'}">
                                    <option value="1">Easy</option>
                                    <option value="2" selected>Medium</option>
                                    <option value="3">Hard</option>
                               </c:when>
                               <c:otherwise>
                                   <option value="1">Easy</option>
                                   <option value="2">Medium</option>
                                   <option value="3" selected>Hard</option>
                               </c:otherwise>
                           </c:choose>
                         </select>
                         <label class="form-label" for="test-level">Test level</label>
                      </div>
                  <c:choose>
                         <c:when test="${requestScope.currentTest.isClose == '2'}">
                            <div class="form-outline mb-2">
                                  <div class="input-group-prepend">
                                      <input value="2" type="radio" class="btn-check" name="test-close" id="1primary-outlined" autocomplete="off" checked><label id="label-radio" class="btn btn-outline-danger col-6" for="1primary-outlined"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-lock-fill" viewBox="0 0 16 16">
                                      <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"/></svg></label>
                                      <input value="0" type="radio" class="btn-check" name="test-close" id="primary-outlined" autocomplete="off" disabled><label id="label-radio" class="btn btn-outline-success col-6" for="primary-outlined"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-unlock-fill" viewBox="0 0 16 16">
                                      <path d="M11 1a2 2 0 0 0-2 2v4a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V9a2 2 0 0 1 2-2h5V3a3 3 0 0 1 6 0v4a.5.5 0 0 1-1 0V3a2 2 0 0 0-2-2z"/></svg></label>
                                 </div>
                                  <span class="text-danger">You cannot change this option until you have added at least one question</span>
                                  <br>
                                 <label class="form-label" for="test-close">Closed/open</label>
                            </div>
                            </c:when>
                            <c:when test="${requestScope.currentTest.isClose == '1'}">
                                <div class="form-outline mb-2">
                                        <div class="input-group-prepend">
                                            <input value="1" type="radio" class="btn-check" name="test-close" id="1primary-outlined" autocomplete="off" checked><label id="label-radio" class="btn btn-outline-danger col-6" for="1primary-outlined"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-lock-fill" viewBox="0 0 16 16">
                                            <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"/></svg></label>
                                            <input value="0" type="radio" class="btn-check" name="test-close" id="primary-outlined" autocomplete="off"><label id="label-radio" class="btn btn-outline-success col-6" for="primary-outlined"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-unlock-fill" viewBox="0 0 16 16">
                                            <path d="M11 1a2 2 0 0 0-2 2v4a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V9a2 2 0 0 1 2-2h5V3a3 3 0 0 1 6 0v4a.5.5 0 0 1-1 0V3a2 2 0 0 0-2-2z"/></svg></label>
                                       </div>
                                       <label class="form-label" for="test-order">Closed/open</label>
                                </div>
                             </c:when>
                            <c:otherwise>
                                  <div class="form-outline mb-2">
                                         <div class="input-group-prepend">
                                             <input value="1" type="radio" class="btn-check" name="test-close" id="1primary-outlined" autocomplete="off"><label id="label-radio" class="btn btn-outline-danger col-6" for="1primary-outlined"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-lock-fill" viewBox="0 0 16 16">
                                             <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"/></svg></label>
                                             <input value="0" type="radio" class="btn-check" name="test-close" id="primary-outlined" autocomplete="off" checked><label id="label-radio" class="btn btn-outline-success col-6" for="primary-outlined"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-unlock-fill" viewBox="0 0 16 16">
                                             <path d="M11 1a2 2 0 0 0-2 2v4a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V9a2 2 0 0 1 2-2h5V3a3 3 0 0 1 6 0v4a.5.5 0 0 1-1 0V3a2 2 0 0 0-2-2z"/></svg></label>
                                        </div>
                                        <label class="form-label" for="test-order">Closed/open</label>
                                 </div>
                            </c:otherwise>
                    </c:choose>
                        <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
                        <div class="py-3 d-flex align-items-center justify-content-center">
                                    <button class="btn btn-success mr-3">Edit test</button>
                                    <button type="button" class="btn border btn-secondary" onClick="window.location.reload(true)">Cancel</button>
                                </div>
                    </form>
                    <div class="d-flex align-items-center pt-3" id="editquestion">
                     <div class="text-center">
                          <b>Change questions and answers or add new</b>
                      </div>
                        <div class="ml-auto">
                           <button class="btn btn btn-success" onclick="location.href = '/change/${requestScope.currentTest.id}?question=1';">Go to questions</button>
                        </div>
                    </div>
                  </div>
                </div>
              </div>
            </section>
    </div>
</div>