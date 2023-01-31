<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<style><%@include file="../style/settings.css"%></style>
<head>
    <title>Quick Test: Test</title>
</head>
<div class="wrapper bg-white mt-sm-5 ">
    <h2 class="pb-4 d-flex align-items-center justify-content-center border-bottom">Test</h2>
     <section class="vh-10">
              <div class="container py-1 h-10">
                <div class="row d-flex align-items-center justify-content-center h-10">
                  <div class="col-md-10 col-lg-10 col-xl-10 offset-xl-1">
                    <form action="/view/${requestScope.currentTest.id}" method="post">
                    <div class="form-outline mb-2">
                    <label class="form-label" for="form1Example12">Test name</label>
                       <h3 class="pb-4 d-flex align-items-center justify-content-center">${requestScope.currentTest.name}</h3>

                    </div>
                    <div class="form-outline mb-2">
                    <label class="form-label" for="form1Example13">Test author</label>
                        <h4 class="pb-4 d-flex align-items-center justify-content-center">${requestScope.currentTest.owner.name}</h4>

                      </div>
                      <div class="form-outline mb-2">
                      <label class="form-label" for="form1Example13">Test subject</label>
                        <h4 class="pb-4 d-flex align-items-center justify-content-center">${requestScope.currentTest.subject}</h4>

                      </div>
                      <div class="form-outline mb-2">
                       <label class="form-label" for="form1Example13">Test time(minutes)</label>
                          <h4 class="pb-4 d-flex align-items-center justify-content-center">${requestScope.currentTest.time}</h4>

                       </div>
                      <div class="form-outline mb-2">
                       <label class="form-label" for="test-level">Test level</label>
                         <select name="test-level" class="form-select form-select-md" aria-label=".form-select-lg example" disabled>
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

                      </div>
                       <div class="form-outline mb-2">
                       <label class="form-label" for="form1Example13">Your result</label>
                         <h4 class="pb-4 d-flex align-items-center justify-content-center">${requestScope.result}/100%</h4>

                      </div>
                        <div class="py-3 d-flex align-items-center justify-content-center">
                        <c:choose>
                               <c:when test="${sessionScope.id == null}">
                                   <button type="button" class="btn border btn-primary" onclick="location.href = '/login'">Log In</button>
                                   <button type="button" class="btn border btn-secondary" onclick="location.href = '/tests?page=1'">Back to tests</button>
                               </c:when>
                               <c:when test="${requestScope.result == '-'}">
                                  <button class="btn btn-success mr-3">Start test</button>
                                  <button type="button" class="btn border btn-secondary" onclick="location.href = '/tests?page=1'">Back to tests</button>
                              </c:when>
                              <c:otherwise>
                              <button type="button" class="btn border btn-secondary" onclick="location.href = '/tests?page=1'">Back to tests</button>
                              </c:otherwise>
                       </c:choose>
                                </div>
                    </form>
                   </div>
                </div>
              </div>
            </section>
    </div>
</div>