<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<style><%@include file="../style/navbar.css"%></style>
<nav id="navigation" class="navbar navbar-expand-lg navbar-light ">
   <a class="navbar-brand ml-5" href="/"><img  src="https://i.ibb.co/qYQpgLz/logo.png" alt="" width="30" height="32" >Quick test</a>
   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
     <span class="navbar-toggler-icon"></span>
   </button>
   <div class="collapse navbar-collapse ml-5" id="navbarNav">
     <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Home</a>
        </li>
      </ul>
     </ul>
     <ul class="nav navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" href="/signup"><img src="https://cdn-icons-png.flaticon.com/512/4289/4289598.png" alt="" width="30" height="32" > Sign Up</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/login"><img src="https://i.ibb.co/6XpXb1m/signin.png" alt="" width="30" height="32" >Log In</a>
      </li>
    </ul>
   </div>
</nav>