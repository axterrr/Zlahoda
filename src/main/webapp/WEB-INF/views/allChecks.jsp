<%@include file="header.jsp"%>

<div class="container-fluid">

    <div class="row-fluid" align="center">
        <h2>
            Checks
        </h2>
    </div>

    <div class="row-fluid" align="left">
        <div class="btn-group" role="group" aria-label="buttons">
            <button type="button" class="btn btn-default"
                    onclick="location.href='${pageContext.request.contextPath}/controller/checks/addCheck';">
                Add Check
            </button>
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#searchByNumber">
                Search by Number
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#filter">
                Search per Period
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#totalSum">
                Total Sum
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#totalAmount">
                Total Amount
            </button>
            <button type="button" class="btn btn-default"
                    onclick="location.href='${pageContext.request.contextPath}/controller/checks/report';">
                Report
            </button>
        </div>
    </div>

    <!-- modal searchByNumber -->
    <div class="modal fade" id="searchByNumber" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Search By Number
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/checks/number"
                            method="POST" role="form">

                        <div class="form-group">
                            <label for="number">Search By Number</label>
                            <input type="text" class="form-control" id="number" name="id_check" />
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

    <!-- modal filter -->
    <div class="modal fade" id="filter" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Search per Period
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/checks/search"
                            method="POST" role="form">
                        <div class="form-group">
                            <label for="employee">Employee</label>
                            <select class="form-control" id="employee" name="employee">
                                <option value="">All Employees</option>
                                <c:forEach items="${employees}" var="employee">
                                    <option value="${employee.getId()}">${employee.getSurname()}</option>
                                </c:forEach>
                            </select>
                            <label for="date-from">Date from</label>
                            <input type="date" class="form-control" id="date-from" name="dateFrom"/>
                            <label for="date-to">To</label>
                            <input type="date" class="form-control" id="date-to" name="dateTo" />
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

    <!-- modal totalSum -->
    <div class="modal fade" id="totalSum" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Total Sum
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/checks/sum"
                            method="POST" role="form">
                        <div class="form-group">
                            <label for="check-employee">Employee</label>
                            <select class="form-control" id="check-employee" name="employee">
                                <option value="">All Employees</option>
                                <c:forEach items="${employees}" var="empl">
                                    <option value="${empl.getId()}">${empl.getSurname()}</option>
                                </c:forEach>
                            </select>
                            <label for="dateFrom">From</label>
                            <input type="date" class="form-control" id="dateFrom" name="dateFrom"/>
                            <label for="dateTo">To</label>
                            <input type="date" class="form-control" id="dateTo" name="dateTo" />
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-default" id="submitButton">
                                Count
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- modal totalAmount -->
    <div class="modal fade" id="totalAmount" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Total Amount
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/checks/amount"
                            method="POST" role="form">
                        <div class="form-group">
                            <label for="employee">Product</label>
                            <select class="form-control" id="product" name="product">
                                <c:forEach items="${products}" var="product">
                                    <option value="${product.getId()}">${product.getName()}</option>
                                </c:forEach>
                            </select>
                            <label for="dateFrom">Date from</label>
                            <input type="date" class="form-control" id="dateFrom" name="dateFrom"/>
                            <label for="dateTo">To</label>
                            <input type="date" class="form-control" id="dateTo" name="dateTo" />
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-default" id="submitButton">
                                Count
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
                <th>ID</th>
                <th>Employee</th>
                <th>Customer Card</th>
                <th>Print Date</th>
                <th>Total Sum</th>
                <th>VAT</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${checks}" var="check">
                <tr>
                    <td>${check.getNumber()}</td>
                    <td>${check.getEmployee().getId()}, ${check.getEmployee().getSurname()}</td>
                    <td>${check.getCustomerCard().getNumber()}, ${check.getCustomerCard().getSurname()}</td>
                    <td>${check.getPrintDate()}</td>
                    <td>${check.getTotalSum()}</td>
                    <td>${check.getVat()}</td>
                    <td><button type="button" class="btn btn-default" data-toggle="modal" data-target="#fullInfoModal${check.getNumber()}">
                        Full Info
                    </button>
                        <!-- modal filter -->
                        <div class="modal fade" id="fullInfoModal${check.getNumber()}" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="Close">
                                            <span>&times;</span>
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            Check ${check.getNumber()}
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="full-info">
                                            <h4><b>Cashier</b> :  ${check.getEmployee().getId()}, ${check.getEmployee().getSurname()}</h4>
                                            <h4><b>Customer Card</b> :  ${check.getCustomerCard().getNumber()}, ${check.getCustomerCard().getSurname()}</h4>
                                            <br>
                                            <br>
                                            <div class="sales-container">
                                                <c:forEach items="${check.getSales()}" var="sale">
                                                    <div class="sale">
                                                        <h4>${sale.getStoreProduct().getUpc()}, ${sale.getStoreProduct().getProduct().getName()}, x${sale.getProductsNumber()}, ${sale.getPrice()}</h4>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <br>
                                            <br>
                                            <h4><b>Print Date</b> :  ${check.getPrintDate()}</h4>
                                            <h4><b>Total Sum</b> :  ${check.getTotalSum()}</h4>
                                            <h4><b>VAT</b> :  ${check.getVat()}</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/controller/checks/checkInfo?check_id=${check.getNumber()}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<%@include file="footer.jsp"%>
