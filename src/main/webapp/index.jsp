<%@include file="WEB-INF/views/header.jsp"%>
<div class="container-fluid" align="center">

    <div class="row-fluid" align="center">
        <c:if test="${not empty param.success}">
            <div class="alert alert-success">
                ${param.success}
            </div>
        </c:if>
    </div>
    <div class="row-fluid">
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger">
                ${param.error}
            </div>
        </c:if>
    </div>

    <div class="row-fluid">
        <h2>
            Welcome to Zlahoda !
        </h2>
    </div>

</div>
<%@include file="WEB-INF/views/footer.jsp"%>
