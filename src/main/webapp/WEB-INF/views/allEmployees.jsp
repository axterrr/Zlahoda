<%@include file="header.jsp"%>

<div class="container-fluid">

    <div class="row-fluid" align="center">
        <h2>
            Employees
        </h2>
    </div>

    <div class="row-fluid" align="left">
        <div class="btn-group" role="group" aria-label="buttons">
            <button type="button" class="btn btn-default"
                    onclick="location.href='${pageContext.request.contextPath}/controller/employees/addEmployee';">
                Add Employee
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#searchByRole">
                Search by Role
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#searchBySurname">
                Search by Surname
            </button>
            <button type="button" class="btn btn-default"
                    onclick="location.href='${pageContext.request.contextPath}/controller/employees/report';">
                Report
            </button>
        </div>
    </div>

    <!-- modal searchByRole -->
    <div class="modal fade" id="searchByRole" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Search by Role
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/employees/role"
                            method="POST" role="form">

                        <div class="form-group">
                            <label for="role">Role</label>
                            <select class="form-control" id="role" name="role">
                                <option value="">All Employees</option>
                                <option value="cashier">Cashiers</option>
                                <option value="manager">Manager</option>
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

    <!-- modal searchBySurname -->
    <div class="modal fade" id="searchBySurname" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Search By Surname
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/employees/surname"
                            method="POST" role="form">

                        <div class="form-group">
                            <label for="surname">Search By Surname</label>
                            <input type="text" class="form-control" id="surname" name="surname" />
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
                <th>Surname</th>
                <th>Name</th>
                <th>Role</th>
                <th>Phone Number</th>
                <th>City</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.getId()}</td>
                    <td>${employee.getSurname()}</td>
                    <td>${employee.getName()}</td>
                    <td>${employee.getRole()}</td>
                    <td>${employee.getPhoneNumber()}</td>
                    <td>${employee.getCity()}</td>
                    <td><a href="${pageContext.request.contextPath}/controller/employees/employeeInfo?id_employee=${employee.getId()}">Full Info</a></td>
                    <td><a href="${pageContext.request.contextPath}/controller/employees/updateEmployee?id_employee=${employee.getId()}">Update</a></td>
                    <td><a href="${pageContext.request.contextPath}/controller/employees/deleteEmployee?id_employee=${employee.getId()}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<%@include file="footer.jsp"%>
