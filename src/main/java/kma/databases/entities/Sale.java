package kma.databases.entities;

import java.math.BigDecimal;

public class Sale {

    private StoreProduct storeProduct;
    private Check check;
    private Long productsNumber;
    private BigDecimal price;

    public static class Builder implements IBuilder<Sale> {

        Sale sale = new Sale();

        public Builder setStoreProduct(StoreProduct storeProduct) {
            sale.storeProduct = storeProduct;
            return this;
        }

        public Builder setCheck(Check check) {
            sale.check = check;
            return this;
        }

        public Builder setProductsNumber(Long productsNumber) {
            sale.productsNumber = productsNumber;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            sale.price = price;
            return this;
        }

        @Override
        public Sale build() {
            return sale;
        }
    }

    public StoreProduct getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(StoreProduct storeProduct) {
        this.storeProduct = storeProduct;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Long getProductsNumber() {
        return productsNumber;
    }

    public void setProductsNumber(Long productsNumber) {
        this.productsNumber = productsNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
