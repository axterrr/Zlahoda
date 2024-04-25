<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en_US">
<head>
    <title>Zlahoda</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/css/styles.css" />" />
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/bootstrap-3.3.7/css/bootstrap.min.css" />" />
    <link rel="stylesheet"
          href="<c:url value="/resources/font-awesome-4.7.0/css/font-awesome.min.css" />">
</head>
<body>
    <div class="container main-container">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand"
                       href="${pageContext.request.contextPath}/">Main</a>
                </div>
                <ul class="nav navbar-nav">
<%--
                    <c:if test="${not empty user}">
--%>
                        <li><a
                                href="${pageContext.request.contextPath}/controller/categories">Categories</a></li>
                        <li><a
                                href="${pageContext.request.contextPath}/controller/products">Products</a></li>
                        <li><a
                                href="${pageContext.request.contextPath}/controller/storeProducts">Store Products</a></li>
                        <li><a
                                href="${pageContext.request.contextPath}/controller/checks">Checks</a></li>
                        <li><a
                                href="${pageContext.request.contextPath}/controller/employees">Employees</a></li>
                        <li><a
                                href="${pageContext.request.contextPath}/controller/customerCards">Customers</a></li>
<%--
                    </c:if>
--%>
                </ul>
                    <c:choose>
                        <c:when test="${empty employee}">
                            <li><a
                                    href="${pageContext.request.contextPath}/controller/login"><span
                                    class="glyphicon glyphicon-log-out"></span>Login</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a
                                    href="${pageContext.request.contextPath}/controller/logout"><span
                                    class="glyphicon glyphicon-log-in"></span>Logout</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>
