<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<head>
    <title>Quick Test: Sign up</title>
</head>

<section class="vh-100">
  <div class="container py-5 h-100">
    <div class="row d-flex align-items-center justify-content-center h-100">
      <div class="col-md-8 col-lg-7 col-xl-6">
        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
          class="img-fluid" alt="Phone image">
      </div>
      <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
        <form action="/signup" method="post">
        <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
            <h1><fmt:message key="SignUpTitle" /></h1>
         </div>
        <div class="form-outline mb-2">
           <input name="name" type="text" id="form1Example12" class="form-control form-control-lg" />
           <label class="form-label" for="form1Example12"><fmt:message key="SignUpName" /></label>
        </div>

          <div class="form-outline mb-2">
            <input name="email" type="text" id="form1Example13" class="form-control form-control-lg" />
            <label class="form-label" for="form1Example13"><fmt:message key="SignUpEmail" /></label>
          </div>

            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <label id="status-label" class="input-group-text" for="inputGroupSelect01"><fmt:message key="SignUpStatus" /></label>
              </div>
              <select name="role" class="custom-select" id="inputGroupSelect01">
                <option value="1"><fmt:message key="SignUpStudent" /></option>
                <option value="2"><fmt:message key="SignUpTeacher" /></option>
              </select>
            </div>

          <div class="form-outline mb-2">
            <input name="password" type="password" id="form1Example23" class="form-control form-control-lg" />
            <label class="form-label" for="form1Example23"><fmt:message key="SignUpPassword" /></label>
          </div>

          <div class="d-flex justify-content-around align-items-center mb-2">
            <a href="/login"><fmt:message key="SignUpAnswer" /></a>
          </div>
            <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
          <button id="btn" type="submit" class="btn btn-primary btn-lg btn-block"><fmt:message key="SingUpButton" /></button>
        </form>
      </div>
    </div>
  </div>
</section>
