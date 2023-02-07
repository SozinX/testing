<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../template/base.jsp"%>
<style><%@include file="../style/index.css"%></style>
<%@include file="../template/sidebarForOwner.jsp"%>
 <head>
     <title>Quick Test: My tests</title>
 </head>
 <div class="page-content">
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
                            <a class="page-link" href="#"><fmt:message key="Prev" /></a>
                             </li>
                            <br />
                    </c:when>
                      <c:otherwise>
                              <li class="page-item">
                                <a class="page-link" href="/mytests?page=${prev}${requestScope.address}"><fmt:message key="Prev" /></a>
                               </li>
                              <br />
                      </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${requestScope.currentPage >= requestScope.pages}">
                                <li class="page-item disabled">
                                      <a class="page-link" href="#"><fmt:message key="Next" /></a>
                                    </li>
                                <br />
                        </c:when>
                          <c:otherwise>
                                  <li class="page-item">
                                    <a class="page-link" href="/mytests?page=${next}${requestScope.address}"><fmt:message key="Next" /></a>
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
                <form id="for-hover" class="col-xl-3">
                    <a href="/edit/${test.id}" class="card card-video border-0 bg-transparent mb-4">
                        <img src="https://img.freepik.com/premium-vector/job-exam-test-vector-illustration_138676-243.jpg?w=2000" class="img-fluid" alt="">
                        <div class="card-video-details d-flex mt-2">
                            <div>
                                <input name="current" value="${test.id}" disabled hidden>
                                <h4>${test.name}</h4>
                                <div>${test.subject}</div>
                                <div>${test.owner.name} • ${test.level.level} | ${test.finished}
                                <c:choose>
                                    <c:when test="${test.isClose == '0'}">
                                      <svg xmlns="http://www.w3.org/2000/svg" width="20" height="16" fill="currentColor" class="bi bi-unlock-fill" viewBox="0 0 16 16">
                                      <path d="M11 1a2 2 0 0 0-2 2v4a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V9a2 2 0 0 1 2-2h5V3a3 3 0 0 1 6 0v4a.5.5 0 0 1-1 0V3a2 2 0 0 0-2-2z"/></svg>
                                    </c:when>
                                      <c:otherwise>
                                                 <svg xmlns="http://www.w3.org/2000/svg" width="20" height="16" fill="currentColor" class="bi bi-lock-fill" viewBox="0 0 16 16">
                                                 <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"/></svg></label>

                                      </c:otherwise>
                                 </c:choose>
                                </div>
                            </div>
                        </div>
                    </a>
                </form>
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
                                         <a class="page-link" href="#"><fmt:message key="Prev" /></a>
                                          </li>
                                         <br />
                                 </c:when>
                                   <c:otherwise>
                                           <li class="page-item">
                                             <a class="page-link" href="/mytests?page=${prev}${requestScope.address}"><fmt:message key="Prev" /></a>
                                            </li>
                                           <br />
                                   </c:otherwise>
                                 </c:choose>
                                 <c:choose>
                                     <c:when test="${requestScope.currentPage >= requestScope.pages}">
                                             <li class="page-item disabled">
                                                   <a class="page-link" href="#"><fmt:message key="Next" /></a>
                                                 </li>
                                             <br />
                                     </c:when>
                                       <c:otherwise>
                                               <li class="page-item">
                                                 <a class="page-link" href="/mytests?page=${next}${requestScope.address}"><fmt:message key="Next" /></a>
                                                </li>
                                               <br />
                                       </c:otherwise>
                                 </c:choose>
                           </ul>
                         </nav>
            </div>
     </div>
</div>