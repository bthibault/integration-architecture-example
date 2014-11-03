<!DOCTYPE HTML><%@ page language="java" pageEncoding="UTF-8"%><%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><d:title default="Integration Architecture Example" /></title>
<meta name="description" content="${meta}" />
<d:head />
</head>

<body>
  <%@ include file="/WEB-INF/commons/header.jsp"%>
  <c:if test="${not empty _error}">
    <div id="message" class="alert alert-danger">${_error}</div>
  </c:if>
  <c:if test="${not empty _msg}">
    <div id="message" class="alert alert-info">${_msg}</div>
  </c:if>
  <c:if test="${not _blank}">
    <d:body />
  </c:if>
  <%@ include file="/WEB-INF/commons/footer.jsp"%>
</body>
</html>