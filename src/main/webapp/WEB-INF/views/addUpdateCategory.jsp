<%@include file="/WEB-INF/views/header.jsp"%>

<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h3>
            <c:choose>
                <c:when test="${not empty requestScope.categoryDto.getNumber()}">
                    Update Category
                </c:when>
                <c:otherwise>
                    Add Category
                </c:otherwise>
            </c:choose>
        </h3>
    </div>

    <div class="row-fluid">
        <div class="col-sm-6 col-sm-offset-3 ">
            <c:choose>
                <c:when test="${not empty requestScope.categoryDto.getNumber()}">
                    <form action="./updateCategory" method="POST" role="form">
                </c:when>
                <c:otherwise>
                    <form action="./addCategory" method="POST" role="form">
                </c:otherwise>
            </c:choose>

                    <c:if test="${not empty requestScope.errors}">
                        <div class="alert alert-danger">
                            <c:forEach items="${requestScope.errors}" var="error">
                                <p>
                                    ${error}
                                </p>
                            </c:forEach>
                        </div>
                    </c:if>

                    <div class="form-group">
                        <label for="name">Name</label>
                        <input
                            type="text" class="form-control" id="name" name="name"
                            placeholder="Name"
                            value="<c:out value="${requestScope.categoryDto.getName()}" />" />
                    </div>

                    <c:if test="${not empty requestScope.categoryDto.getNumber()}">
                        <input type="hidden" id="id_category" name="id_category"
                               value="<c:out value="${requestScope.categoryDto.getNumber()}"/>">
                    </c:if>

                    <c:choose>
                        <c:when test="${not empty requestScope.categoryDto.getNumber()}">
                            <button type="submit" class="btn btn-default" id="submitButton">
                                Update
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn btn-default" id="submitButton">
                                Add
                            </button>
                        </c:otherwise>
                    </c:choose>
                </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/footer.jsp"%>
