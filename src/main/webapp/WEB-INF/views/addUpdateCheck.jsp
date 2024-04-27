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
    function addSaleModule(productId, productName) {
        let saleModule = document.getElementById(productId);
        if(saleModule)
        {
            let amount = document.getElementById(productId + 'amount');
            amount.value = parseInt(amount.value) + 1;
        }
        else {
            let saleModule = document.createElement('div');
            saleModule.className = 'sale';
            saleModule.id = productId;
            saleModule.style.width = '100%';
            saleModule.style.height = 'fit-content';
            saleModule.style.display = 'flex';
            saleModule.style.justifyContent = 'space-between';
            saleModule.style.alignItems = 'center';
            saleModule.style.border = 'solid 1px black';
            saleModule.style.paddingLeft = '25px';
            saleModule.style.paddingRight = '25px';
            saleModule.style.paddingTop = '10px';
            saleModule.style.paddingBottom = '10px';

            let productNameElement = document.createElement('p');
            productNameElement.textContent = productName;
            productNameElement.style.width = '200px';
            productNameElement.style.height = 'fit-content';
            productNameElement.style.margin = '0px';
            productNameElement.style.fontSize = '15px';
            saleModule.appendChild(productNameElement);

            let idStoreProduct = document.createElement('input');
            idStoreProduct.type = 'hidden';
            idStoreProduct.name = 'storeProducts[]';
            idStoreProduct.value = productId;
            saleModule.appendChild(idStoreProduct);

            let amountInput = document.createElement('input');
            amountInput.type = 'number';
            amountInput.classList.add('btn');
            amountInput.id = productId + 'amount';
            amountInput.value = '1';
            amountInput.name = 'amount[]';
            amountInput.style.width = '75px'
            amountInput.style.height = '40px'
            saleModule.appendChild(amountInput);

            let incrementButton = document.createElement('button');
            incrementButton.classList.add('btn');
            incrementButton.type = 'button';
            incrementButton.textContent = '+';
            incrementButton.style.width = '40px';
            incrementButton.style.height = '40px';
            incrementButton.style.border = 'none';
            incrementButton.onclick = function () {
                amountInput.value = parseInt(amountInput.value) + 1;
            };
            saleModule.appendChild(incrementButton);

            let decrementButton = document.createElement('button');
            decrementButton.classList.add('btn');
            decrementButton.type = 'button';
            decrementButton.textContent = '-';
            decrementButton.style.width = '40px';
            decrementButton.style.height = '40px';
            decrementButton.style.border = 'none';
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
            deleteButton.classList.add('btn');
            deleteButton.type = 'button';
            deleteButton.textContent = 'Delete';
            deleteButton.style.height = '40px';
            deleteButton.style.border = 'none';
            deleteButton.onclick = function () {
                saleModule.remove();
            };
            saleModule.appendChild(deleteButton);

            document.getElementById('saleContainer').appendChild(saleModule);
        }
    }

    document.getElementById('product').addEventListener('change', function() {
        let productId = this.options[this.selectedIndex].value;
        let productName = this.options[this.selectedIndex].textContent;
        addSaleModule(productId, productName);
    });
</script>

<%@include file="/WEB-INF/views/footer.jsp"%>
