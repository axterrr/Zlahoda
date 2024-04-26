<%@include file="/WEB-INF/views/header.jsp"%>

<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h3>
            <c:choose>
                <c:when test="${not empty requestScope.storeProductDto.getUpc()}">
                    Update Store Product
                </c:when>
                <c:otherwise>
                    Add Store Product
                </c:otherwise>
            </c:choose>
        </h3>
    </div>
    <div class="row-fluid">
        <div class="col-sm-6 col-sm-offset-3 ">
            <c:choose>
            <c:when test="${not empty requestScope.storeProductDto.getUpc()}">
            <form action="./updateStoreProduct" method="POST" role="form">
                </c:when>
                <c:otherwise>
                <form action="./addStoreProduct" method="POST" role="form">
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
                        <label for="name">Product</label>
                        <c:choose>
                            <c:when test="${empty requestScope.storeProductDto.getUpc()}">
                                <select class="form-control" id="name"
                                        name="product">
                                    <c:forEach items="${products}" var="product">
                                        <option value="${product.getId()}">${product.getName()}</option>
                                    </c:forEach>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <h4 id="name">
                                    <c:out value="${requestScope.storeProductDto.getProduct().getName()}"/>
                                </h4>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="form-group">
                        <label for="price">Price</label> <input type="number" step="0.0001" class="form-control"
                                                              id="price" name="price"
                                                              placeholder="Price"
                                                              value="<c:out value="${requestScope.storeProductDto.getPrice()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="amount">Amount</label> <input type="number" class="form-control"
                                                                id="amount" name="number"
                                                                placeholder="Amount"
                                                                value="<c:out value="${requestScope.storeProductDto.getAmount()}" />" />
                    </div>
                    <c:if test="${empty requestScope.storeProductDto.getUpc()}">
                        <div class="form-group">
                            <label for="promotional">Is Promotional</label>
                            <select class="form-control" id="promotional"
                                    name="isPromotional">
                                <option value="false">Not Promotional</option>
                                <option value="true">Promotional</option>
                            </select>
                        </div>
                    </c:if>
                    <c:if test="${not empty requestScope.storeProductDto.getUpc()}">
                        <input type="hidden" id="id_storeProduct" name="id_storeProduct"
                               value="<c:out value="${requestScope.storeProductDto.getUpc()}"/>">
                    </c:if>
                    <c:choose>
                        <c:when test="${not empty requestScope.storeProductDto.getUpc()}">
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
