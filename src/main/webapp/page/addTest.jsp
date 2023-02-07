<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<style><%@include file="../style/settings.css"%></style>
<head>
    <title>Quick Test: Quiz</title>
</head>
<div class="wrapper bg-white mt-sm-5 ">
    <h4 class="pb-4 border-bottom d-flex align-items-center justify-content-center"><fmt:message key="AddTestCreating" /></h4>
     <section class="vh-10">
              <div class="container py-1 h-10">
                <div class="row d-flex align-items-center justify-content-center h-10">
                  <div class="col-md-10 col-lg-10 col-xl-10 offset-xl-1">
                    <form action="/create" method="post">
                    <div class="form-outline mb-2">
                       <input name="test-name" type="text" id="form1Example12" class="form-control form-control-lg" value=""/>
                       <label class="form-label" for="form1Example12"><fmt:message key="AddTestName" /></label>
                    </div>
                      <div class="form-outline mb-2">
                        <input name="test-subject" type="text" id="form1Example13" class="form-control form-control-lg" value=""/>
                        <label class="form-label" for="form1Example13"><fmt:message key="AddTestSubject" /></label>
                      </div>
                      <div class="form-outline mb-2">
                          <input name="test-time" type="text" id="form1Example13" class="form-control form-control-lg" value=""/>
                          <label class="form-label" for="form1Example13"><fmt:message key="AddTestTime" /></label>
                       </div>
                      <div class="form-outline mb-2">
                         <select name="test-level" class="form-select form-select-md" aria-label=".form-select-lg example">
                               <option value="1" selected><fmt:message key="AddTestTestLevelEasy" /></option>
                               <option value="2"><fmt:message key="AddTestTestLevelMedium" /></option>
                               <option value="3"><fmt:message key="AddTestTestLevelHard" /></option>
                         </select>
                         <label class="form-label" for="test-level"><fmt:message key="AddTestTestLevel" /></label>
                      </div>
                        <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
                        <div class="py-3 d-flex align-items-center justify-content-center">
                                    <button class="btn btn-success mr-3"><fmt:message key="AddTestCreate" /></button>
                                     <button type="button" class="btn border btn-secondary" onClick="window.location.reload(true)"><fmt:message key="AddTestCancel" /></button>
                                </div>
                    </form>
                    <div class="d-sm-flex align-items-center pt-3" id="addquestion">
                        <div>
                            <b><fmt:message key="AddTestWarning" /></b>
                            <p><fmt:message key="AddTestGoLast" /></p>
                        </div>
                        <div class="ml-auto">
                            <button type="button" onclick="location.href='/edit/${requestScope.lastAddedTest.id}'" type="button" class="btn btn btn-success"><fmt:message key="AddTestEdit" /></button>
                        </div>
                    </div>
                  </div>
                </div>
              </div>
            </section>
    </div>
</div>