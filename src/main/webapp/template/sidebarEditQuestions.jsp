<style><%@include file="../style/sidebar.css"%></style>
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="sidebar">
     <hr style="margin-top: -6px;">
         <div class="form-group column center mt-5">
                 <div class="col-sm-12 mt-2">
                    <button id="form-btn" onclick="location.href = '/edit/${requestScope.currentTest.id}';" type="button" class="btn btn-success col-lg-12 btn-lg"><fmt:message key="SidebarAddQuestionEdit" /></button>
                 </div>
                 <div class="col-sm-12 mt-2">
                    <button id="form-btn" onclick="location.href = '/add/${requestScope.currentTest.id}';" type="button" class="btn btn-success col-lg-12 btn-lg"><fmt:message key="SidebarEditQuestionAdd" /></button>
                 </div>
                 <hr>
                 <fmt:parseNumber var="next" type="number" value="${requestScope.currentPage}" scope="page"/>
                 <c:set var="next" value="${next + 1}" scope="page"/>
                 <fmt:parseNumber var="prev" type="number" value="${requestScope.currentPage}" scope="page"/>
                 <c:set var="prev" value="${prev - 1}" scope="page"/>
                   <c:choose>
                         <c:when test="${requestScope.currentPage >= requestScope.pages}">
                         <div class="col-sm-12 mt-2">
                            <button type="button" class="btn btn-primary col-lg-12 btn-lg" disabled><fmt:message key="SidebarEditQuestionNext" /></button>
                         </div>
                         </c:when>
                           <c:otherwise>
                           <div class="col-sm-12 mt-2">
                                <button type="button" onclick="location.href = '/change/${requestScope.currentTest.id}?question=${next}';" class="btn btn-primary col-lg-12 btn-lg"><fmt:message key="SidebarEditQuestionNext" /></button>
                           </div>
                           </c:otherwise>
                     </c:choose>
                     <c:choose>
                          <c:when test="${requestScope.currentPage <= '1'}">
                          <div class="col-sm-12 mt-2">
                              <button type="submit" class="btn btn-primary col-lg-10 btn-lg col-lg-12" disabled><fmt:message key="SidebarEditQuestionPrev" /></button>
                          </div>
                          </c:when>
                            <c:otherwise>
                            <div class="col-sm-12 mt-2">
                                <button type="button" onclick="location.href = '/change/${requestScope.currentTest.id}?question=${prev}';" class="btn btn-primary col-lg-12 btn-lg"><fmt:message key="SidebarEditQuestionPrev" /></button>
                            </div>
                            </c:otherwise>
                     </c:choose>
        </div>
</div>
