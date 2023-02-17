<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/signup.css"%></style>
<style><%@include file="../style/settings.css"%></style>
<head>
    <title>Quick Test: Blocking</title>
</head>
<div class="wrapper bg-white mt-sm-5 ">
    <h4 class="pb-4 border-bottom d-flex align-items-center justify-content-center"><fmt:message key="BlockSystem" /></h4>
     <section class="vh-10">
              <div class="container py-1 h-10">
                <div class="row d-flex align-items-center justify-content-center h-10">
                  <div class="col-md-10 col-lg-10 col-xl-10 offset-xl-1">
                    <form action="/block" method="post">

                      <div class="form-outline mb-2">
                        <input name="blockEmail" type="text" id="form1Example13" class="form-control form-control-lg" placeholder="Block user"/>
                        <label class="form-label" for="form1Example13"><fmt:message key="BlockEmail" /></label>
                      </div>
                      <span id="error" class="d-flex justify-content-around align-items-center mb-1 text-danger">${requestScope.message}</span>
                        <div class="py-3 pb-4 border-bottom d-flex align-items-center justify-content-center">
                                    <button class="btn btn-danger mr-3"><fmt:message key="BlockBlock" /></button>
                                    <button type="button" class="btn border btn-secondary" onClick="window.location.reload(true)"><fmt:message key="BlockCancel" /></button>
                                </div>
                    </form>
                  </div>
                </div>
              </div>
            </section>
 <form action="/unblock">
        <div class="d-sm-flex align-items-center pt-3" id="deactivate">
            <div>
                <b><fmt:message key="BlockUnblockInfo" /></b>
                <p><fmt:message key="BlockUnblockGo" /></p>
            </div>
            <div class="ml-auto">
                <button class="btn btn-success"><fmt:message key="BlockUnblock" /></button>
            </div>
        </div>
        </form>
    </div>
</div>