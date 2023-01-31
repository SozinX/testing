<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<style><%@include file="../style/settings.css"%></style>
<head>
    <title>Quick Test: Testing</title>
</head>
<div class="mx-auto bg-white mt-lg-5 col-6">
    <h4 class="py-4 border-bottom d-flex align-items-center justify-content-center">${requestScope.currentQuestion.test.name}</h4>
     <section class="vh-10">
              <div class="container mx-0 py-1 h-10">
                <div class="row d-flex align-items-center justify-content-center h-10">
                  <div class="col-md-12 col-lg-12 col-xl-12 offset-xl-1">
                    <form action="/testing/${requestScope.currentQuestion.test.id}?question=${requestScope.currentPage}" method="post" class="me-3">
                      <input id="timer" name="timer" value="${sessionScope.time}" hidden>
                       <input id="testId" value="${sessionScope.testId}" hidden>
                       <input id="countDown" class = "form-control form-control-lg text-center" disabled>
                       <input id="currentPage" value="${requestScope.currentPage}" hidden>
                       <input id="hours" value="${sessionScope.hour}" hidden>
                       <input id="minutes" value="${sessionScope.minutes}" hidden>
                       <input id="seconds" value="${sessionScope.seconds}" hidden>
                       <input name="questionId" value="${requestScope.currentQuestion.id}" hidden>
                          <div class="mb-3 border-bottom">
                          <label for="exampleFormControlTextarea1" class="form-label">Question</label>
                            <h4 class="word-wrap">${requestScope.currentQuestion.question}</h4>
                          </div>
                          <div class="form-outline mb-2">
                             <c:choose>
                                <c:when test="${requestScope.currentQuestion.type.id == '1'}">
                                <c:forEach items="${requestScope.currentAnswers}" var="answer">
                                    <div class="col-sm-12 mt-2">
                                      <div class="input-group-prepend me-5">
                                            <input value="${answer.id}" type="radio" class="btn-check mx-auto" id="answer${answer.id}" name="answerCheck">
                                            <label id="answer" class="btn btn-outline-success col-8 mx-auto rounded-pill" for="answer${answer.id}">${answer.answer}</label>
                                         </div>
                                  </div>
                                  </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${requestScope.currentAnswers}" var="answer">
                                        <div class="col-sm-12 mt-2">
                                            <div class="input-group-prepend me-5">
                                                   <input type="checkbox" class="btn-check mx-auto" value="${answer.id}" id="answer${answer.id}" name="answerCheck">
                                                   <label id="answer" class="btn btn-outline-primary col-8 mx-auto rounded-pill" for="answer${answer.id}">${answer.answer}</label><br>
                                             </div>
                                         </div>
                                      </c:forEach>
                                      <h5 class="pt-4 d-flex align-items-center justify-content-center me-5">(Select two or more)</h5>
                                </c:otherwise>
                             </c:choose>

                          </div>
                            <div class="py-3 d-flex align-items-center justify-content-center me-5">
                                  <button class="btn btn-success mr-3">Save answer and go to next question</button>
                                  <button type='button' class="btn border btn-secondary" onclick="location.href = '/result/${requestScope.currentQuestion.test.id}';">Finish test</button>
                            </div>

                      </form>
                  </div>
                </div>
              </div>
            </section>
</div>
<script type="text/javascript"><%@include file="../script/timer.js"%></script>