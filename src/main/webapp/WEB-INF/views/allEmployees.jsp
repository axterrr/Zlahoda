<%@include file="header.jsp"%>

<div class="container-fluid">

    <div class="row-fluid" align="center">
        <h2>
            Employees
        </h2>
    </div>

    <div class="row-fluid" align="left">
<c:if test="${employee.getRole().getValue() eq 'manager' }">
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
</c:if>
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
    <c:if test="${employee.getRole().getValue() eq 'manager' }">
                <th class="tdbutton"></th>
                <th class="tdbutton"></th>
    </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${employees}" var="empl">
                <tr>
                    <td>${empl.getId()}</td>
                    <td>${empl.getSurname()}</td>
                    <td>${empl.getName()}</td>
                    <td class="show-print">${empl.getPatronymic()}</td>
                    <td>${empl.getRole()}</td>
                    <td class="show-print">${empl.getSalary()}</td>
                    <td class="show-print">${empl.getDateOfBirthString()}</td>
                    <td class="show-print">${empl.getDateOfStartString()}</td>
                    <td>${empl.getPhoneNumber()}</td>
                    <td>${empl.getCity()}</td>
                    <td class="show-print">${empl.getStreet()}</td>
                    <td class="show-print">${empl.getZipCode()}</td>
                    <td class="tdbutton"><button type="button" class="btn btn-default" data-toggle="modal" data-target="#fullInfoModal${empl.getId()}">
                        Full Info
                    </button>
                        <!-- modal filter -->
                        <div class="modal fade tdbutton" id="fullInfoModal${empl.getId()}" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="Close">
                                            <span>&times;</span>
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            Employee ${empl.getId()}
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="full-info">
                                            <h4><b>Surname</b> :  ${empl.getSurname()}</h4>
                                            <h4><b>Name</b> :  ${empl.getName()}</h4>
                                            <h4><b>Patronymic</b> :  ${empl.getPatronymic()}</h4>
                                            <h4><b>Role</b> :  ${empl.getRole()}</h4>
                                            <h4><b>Salary</b> :  ${empl.getSalary()}</h4>
                                            <h4><b>Date of Birth</b> :  ${empl.getDateOfBirthString()}</h4>
                                            <h4><b>Date of Start</b> :  ${empl.getDateOfStartString()}</h4>
                                            <h4><b>City</b> :  ${empl.getCity()}</h4>
                                            <h4><b>Street</b> :  ${empl.getStreet()}</h4>
                                            <h4><b>ZipCode</b> :  ${empl.getZipCode()}</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <c:if test="${employee.getRole().getValue() eq 'manager' }">
                    <td class="tdbutton"><a href="${pageContext.request.contextPath}/controller/employees/updateEmployee?id_employee=${empl.getId()}">Update</a></td>
                    <td class="tdbutton"><a href="${pageContext.request.contextPath}/controller/employees/deleteEmployee?id_employee=${empl.getId()}">Delete</a></td>
                    </c:if>
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
