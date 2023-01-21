<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
            <h1>Sign up</h1>
         </div>
        <div class="form-outline mb-2">
           <input name="name" type="text" id="form1Example12" class="form-control form-control-lg" />
           <label class="form-label" for="form1Example12">Your name</label>
        </div>

          <div class="form-outline mb-2">
            <input name="email" type="text" id="form1Example13" class="form-control form-control-lg" />
            <label class="form-label" for="form1Example13">Email address</label>
          </div>

            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <label id="status-label" class="input-group-text" for="inputGroupSelect01">Status</label>
              </div>
              <select name="role" class="custom-select" id="inputGroupSelect01">
                <option value="1">Student</option>
                <option value="2">Teacher</option>
              </select>
            </div>

          <div class="form-outline mb-2">
            <input name="password" type="password" id="form1Example23" class="form-control form-control-lg" />
            <label class="form-label" for="form1Example23">Password</label>
          </div>

          <div class="d-flex justify-content-around align-items-center mb-2">
            <a href="/login">Already registered?</a>
          </div>
            <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
          <button id="btn" type="submit" class="btn btn-primary btn-lg btn-block">Sign up</button>
        </form>
      </div>
    </div>
  </div>
</section>
