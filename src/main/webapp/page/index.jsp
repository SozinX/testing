<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/index.css"%></style>
<%@include file="../template/sidebar.jsp"%>
 <head>
     <title>Quick Test: Home</title>
 </head>
 <form class="page-content" action='get'>
 <div class="container-fluid py-2 px-5">
        <div class="row" style="margin-top: 60px">
        <nav class="ml-5" aria-label="...">
          <ul class="pagination">
            <fmt:parseNumber var="next" type="number" value="${requestScope.currentPage}" scope="page"/>
            <c:set var="next" value="${next + 1}" scope="page"/>
            <fmt:parseNumber var="prev" type="number" value="${requestScope.currentPage}" scope="page"/>
            <c:set var="prev" value="${prev - 1}" scope="page"/>
             <c:choose>
                <c:when test="${requestScope.currentPage <= '1'}">
                        <li class="page-item disabled">
                        <a class="page-link" href="#">Previous</a>
                         </li>
                        <br />
                </c:when>
                  <c:otherwise>
                          <li class="page-item">
                            <a class="page-link" href="/tests?page=${prev}${requestScope.address}">Previous</a>
                           </li>
                          <br />
                  </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${requestScope.currentPage >= requestScope.pages}">
                            <li class="page-item disabled">
                                  <a class="page-link" href="#">Next</a>
                                </li>
                            <br />
                    </c:when>
                      <c:otherwise>
                              <li class="page-item">
                                <a class="page-link" href="/tests?page=${next}${requestScope.address}">Next</a>
                               </li>
                              <br />
                      </c:otherwise>
                </c:choose>
          </ul>
        </nav>
        <c:choose>
        <c:when test="${requestScope.tests == '[]'}">
            <div class="col-lg-15">
                    <img src="https://cdn.dribbble.com/users/2382015/screenshots/6065978/media/8b4662f8023e4e2295f865106b5d3aa7.gif"
                      class="img-fluid" alt="Sample image">
             </div>
             </c:when>
             <c:otherwise>
        <c:forEach items="${requestScope.tests}" var="test">
            <div id="for-hover" class="col-xl-3">
                <a href="#" class="card card-video border-0 bg-transparent mb-4">
                    <img src="https://img.freepik.com/premium-vector/job-exam-test-vector-illustration_138676-243.jpg?w=2000" class="img-fluid" alt="">
                    <div class="card-video-details d-flex mt-2">
                        <div>
                            <h4>${test.name}</h4>
                            <div>${test.subject}</div>
                            <div>${test.owner.name} • ${test.level.level}</div>
                        </div>
                    </div>
                </a>
            </div>
             </c:forEach>
             </c:otherwise>
             </c:choose>
             <nav class="ml-5" aria-label="...">
                       <ul class="pagination">
                         <fmt:parseNumber var="next" type="number" value="${requestScope.currentPage}" scope="page"/>
                         <c:set var="next" value="${next + 1}" scope="page"/>
                         <fmt:parseNumber var="prev" type="number" value="${requestScope.currentPage}" scope="page"/>
                         <c:set var="prev" value="${prev - 1}" scope="page"/>
                          <c:choose>
                             <c:when test="${requestScope.currentPage <= '1'}">
                                     <li class="page-item disabled">
                                     <a class="page-link" href="#">Previous</a>
                                      </li>
                                     <br />
                             </c:when>
                               <c:otherwise>
                                       <li class="page-item">
                                         <a class="page-link" href="/tests?page=${prev}${requestScope.address}">Previous</a>
                                        </li>
                                       <br />
                               </c:otherwise>
                             </c:choose>
                             <c:choose>
                                 <c:when test="${requestScope.currentPage >= requestScope.pages}">
                                         <li class="page-item disabled">
                                               <a class="page-link" href="#">Next</a>
                                             </li>
                                         <br />
                                 </c:when>
                                   <c:otherwise>
                                           <li class="page-item">
                                             <a class="page-link" href="/tests?page=${next}${requestScope.address}">Next</a>
                                            </li>
                                           <br />
                                   </c:otherwise>
                             </c:choose>
                       </ul>
                     </nav>
        </div>
 </div>
</form>