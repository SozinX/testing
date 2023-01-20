<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="icon" type="image/x-icon" href="https://i.ibb.co/qYQpgLz/logo.png">
</head>
<header>
  <c:choose>
      <c:when test="${sessionScope.role == null}">
          <%@include file="./navENNN.jsp"%>
          <br />
      </c:when>
      <c:when test="${sessionScope.role == 'Confirmed teacher'}">
                    <%@include file="./navENT.jsp"%>
                    <br />
      </c:when>
      <c:otherwise>
           <%@include file="./navENS.jsp"%>
          <br />
      </c:otherwise>
  </c:choose>
</header>
