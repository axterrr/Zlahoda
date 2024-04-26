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
                    onclick="printTable()">
                Report
            </button>
        </div>
    </div>

    <script>
        function printTable() {
            var table = document.getElementById("emplTable");
            if (table) {
                var iframe = document.createElement('iframe');
                iframe.style.display = 'none';
                document.body.appendChild(iframe);
                var iframeDoc = iframe.contentWindow.document;
                iframeDoc.write('<style>body {padding-top: 50px; padding-bottom: 50px; justify-content: stretch;}</style>');
                iframeDoc.write('<h2 align="center">Employees</h2>');
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
        <table class="table table-bordered" id="emplTable">
            <thead>
            <tr>
                <th>ID</th>
                <th>Surname</th>
                <th>Name</th>
                <th class="show-print">Patronymic</th>
                <th>Role</th>
                <th class="show-print">Salary</th>
                <th class="show-print">Date of Birth</th>
                <th class="show-print">Date of Start</th>
                <th>Phone Number</th>
                <th>City</th>
                <th class="show-print">Street</th>
                <th class="show-print">Zip Code</th>
                <th class="tdbutton"></th>
                <th class="tdbutton"></th>
                <th class="tdbutton"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.getId()}</td>
                    <td>${employee.getSurname()}</td>
                    <td>${employee.getName()}</td>
                    <td class="show-print">${employee.getPatronymic()}</td>
                    <td>${employee.getRole()}</td>
                    <td class="show-print">${employee.getSalary()}</td>
                    <td class="show-print">${employee.getDateOfBirthString()}</td>
                    <td class="show-print">${employee.getDateOfStartString()}</td>
                    <td>${employee.getPhoneNumber()}</td>
                    <td>${employee.getCity()}</td>
                    <td class="show-print">${employee.getStreet()}</td>
                    <td class="show-print">${employee.getZipCode()}</td>
                    <td class="tdbutton"><button type="button" class="btn btn-default" data-toggle="modal" data-target="#fullInfoModal${employee.getId()}">
                        Full Info
                    </button>
                        <!-- modal filter -->
                        <div class="modal fade tdbutton" id="fullInfoModal${employee.getId()}" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="Close">
                                            <span>&times;</span>
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            Employee ${employee.getId()}
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="full-info">
                                            <h4><b>Surname</b> :  ${employee.getSurname()}</h4>
                                            <h4><b>Name</b> :  ${employee.getName()}</h4>
                                            <h4><b>Patronymic</b> :  ${employee.getPatronymic()}</h4>
                                            <h4><b>Role</b> :  ${employee.getRole()}</h4>
                                            <h4><b>Salary</b> :  ${employee.getSalary()}</h4>
                                            <h4><b>Date of Birth</b> :  ${employee.getDateOfBirthString()}</h4>
                                            <h4><b>Date of Start</b> :  ${employee.getDateOfStartString()}</h4>
                                            <h4><b>City</b> :  ${employee.getCity()}</h4>
                                            <h4><b>Street</b> :  ${employee.getStreet()}</h4>
                                            <h4><b>ZipCode</b> :  ${employee.getZipCode()}</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <td class="tdbutton"><a href="${pageContext.request.contextPath}/controller/employees/updateEmployee?id_employee=${employee.getId()}">Update</a></td>
                    <td class="tdbutton"><a href="${pageContext.request.contextPath}/controller/employees/deleteEmployee?id_employee=${employee.getId()}">Delete</a></td>
                </tr>
            </c:forEach>
            <style>
                .show-print {
                    display: none;
                }
                @media print {
                    .show-print {
                        display: table-cell;
                    }
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
