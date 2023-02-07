<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<style><%@include file="../style/settings.css"%></style>
<%@include file="../template/sidebarAddQuestions.jsp"%>
<head>
    <title>Quick Test: Add question</title>
</head>
<div class="mx-auto bg-white mt-lg-5 col-8">
    <h4 class="py-4 border-bottom d-flex align-items-center justify-content-center"><fmt:message key="AddQuestionCreating" /></h4>
     <section class="vh-10">
              <div class="container mx-0 py-1 h-10">
                <div class="row d-flex align-items-center justify-content-center h-10">
                  <div class="col-md-12 col-lg-12 col-xl-12 offset-xl-1">
                    <form action="/add/${requestScope.currentTest.id}" method="post">
                    <div class="form-outline mb-2">
                       <input name="test-name" type="text" id="form1Example12" class="form-control form-control-lg" value="${requestScope.currentTest.name}" disabled>
                       <label class="form-label" for="form1Example12"><fmt:message key="AddQuestionTest" /></label>
                    </div>
                      <div class="mb-3">
                        <textarea name="question" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                        <label for="exampleFormControlTextarea1" class="form-label"><fmt:message key="AddQuestionQuestion" /></label>
                      </div>
                      <div class="form-outline mb-2">
                           <select name="question-type" class="form-select form-select-md" aria-label=".form-select-lg example">
                                 <option value="1" selected><fmt:message key="AddQuestionOneCorrectAnswer" /></option>
                                 <option value="2"><fmt:message key="AddQuestionManyCorrectAnswers" /></option>
                           </select>
                           <label class="form-label" for="question-type"><fmt:message key="AddQuestionType" /></label>
                        </div>
                        <input id="answers" value="2"  hidden>
                         <input id="iterations" name="iterations" value="2"  hidden>
                        <h4 class="py-4 border-bottom d-flex align-items-center justify-content-center"><fmt:message key="AddQuestionAnswersAdding" /></h4>
                        <div id="newInput">
                        <div class="py-3 d-flex align-items-center justify-content-center">
                             <select name="qt1" class="form-select form-select-md col-4" aria-label=".form-select-lg example">
                                    <option value="0" selected><fmt:message key="AddQuestionNotCorrect" /></option>
                                    <option value="1"><fmt:message key="AddQuestionCorrect" /></option>
                              </select>
                              <input name="a1" type="text" id="form1Example12" class="form-control form-control-lg" value="">
                        </div>
                        </div>
                        <button id="answerAdder" type="button" class="btn btn-outline-success col-md-12 col-lg-12 col-xl-12 pt-2 mt-3"><fmt:message key="AddQuestionAddAnswer" /></button>
                        <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
                              <div class="py-3 d-flex align-items-center justify-content-center">
                              <button class="btn btn-success mr-3"><fmt:message key="AddQuestionSave" /></button>
                              <button type='button' class="btn border btn-secondary" onClick="window.location.reload(true)"><fmt:message key="AddQuestionCancel" /></button>
                        </div>
                       <input id="iterations" name="iterations" value="2" hidden>
                      </form>

                      <div class="pb-4 pe-3 border-bottom d-flex align-items-center justify-content-center">

                      </div>

                  </div>
                </div>
              </div>
            </section>
</div>
<script type="text/javascript"><%@include file="../script/addAnswer.js"%></script>