<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<style><%@include file="../style/settings.css"%></style>
<%@include file="../template/sidebarEditQuestions.jsp"%>
<head>
    <title>Quick Test: Edit question</title>
</head>
<div class="mx-auto bg-white mt-lg-5 col-8">
    <h4 class="py-4 border-bottom d-flex align-items-center justify-content-center"><fmt:message key="EditQuestionTitle" /></h4>
     <section class="vh-10">
              <div class="container mx-0 py-1 h-10">
                <div class="row d-flex align-items-center justify-content-center h-10">
                  <div class="col-md-12 col-lg-12 col-xl-12 offset-xl-1">
                    <form action="/change/${requestScope.currentTest.id}?question=${requestScope.currentPage}" method="post">
                        <input name="questionId" value="${requestScope.currentQuestion.id}" hidden>
                    <div class="form-outline mb-2">
                       <input name="test-name" type="text" id="form1Example12" class="form-control form-control-lg" value="${requestScope.currentQuestion.test.name}" disabled>
                       <label class="form-label" for="form1Example12"><fmt:message key="EditQuestionTestName" /></label>
                    </div>
                      <div class="mb-3">
                        <textarea name="questionText" class="form-control" id="exampleFormControlTextarea1" rows="3">${requestScope.currentQuestion.question}</textarea>
                        <label for="exampleFormControlTextarea1" class="form-label"><fmt:message key="EditQuestionQuestion" /></label>
                      </div>
                      <div class="form-outline mb-2">
                           <select name="questionType" class="form-select form-select-md" aria-label=".form-select-lg example">
                           <c:choose>
                            <c:when test="${requestScope.currentQuestion.type.id == '1'}">
                                 <option value="1" selected><fmt:message key="EditQuestionOneCorrectAnswer" /></option>
                                 <option value="2"><fmt:message key="EditQuestionManyCorrectAnswers" /></option>
                                 </c:when>
                                 <c:otherwise>
                                 <option value="1"><fmt:message key="EditQuestionOneCorrectAnswer" /></option>
                                 <option value="2" selected><fmt:message key="EditQuestionManyCorrectAnswers" /></option>
                                 </c:otherwise>
                            </c:choose>
                           </select>
                           <label class="form-label" for="questionType"><fmt:message key="EditQuestionType" /></label>
                        </div>
                        <h4 class="py-4 border-bottom d-flex align-items-center justify-content-center"><fmt:message key="EditQuestionAnswersUpdating" /></h4>
                        <div id="newInput">
                        <c:set var="count" value="1" scope="page" />
                        <input id="deleteCount" value="1" hidden>
                        <c:forEach items="${requestScope.currentAnswers}" var="answer">
                        <input id="up${count}i" name="up${count}i" value="${answer.id}" hidden>
                        <div id="up${count}" class="py-3 d-flex align-items-center justify-content-center">
                             <select name="qtu${count}"  id="select${count}" class="form-select form-select-md col-4" aria-label=".form-select-lg example">
                                     <c:choose>
                                        <c:when test="${answer.correctness == '1'}">
                                             <option value="0"><fmt:message key="EditQuestionNotCorrect" /></option>
                                              <option value="1" selected><fmt:message key="EditQuestionCorrect" /></option>
                                             </c:when>
                                             <c:otherwise>
                                                 <option value="0" selected><fmt:message key="EditQuestionNotCorrect" /></option>
                                                 <option value="1"><fmt:message key="EditQuestionCorrect" /></option>
                                             </c:otherwise>
                                        </c:choose>
                              </select>
                              <input name="au${count}" type="text" id="input${count}" class="form-control form-control-lg" value="${answer.answer}">
                              <button class="btn btn-danger" id="DeleteAnswer" onClick="deleteAnswer('${count}')" type="button"><b>X</b></button>
                        </div>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        </c:forEach>
                        </div>
                        <input id="answers" value="${count}" disabled hidden>
                        <input id="iterations" name="iterations" value="${count}" hidden>
                        <button id="answerAdder" type="button" class="btn btn-outline-success col-md-12 col-lg-12 col-xl-12 pt-2 mt-3"><fmt:message key="EditQuestionAddAnswer" /></button>
                        <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
                              <div class="py-3 d-flex align-items-center justify-content-center">
                              <button class="btn btn-success mr-3"><fmt:message key="EditQuestionSave" /></button>
                              <button type='button' class="btn border btn-secondary" onClick="window.location.reload(true)"><fmt:message key="EditQuestionCancel" /></button>
                        </div>

                      </form>

                      <div class="pb-4 pe-3 border-bottom d-flex align-items-center justify-content-center">

                      </div>

                  </div>
                </div>
              </div>
            </section>
            <form>
    <div class="pt-3" id="delete">
        <div class="text-center">
            <b><fmt:message key="EditQuestionDeleteQuestion" /><br> </b>
            <span><fmt:message key="EditQuestionDeleteQuestionInfo" /></span>
        </div>
            <div class="pb-3 mt-1 d-flex justify-content-center">
            <button class="btn danger" type="button" onclick="location.href = '/delete/${requestScope.currentTest.id}?question=${requestScope.currentPage}';"><fmt:message key="EditQuestionDelete" /></button>
        </div>
    </div>
</div>
</form>
<script type="text/javascript"><%@include file="../script/editAnswer.js"%></script>