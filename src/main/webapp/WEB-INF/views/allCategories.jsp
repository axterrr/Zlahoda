<%@include file="header.jsp"%>

<div class="container-fluid">

    <div class="row-fluid" align="center">
        <h2>
            Categories
        </h2>
    </div>

    <div class="row-fluid" align="left">
<%--
    <c:if test="${user.getRole().getValue() eq 'manager' }">
--%>
        <div class="btn-group" role="group" aria-label="buttons">
                <button type="button" class="btn btn-default"
                        onclick="location.href='${pageContext.request.contextPath}/controller/categories/addCategory';">
                    Add Category
                </button>
                <button type="button" class="btn btn-default"
                        onclick="location.href='${pageContext.request.contextPath}/controller/categories/report';">
                    Report
                </button>
            </div>
        </div>
<%--
    </c:if>
--%>

    <div class="row-fluid" align="center">
        <c:if test="${not empty param.success}">
            <div class="alert alert-success">
                ${param.success}
            </div>
        </c:if>
    </div>
    <div class="row-fluid" align="center">
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger">
                ${param.error}
            </div>
        </c:if>
    </div>

    <div class="row-fluid top-margin" align="center">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <c:if test="${user.getRole().getValue() eq 'manager' }">
                    <th></th>
                    <th></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.getNumber()}</td>
                    <td>${category.getName()}</td>
<%--
                    <c:if test="${user.getRole().getValue() eq 'manager' }">
--%>
                        <td><a href="${pageContext.request.contextPath}/controller/categories/updateCategory?id_category=${category.getNumber()}">Update</a></td>
                        <td><a href="${pageContext.request.contextPath}/controller/categories/deleteCategory?id_category=${category.getNumber()}">Delete</a></td>
<%--
                    </c:if>
--%>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<%@include file="footer.jsp"%>
