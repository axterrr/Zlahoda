<%@include file="/WEB-INF/views/header.jsp"%>

<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h3>
            <c:choose>
                <c:when test="${not empty requestScope.employeeDto.getId()}">
                    Update Employee
                </c:when>
                <c:otherwise>
                    Add Employee
                </c:otherwise>
            </c:choose>
        </h3>
    </div>

    <div class="row-fluid">
        <div class="col-sm-6 col-sm-offset-3 ">
            <c:choose>
            <c:when test="${not empty requestScope.employeeDto.getId()}">
            <form action="./updateEmployee" method="POST" role="form">
                </c:when>
                <c:otherwise>
                <form action="./addEmployee" method="POST" role="form">
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
                        <label for="surname">Surname</label> <input type="text" class="form-control"
                                                                                        id="surname" name="surname"
                                                                                        placeholder="Surname"
                                                                                        value="<c:out value="${requestScope.employeeDto.getSurname()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="name">Name</label> <input type="text" class="form-control"
                                                                                           id="name" name="name"
                                                                                           placeholder="Name"
                                                                                           value="<c:out value="${requestScope.employeeDto.getName()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="patronymic">Patronymic</label> <input type="text" class="form-control"
                                                              id="patronymic" name="patronymic"
                                                              placeholder="Patronymic"
                                                              value="<c:out value="${requestScope.employeeDto.getPatronymic()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="role">Role</label>
                        <select class="form-control" id="role" name="role" >
                            <c:choose>
                                <c:when test="${requestScope.employeeDto.getRole().getValue() == 'manager'}">
                                    <option value="cashier">Cashier</option>
                                    <option value="manager" selected>Manager</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="cashier" selected>Cashier</option>
                                    <option value="manager">Manager</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="salary">Salary</label> <input type="number" class="form-control"
                                                                      id="salary" name="salary"
                                                                      placeholder="Salary"
                                                                      value="<c:out value="${requestScope.employeeDto.getSalary()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="birthdate">Date of Birth</label> <input type="date" class="form-control"
                                                                  id="birthdate" name="dateOfBirth"
                                                                  placeholder="Date of Birth"
                                                                            value="<c:out value="${requestScope.employeeDto.getDateOfBirthString()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="startdate">Date of Start</label> <input type="date" class="form-control"
                                                                            id="startdate" name="dateOfStart"
                                                                            placeholder="Date of Start"
                                                                            value="<c:out value="${requestScope.employeeDto.getDateOfStartString()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone Number</label> <input type="text" class="form-control"
                                                                                         id="phone" name="phoneNumber"
                                                                                         placeholder="Phone Number"
                                                                                         value="<c:out value="${requestScope.employeeDto.getPhoneNumber()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="city">City</label> <input type="text" class="form-control"
                                                                                           id="city" name="city"
                                                                                           placeholder="City"
                                                                                           value="<c:out value="${requestScope.employeeDto.getCity()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="street">Street</label> <input type="text" class="form-control"
                                                                                           id="street" name="street"
                                                                                           placeholder="Street"
                                                                                           value="<c:out value="${requestScope.employeeDto.getStreet()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="zipcode">ZipCode</label> <input type="text" class="form-control"
                                                                                           id="zipcode" name="zipCode"
                                                                                           placeholder="ZipCode"
                                                                                           value="<c:out value="${requestScope.employeeDto.getZipCode()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label> <input type="password" class="form-control"
                                                                                            id="password" name="password"
                                                                                            placeholder="Password" />
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password</label> <input
                            type="password" class="form-control" id="confirmPassword"
                            name="confirmPassword"
                            placeholder="Confirm Password" />
                    </div>
                    <c:if test="${not empty requestScope.employeeDto.getId()}">
                        <input type="hidden" id="employee_id" name="id_employee"
                               value="<c:out value="${requestScope.employeeDto.getId()}"/>" >
                    </c:if>
                    <c:choose>
                        <c:when test="${not empty requestScope.employeeDto.getId()}">
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
