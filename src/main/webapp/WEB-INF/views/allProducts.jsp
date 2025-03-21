<%@include file="header.jsp"%>

<div class="container-fluid">

    <div class="row-fluid" align="center">
        <h2>
            Products
        </h2>
    </div>

    <div class="row-fluid" align="left">
        <div class="btn-group" role="group" aria-label="buttons">
<c:if test="${employee.getRole().getValue() eq 'manager' }">
            <button type="button" class="btn btn-default"
                    onclick="location.href='${pageContext.request.contextPath}/controller/products/addProduct';">
                Add Product
            </button>
</c:if>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#searchByCategory">
                Search by Category
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#searchByName">
                Search by name
            </button>
<c:if test="${employee.getRole().getValue() eq 'manager' }">
            <button type="button" class="btn btn-default"
                    onclick="printTable()">
                Report
            </button>
</c:if>
        </div>
    </div>

    <script>
        function printTable() {
            var table = document.getElementById("prodTable");
            if (table) {
                var iframe = document.createElement('iframe');
                iframe.style.display = 'none';
                document.body.appendChild(iframe);
                var iframeDoc = iframe.contentWindow.document;
                iframeDoc.write('<style>body {padding-top: 50px; padding-bottom: 50px; justify-content: stretch;}</style>');
                iframeDoc.write('<h2 align="center">Products</h2>');
                iframeDoc.write(table.outerHTML);
                iframeDoc.close();
                iframe.onload = function() {
                    iframe.contentWindow.print();
                };
            } else {
                alert("Table not found!");
            }
        }
    </script>

    <!-- modal searchByCategory -->
    <div class="modal fade" id="searchByCategory" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Search by Category
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/products/category"
                            method="POST" role="form">

                        <div class="form-group">
                            <label for="category">Category</label>
                            <select class="form-control" id="category" name="category">
                                <option value="">All Categories</option>
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.getNumber()}">${category.getName()}</option>
                                </c:forEach>
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

    <!-- modal searchByName -->
    <div class="modal fade" id="searchByName" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Search By Name
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/products/name"
                            method="POST" role="form">

                        <div class="form-group">
                            <label for="name">Search By Name</label>
                            <input type="text" class="form-control" id="name" name="name" />
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
        <table class="table table-bordered" id="prodTable">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Characteristics</th>
                <th>Category</th>
<c:if test="${employee.getRole().getValue() eq 'manager' }">
                <th class="tdbutton"></th>
                <th class="tdbutton"></th>
</c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.getId()}</td>
                    <td>${product.getName()}</td>
                    <td>${product.getCharacteristics()}</td>
                    <td>${product.getCategory().getName()}</td>
                    <c:if test="${employee.getRole().getValue() eq 'manager' }">
                    <td class="tdbutton"><a href="${pageContext.request.contextPath}/controller/products/updateProduct?id_product=${product.getId()}">Update</a></td>
                    <td class="tdbutton"><a href="${pageContext.request.contextPath}/controller/products/deleteProduct?id_product=${product.getId()}">Delete</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            <style>
                @media print {
                    .tdbutton {
                        display: none !important;
                    }
                    table {
                        border-collapse: collapse;
                        border: 1px solid black;
                        width: 100%;
                    }
                    td, th {
                        border: 1px solid black;
                        padding: 8px;
                    }
                }
            </style>
            </tbody>
        </table>
    </div>

</div>

<%@include file="footer.jsp"%>
