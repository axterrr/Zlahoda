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
            <button type="button" class="btn btn-default"
                    onclick="location.href='${pageContext.request.contextPath}/controller/checks/todayChecks';">
                Today
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#searchByNumber">
                Search by Number
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#filter">
                Filter
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    onclick="location.href='${pageContext.request.contextPath}/controller/checks/???????????';">
                Total Sum
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    onclick="location.href='${pageContext.request.contextPath}/controller/checks/???????????';">
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
                            action="${pageContext.request.contextPath}/controller/checks/?number"
                            method="POST" role="form">

                        <div class="form-group">
                            <label for="number">Search By Number</label>
                            <input type="text" class="form-control" id="number" name="number" />
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
                        Filter
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/checks/?filter"
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
                            <input type="date" class="form-control" id="date-from" name="date-from"/>
                            <label for="date-to">To</label>
                            <input type="date" class="form-control" id="date-to" name="date-to" />
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
                    <td>${check.getEmployee().getSurname()}</td>
                    <td>${check.getCustomerCard().getSurname()}</td>
                    <td>${check.getPrintDate()}</td>
                    <td>${check.getTotalSum()}</td>
                    <td>${check.getVat()}</td>
                    <td><a href="${pageContext.request.contextPath}/controller/checks/checkInfo?check_id=${check.getNumber()}">Full Info</a></td>
                    <td><a href="${pageContext.request.contextPath}/controller/checks/checkInfo?check_id=${check.getNumber()}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<%@include file="footer.jsp"%>
