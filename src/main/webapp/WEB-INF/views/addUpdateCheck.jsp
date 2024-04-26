<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h3>
            Add Check
        </h3>
    </div>

    <div class="row-fluid">
        <div class="col-sm-6 col-sm-offset-3 ">
            <form action="./addCheck" method="POST" role="form">

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
                    <label for="card">Customer Card</label>
                    <select class="form-control" id="card"
                            name="customerCard">
                        <option value=""></option>
                        <c:forEach items="${customerCards}" var="customer">
                            <option value="${customer.getNumber()}">
                                    ${customer.getSurname()}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group" id="saleContainer">

                </div>

                <div class="form-group">
                    <select class="form-control" id="product">
                        <c:forEach items="${storeProducts}" var="product">
                            <option value="${product.getUpc()}">
                                <c:if test="${product.isPromotional()}">prom</c:if> ${product.getProduct().getName()}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-default" id="submitButton">
                    Add
                </button>
            </form>
        </div>
    </div>
</div>
<script>
    function addSaleModule(productName) {
        let saleModule = document.getElementById(productName);
        if(saleModule)
        {
            let amount = document.getElementById(productName + 'amount');
            amount.value = parseInt(amount.value) + 1;
        }
        else {
            let saleModule = document.createElement('div');
            saleModule.className = 'sale';
            saleModule.id = productName;
            saleModule.style.width = '100%';
            saleModule.style.display = 'flex';
            saleModule.style.alignItems = 'stretch';

            let productNameElement = document.createElement('label');
            productNameElement.textContent = productName;
            saleModule.appendChild(productNameElement);

            let idStoreProduct = document.createElement('input');
            idStoreProduct.type = 'hidden';
            idStoreProduct.name = 'storeProducts[]';
            idStoreProduct.value = productName;
            saleModule.appendChild(idStoreProduct);

            let amountInput = document.createElement('input');
            amountInput.type = 'number';
            amountInput.id = productName + 'amount';
            amountInput.value = '1';
            amountInput.name = 'amount[]';
            saleModule.appendChild(amountInput);

            let incrementButton = document.createElement('button');
            incrementButton.type = 'button';
            incrementButton.textContent = '+';
            incrementButton.onclick = function () {
                amountInput.value = parseInt(amountInput.value) + 1;
            };
            saleModule.appendChild(incrementButton);

            let decrementButton = document.createElement('button');
            decrementButton.type = 'button';
            decrementButton.textContent = '-';
            decrementButton.onclick = function () {
                if (parseInt(amountInput.value) > 1) {
                    amountInput.value = parseInt(amountInput.value) - 1;
                }
                else
                {
                    saleModule.remove();
                }
            };
            saleModule.appendChild(decrementButton);

            let deleteButton = document.createElement('button');
            deleteButton.type = 'button';
            deleteButton.textContent = 'Delete';
            deleteButton.onclick = function () {
                saleModule.remove();
            };
            saleModule.appendChild(deleteButton);

            document.getElementById('saleContainer').appendChild(saleModule);
        }
    }

    document.getElementById('product').addEventListener('change', function() {
        let productName = this.options[this.selectedIndex].value;
        addSaleModule(productName);
    });
</script>

<%@include file="/WEB-INF/views/footer.jsp"%>
