<%@include file="header.jsp"%>

<div class="container-fluid">

    <div class="row-fluid" align="center">
        <h2>
            Categories
        </h2>
    </div>

    <div class="row-fluid" align="left">
        <div class="btn-group" role="group" aria-label="buttons">
                <button type="button" class="btn btn-default"
                        onclick="location.href='${pageContext.request.contextPath}/controller/categories/addCategory';">
                    Add Category
                </button>
                <button type="button" class="btn btn-default"
                        onclick="printTable()">
                    Report
                </button>
            </div>
        </div>
    <script>
        function printTable() {
            var table = document.getElementById("catTable");
            if (table) {
                var iframe = document.createElement('iframe');
                iframe.style.display = 'none';
                document.body.appendChild(iframe);
                var iframeDoc = iframe.contentWindow.document;
                iframeDoc.write('<style>body {padding-top: 50px; padding-bottom: 50px; justify-content: stretch;}</style>');
                iframeDoc.write('<h2 align="center">Categories</h2>');
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
        <table class="table table-bordered" id="catTable">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                    <th class="tdbutton"></th>
                    <th class="tdbutton"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.getNumber()}</td>
                    <td>${category.getName()}</td>
                        <td class="tdbutton"><a href="${pageContext.request.contextPath}/controller/categories/updateCategory?id_category=${category.getNumber()}">Update</a></td>
                        <td class="tdbutton"><a href="${pageContext.request.contextPath}/controller/categories/deleteCategory?id_category=${category.getNumber()}">Delete</a></td>
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
