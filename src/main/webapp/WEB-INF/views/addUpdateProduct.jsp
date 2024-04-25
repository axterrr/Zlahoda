<%@include file="/WEB-INF/views/header.jsp"%>

<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h3>
            <c:choose>
                <c:when test="${not empty requestScope.productDto.getId()}">
                    Update Product
                </c:when>
                <c:otherwise>
                    Add Product
                </c:otherwise>
            </c:choose>
        </h3>
    </div>

    <div class="row-fluid">
        <div class="col-sm-6 col-sm-offset-3 ">
            <c:choose>
                <c:when test="${not empty requestScope.productDto.getId()}">
                    <form action="./updateProduct" method="POST" role="form">
                </c:when>
                <c:otherwise>
                    <form action="./addProduct" method="POST" role="form">
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
                            <input type="text" class="form-control"
                                    id="name" name="name"
                                    placeholder="Name"
                                    value="<c:out value="${requestScope.productDto.getName()}" />" />
                        </div>
                        <div class="form-group">
                            <label for="characteristics">Characteristics</label>
                            <input type="text"
                                  class="form-control" id="characteristics" name="characteristics"
                                  placeholder="Characteristics"
                                  value="<c:out value="${requestScope.productDto.getCharacteristics()}" />" />
                        </div>
                        <div class="form-group">
                            <label for="category">Category</label>
                            <select class="form-control" id="category" name="category">

                                <c:if test="${not empty requestScope.productDto}">
                                    <option value="${requestScope.productDto.getCategory().getNumber()}">
                                            ${requestScope.productDto.getCategory().getName()}</option>
                                </c:if>
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.getNumber()}">${category.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <c:if test="${not empty requestScope.productDto.getId()}">
                            <input type="hidden" id="id_product" name="id_product"
                                   value="<c:out value="${requestScope.productDto.getId()}"/>">
                        </c:if>
                        <c:choose>
                            <c:when test="${not empty requestScope.productDto.getId()}">
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
