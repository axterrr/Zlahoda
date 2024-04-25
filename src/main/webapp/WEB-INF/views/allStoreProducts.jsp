<%@include file="header.jsp"%>

<div class="container-fluid">

    <div class="row-fluid" align="center">
        <h2>
            Store Products
        </h2>
    </div>

    <div class="row-fluid" align="left">
        <div class="btn-group" role="group" aria-label="buttons">
            <button type="button" class="btn btn-default"
                    onclick="location.href='${pageContext.request.contextPath}/controller/storeProducts/addStoreProduct';">
                Add Store Product
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#searchByUPC">
                Search by UPC
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#filterPromotional">
                Filter Promotional
            </button>
            <button type="button" class="btn btn-default"
                    onclick="location.href='${pageContext.request.contextPath}/controller/storeProducts/report';">
                Report
            </button>
        </div>
    </div>

    <!-- modal searchByUPC -->
    <div class="modal fade" id="searchByUPC" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Search By UPC
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                        action="${pageContext.request.contextPath}/controller/storeProducts/upc"
                        method="POST" role="form">
                        <div class="form-group">
                            <label for="upc">Search By UPC</label>
                            <input type="text" class="form-control" id="upc" name="upc" />
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-default" id="submitButton">
                                Search
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- modal filterPromotional -->
    <div class="modal fade" id="filterPromotional" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Filter Promotional
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/storeProducts"
                            method="POST" role="form">
                        <div class="form-group">
                            <label for="promotional">Find</label>
                            <select class="form-control" id="promotional" name="isPromotional">
                                <option value="">All Products</option>
                                <option value="true">Promotional Products</option>
                                <option value="false">Not Promotional Products</option>
                            </select>
                            <label for="sortby">Sorted By</label>
                            <select class="form-control" id="sortby" name="orderBy">
                                <option value="name">Name</option>
                                <option value="amount">Amount</option>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-default" id="submitButton">
                                Search
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

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
                <th>UPC</th>
                <th>Name</th>
                <th>Selling price</th>
                <th>Amount</th>
                <th>Promotional</th>
                <th>Promotional UPC</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${storeProducts}" var="storeProduct">
                <tr>
                    <td>${storeProduct.getUpc()}</td>
                    <td>${storeProduct.getProduct().getName()}</td>
                    <td>${storeProduct.getPrice()}</td>
                    <td>${storeProduct.getAmount()}</td>
                    <td align="center">
                        <c:choose>
                            <c:when test="${storeProduct.isPromotional()}">
                                &#10004;
                            </c:when>
                        </c:choose>
                    </td>
                    <td>${storeProduct.getProm().getUpc()}</td>
                    <td><a href="${pageContext.request.contextPath}/controller/storeProducts/updateStoreProduct?id_storeProduct=${storeProduct.getUpc()}">Update</a></td>
                    <td><a href="${pageContext.request.contextPath}/controller/storeProducts/deleteStoreProduct?id_storeProduct=${storeProduct.getUpc()}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<%@include file="footer.jsp"%>
