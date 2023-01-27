<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<style><%@include file="../style/settings.css"%></style>
<%@include file="../template/sidebarEditQuestions.jsp"%>
<head>
    <title>Quick Test: Add question</title>
</head>
<div class="mx-auto bg-white mt-lg-5 col-8">
    <h4 class="py-4 border-bottom d-flex align-items-center justify-content-center">Question updating</h4>
     <section class="vh-10">
              <div class="container mx-0 py-1 h-10">
                <div class="row d-flex align-items-center justify-content-center h-10">
                  <div class="col-md-12 col-lg-12 col-xl-12 offset-xl-1">
                    <form action="/change/${requestScope.currentTest.id}?question=${requestScope.currentPage}" method="post">
                        <input name="questionId" value="${requestScope.currentQuestion.id}" hidden>
                    <div class="form-outline mb-2">
                       <input name="test-name" type="text" id="form1Example12" class="form-control form-control-lg" value="${requestScope.currentQuestion.test.name}" disabled>
                       <label class="form-label" for="form1Example12">Test name</label>
                    </div>
                      <div class="mb-3">
                        <textarea name="questionText" class="form-control" id="exampleFormControlTextarea1" rows="3">${requestScope.currentQuestion.question}</textarea>
                        <label for="exampleFormControlTextarea1" class="form-label">Question</label>
                      </div>
                      <div class="form-outline mb-2">
                           <select name="questionType" class="form-select form-select-md" aria-label=".form-select-lg example">
                           <c:choose>
                            <c:when test="${requestScope.currentQuestion.type.id == '1'}">
                                 <option value="1" selected>One correct answer</option>
                                 <option value="2">Many correct answers</option>
                                 </c:when>
                                 <c:otherwise>
                                 <option value="1">One correct answer</option>
                                 <option value="2" selected>Many correct answers</option>
                                 </c:otherwise>
                            </c:choose>
                           </select>
                           <label class="form-label" for="questionType">Question type</label>
                        </div>
                        <h4 class="py-4 border-bottom d-flex align-items-center justify-content-center">Answers updating</h4>
                        <div id="newInput">
                        <c:set var="count" value="1" scope="page" />
                        <input id="deleteCount" value="1" hidden>
                        <c:forEach items="${requestScope.currentAnswers}" var="answer">
                        <input id="up${count}i" name="up${count}i" value="${answer.id}" hidden>
                        <div id="up${count}" class="py-3 d-flex align-items-center justify-content-center">
                             <select name="qtu${count}"  id="select${count}" class="form-select form-select-md col-4" aria-label=".form-select-lg example">
                                     <c:choose>
                                        <c:when test="${answer.correctness == '1'}">
                                             <option value="0">Not correct</option>
                                              <option value="1" selected>Correct</option>
                                             </c:when>
                                             <c:otherwise>
                                                 <option value="0" selected>Not correct</option>
                                                 <option value="1">Correct</option>
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
                        <button id="answerAdder" type="button" class="btn btn-outline-success col-md-12 col-lg-12 col-xl-12 pt-2 mt-3">Add one more answer for this question(max 10 in total)</button>
                        <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
                              <div class="py-3 d-flex align-items-center justify-content-center">
                              <button class="btn btn-success mr-3">Save question</button>
                              <button type='button' class="btn border btn-secondary" onClick="window.location.reload(true)">Cancel</button>
                        </div>

                      </form>

                      <div class="pb-4 pe-3 border-bottom d-flex align-items-center justify-content-center">

                      </div>

                  </div>
                </div>
              </div>
            </section>
            <form action="/delete/${requestScope.currentTest.id}?question=${requestScope.currentPage}">
    <div class="pt-3" id="delete">
        <div class="text-center">
            <b>Delete question?<br> </b>
            <span>This question and all answers will be deleted</span>
        </div>
            <div class="pb-3 mt-1 d-flex justify-content-center">
            <button class="btn danger">Delete</button>
        </div>
    </div>
</div>
</form>
<script type="text/javascript"><%@include file="../script/editAnswer.js"%></script>