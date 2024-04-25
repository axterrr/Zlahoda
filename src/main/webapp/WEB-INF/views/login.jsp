<%@include file="/WEB-INF/views/header.jsp"%>

<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h3>
            Authorization
        </h3>
    </div>

    <div class="row-fluid">
        <div class="col-sm-6 col-sm-offset-3 ">
            <form action="./login" method="POST" role="form">

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
                    <label for="phone-number">Phone number</label>
                        <input type="text" class="form-control"
                         id="phone-number" name="phoneNumber"
                         placeholder="Phone number"
                         value="<c:out value="${requestScope.loginUser.getPhone()}" />" />
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                        <input type="password" class="form-control"
                            id="password" name="password"
                            placeholder="Password"/>
                </div>
                <button type="submit" class="btn btn-default" id="submitButton">
                    Log In
                </button>
            </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/footer.jsp"%>
