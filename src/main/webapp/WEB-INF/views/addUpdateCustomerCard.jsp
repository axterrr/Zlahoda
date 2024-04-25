<%@include file="/WEB-INF/views/header.jsp"%>

<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h3>
            <c:choose>
                <c:when test="${not empty requestScope.customerCardDto.getNumber()}">
                    Update Customer Card
                </c:when>
                <c:otherwise>
                    Add Customer Card
                </c:otherwise>
            </c:choose>
        </h3>
    </div>

    <div class="row-fluid">
        <div class="col-sm-6 col-sm-offset-3 ">
            <c:choose>
                <c:when test="${not empty requestScope.customerCardDto.getNumber()}">
                    <form action="./updateCustomerCard" method="POST" role="form">
                </c:when>
                <c:otherwise>
                    <form action="./addCustomerCard" method="POST" role="form">
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
                                                                value="<c:out value="${requestScope.customerCardDto.getSurname()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="name">Name</label> <input type="text" class="form-control"
                                                              id="name" name="name"
                                                              placeholder="Name"
                                                              value="<c:out value="${requestScope.customerCardDto.getName()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="patronymic">Patronymic</label> <input type="text" class="form-control"
                                                                          id="patronymic" name="patronymic"
                                                                          placeholder="Patronymic"
                                                                          value="<c:out value="${requestScope.customerCardDto.getPatronymic()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone Number</label> <input type="text" class="form-control"
                                                                       id="phone" name="phoneNumber"
                                                                       placeholder="Phone Number"
                                                                       value="<c:out value="${requestScope.customerCardDto.getPhoneNumber()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="city">City</label> <input type="text" class="form-control"
                                                              id="city" name="city"
                                                              placeholder="City"
                                                              value="<c:out value="${requestScope.customerCardDto.getCity()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="street">Street</label> <input type="text" class="form-control"
                                                                  id="street" name="street"
                                                                  placeholder="Street"
                                                                  value="<c:out value="${requestScope.customerCardDto.getStreet()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="zipcode">ZipCode</label> <input type="text" class="form-control"
                                                                    id="zipcode" name="zipCode"
                                                                    placeholder="ZipCode"
                                                                    value="<c:out value="${requestScope.customerCardDto.getZipCode()}" />" />
                    </div>
                    <div class="form-group">
                        <label for="percent">Percent</label> <input type="number" class="form-control"
                                                                    id="percent" name="percent"
                                                                    placeholder="Percent"
                                                                    value="<c:out value="${requestScope.customerCardDto.getPercent()}" />" />
                    </div>
                    <c:if test="${not empty requestScope.customerCardDto.getNumber()}">
                        <input type="hidden" id="customerCard_id" name="id_customerCard"
                               value="<c:out value="${requestScope.customerCardDto.getNumber()}"/>" >
                    </c:if>
                    <c:choose>
                        <c:when test="${not empty requestScope.customerCardDto.getNumber()}">
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
