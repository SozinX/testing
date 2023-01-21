<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/login.css"%></style>
<head>
    <title>Quick Test: Log in</title>
</head>
<section class="vh-100">
  <div class="container-fluid h-custom">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-md-9 col-lg-6 col-xl-5">
        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
          class="img-fluid" alt="Sample image">
      </div>
      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
        <form action="/login" method="post">
          <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
            <h1>Log in</h1>
          </div>

          <div class="form-outline mb-4">
              <input name="email" type="text" id="form1Example13" class="form-control form-control-lg" />
              <label class="form-label" for="form1Example13">Email address</label>
           </div>

           <div class="form-outline mb-4">
              <input name="password" type="password" id="form1Example23" class="form-control form-control-lg" />
              <label class="form-label" for="form1Example23">Password</label>
           </div>


          <div class="d-flex justify-content-around align-items-center mb-4">
             <a href="/signup">Donʼt have an account?</a>
          </div>
          <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
          <button id="btn" type="submit" class="btn btn-primary btn-lg btn-block">Sign in</button>
        </form>
      </div>
    </div>
  </div>
</section>
