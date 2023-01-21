<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<style><%@include file="../style/settings.css"%></style>
<head>
    <title>Quick Test: Quiz</title>
</head>
<div class="wrapper bg-white mt-sm-5 ">
    <h4 class="pb-4 border-bottom d-flex align-items-center justify-content-center">Test creating</h4>
     <section class="vh-10">
              <div class="container py-1 h-10">
                <div class="row d-flex align-items-center justify-content-center h-10">
                  <div class="col-md-10 col-lg-10 col-xl-10 offset-xl-1">
                    <form action="/create" method="post">
                    <div class="form-outline mb-2">
                       <input name="test-name" type="text" id="form1Example12" class="form-control form-control-lg" value=""/>
                       <label class="form-label" for="form1Example12">Test name</label>
                    </div>
                      <div class="form-outline mb-2">
                        <input name="test-subject" type="text" id="form1Example13" class="form-control form-control-lg" value=""/>
                        <label class="form-label" for="form1Example13">Test subject</label>
                      </div>
                      <div class="form-outline mb-2">
                          <input name="test-time" type="text" id="form1Example13" class="form-control form-control-lg" value=""/>
                          <label class="form-label" for="form1Example13">Test time(minutes)</label>
                       </div>
                      <div class="form-outline mb-2">
                         <select name="test-level" class="form-select form-select-md" aria-label=".form-select-lg example">
                               <option value="1" selected>Easy</option>
                               <option value="2">Medium</option>
                               <option value="3">Hard</option>
                         </select>
                         <label class="form-label" for="test-level">Test level</label>
                      </div>
                        <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
                        <div class="py-3 d-flex align-items-center justify-content-center">
                                    <button class="btn btn-success mr-3">Create test</button>
                                </div>
                    </form>
                    <div class="pb-4 pe-3 border-bottom d-flex align-items-center justify-content-center">
                        <button class="btn border btn-secondary" onClick="window.location.reload(true)">Cancel</button>
                    </div>
                    <div class="d-sm-flex align-items-center pt-3" id="addquestion">
                        <div>
                            <b>Your test will not be published until at least one question appears in it</b>
                            <p>Go to edit last added test?</p>
                        </div>
                        <div class="ml-auto">
                            <button onclick="location.href='#'" type="button" class="btn btn btn-success">Edit test</button>
                        </div>
                    </div>
                  </div>
                </div>
              </div>
            </section>
    </div>
</div>