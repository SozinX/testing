<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<style><%@include file="../style/settings.css"%></style>
<head>
    <title>Quick Test: Settings</title>
</head>
<div class="wrapper bg-white mt-sm-5 ">
    <h4 class="pb-4 border-bottom d-flex align-items-center justify-content-center">Account settings</h4>
     <section class="vh-10">
              <div class="container py-1 h-10">
                <div class="row d-flex align-items-center justify-content-center h-10">
                  <div class="col-md-10 col-lg-10 col-xl-10 offset-xl-1">
                    <form action="/settings" method="post">
                    <div class="form-outline mb-2">
                       <input name="set-name" type="text" id="form1Example12" class="form-control form-control-lg" value="${sessionScope.name}"/>
                       <label class="form-label" for="form1Example12">Your name</label>
                    </div>

                      <div class="form-outline mb-2">
                        <input name="set-email" type="text" id="form1Example13" class="form-control form-control-lg" value="${sessionScope.email}"/>
                        <label class="form-label" for="form1Example13">Email address</label>
                      </div>

                        <div class="input-group mb-3">
                          <div class="input-group-prepend">
                            <label id="status-label" class="input-group-text" for="inputGroupSelect01">Status</label>
                          </div>
                          <c:choose>
                          <c:when test="${sessionScope.role == 'Confirmed teacher'}">
                              <select name="set-role" class="custom-select" id="inputGroupSelect01">
                                    <option value="3">Confirmed teacher</option>
                              </select>
                              <br />
                          </c:when>
                            <c:otherwise>
                                 <select name="set-role" class="custom-select" id="inputGroupSelect01">
                                     <option value="1">Student</option>
                                     <option value="2">Teacher</option>
                                   </select>
                                <br />
                            </c:otherwise>
                          </c:choose>
                        </div>

                      <div class="form-outline mb-2 ">
                        <input name="new-password" type="password" id="form1Example23" class="form-control form-control-lg" />
                        <label class="form-label" for="form1Example23">New password</label>
                        <input name="confirm-password" type="password" id="form1Example23" class="form-control form-control-lg" />
                        <label class="form-label" for="form1Example23">Confirm password</label>
                      </div>
                        <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
                        <div class="py-3 pb-4 border-bottom d-flex align-items-center justify-content-center">
                                    <button class="btn btn-success mr-3">Save Changes</button>
                                </div>
                    </form>
                    <div class="py-3 pb-4 border-bottom d-flex align-items-center justify-content-center">
                            <button class="btn border btn-secondary" onClick="window.location.reload(true)">Cancel</button>
                    </div>
                  </div>
                </div>
              </div>
            </section>
        <form action="/deactivate">
        <div class="d-sm-flex align-items-center pt-3" id="deactivate">
            <div>
                <b>Deactivate your account</b>
                <p>Details about your account and password</p>
            </div>
            <div class="ml-auto">
                <button class="btn danger">Deactivate</button>
            </div>
        </div>
        </form>
    </div>
</div>