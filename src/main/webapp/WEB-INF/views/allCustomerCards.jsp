<%@include file="header.jsp"%>

<div class="container-fluid">

    <div class="row-fluid" align="center">
        <h2>
            Customer Cards
        </h2>
    </div>

    <div class="row-fluid" align="left">
        <div class="btn-group" role="group" aria-label="buttons">
            <button type="button" class="btn btn-default"
                    onclick="location.href='${pageContext.request.contextPath}/controller/customerCards/addCustomerCard';">
                Add Customer Card
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#searchBySurname">
                Search by Surname
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#searchByPercent">
                Search by Percent
            </button>
            <button type="button" class="btn btn-default"
                    onclick="location.href='${pageContext.request.contextPath}/controller/customerCards/report';">
                Report
            </button>
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
                            action="${pageContext.request.contextPath}/controller/customerCards/surname"
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

    <!-- modal searchByPercent -->
    <div class="modal fade" id="searchByPercent" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Search By Percent
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/customerCards/percent"
                            method="POST" role="form">
                        <div class="form-group">
                            <label for="percentFrom">From</label>
                            <input type="text" class="form-control" id="percentFrom" name="percentFrom"/>
                            <label for="percentTo">To</label>
                            <input type="text" class="form-control" id="percentTo" name="percentTo" />
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
                <th>Card Number</th>
                <th>Surname</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th>Percent</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customerCards}" var="customer">
                <tr>
                    <td>${customer.getNumber()}</td>
                    <td>${customer.getSurname()}</td>
                    <td>${customer.getName()}</td>
                    <td>${customer.getPhoneNumber()}</td>
                    <td>${customer.getPercent()}</td>
                    <td><button type="button" class="btn btn-default" data-toggle="modal" data-target="#fullInfoModal${customer.getNumber()}">
                        Full Info
                    </button>
                        <!-- modal filter -->
                        <div class="modal fade" id="fullInfoModal${customer.getNumber()}" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="Close">
                                            <span>&times;</span>
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            Customer Card ${customer.getNumber()}
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="full-info">
                                            <h4><b>Surname</b> :  ${customer.getSurname()}</h4>
                                            <h4><b>Name</b> :  ${customer.getName()}</h4>
                                            <h4><b>Patronymic</b> :  ${customer.getPatronymic()}</h4>
                                            <h4><b>Phone Number</b> :  ${customer.getPhoneNumber()}</h4>
                                            <h4><b>City</b> :  ${customer.getCity()}</h4>
                                            <h4><b>Street</b> :  ${customer.getStreet()}</h4>
                                            <h4><b>ZipCode</b> :  ${customer.getZipCode()}</h4>
                                            <h4><b>Percent</b> :  ${customer.getPercent()}</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <td><a href="${pageContext.request.contextPath}/controller/customerCards/updateCustomerCard?id_customerCard=${customer.getNumber()}">Update</a></td>
                    <td><a href="${pageContext.request.contextPath}/controller/customerCards/deleteCustomerCard?id_customerCard=${customer.getNumber()}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<%@include file="footer.jsp"%>
