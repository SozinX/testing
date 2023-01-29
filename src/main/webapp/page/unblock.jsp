<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<style><%@include file="../style/settings.css"%></style>
<head>
    <title>Quick Test: Blocking</title>
</head>
<div class="wrapper bg-white mt-sm-5 ">
    <h4 class="pb-4 border-bottom d-flex align-items-center justify-content-center">Blocking system</h4>
     <section class="vh-10">
              <div class="container py-1 h-10">
                <div class="row d-flex align-items-center justify-content-center h-10">
                  <div class="col-md-10 col-lg-10 col-xl-10 offset-xl-1">
                     <form action="/unblock" method="post">
                              <div class="form-outline mb-2">
                                    <input name="unblockEmail" type="text" id="form1Example13" class="form-control form-control-lg mt-3" placeholder="Unblock user"/>
                                    <label class="form-label" for="form1Example13">Email address</label>
                              </div>
                              <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
                                <div class="py-3 pb-4 border-bottom d-flex align-items-center justify-content-center">
                                            <button class="btn btn-success mr-3">Unblock user</button>
                                            <button type="button" class="btn border btn-secondary" onClick="window.location.reload(true)">Cancel</button>
                                </div>
                            </form>
                  </div>
                </div>
              </div>
            </section>
 <form action="/block">
        <div class="d-sm-flex align-items-center pt-3" id="deactivate">
            <div>
                <b>Do you want to block user?</b>
                <p>Go to block user form</p>
            </div>
            <div class="ml-auto">
                <button class="btn btn-danger">Block user</button>
            </div>
        </div>
        </form>
    </div>
</div>