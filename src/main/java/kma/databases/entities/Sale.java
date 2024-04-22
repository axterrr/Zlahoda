package kma.databases.entities;

import java.math.BigDecimal;

public class Sale {

    private String storeProductUPC;
    private String checkNumber;
    private Long productsNumber;
    private BigDecimal price;

    public static class Builder implements IBuilder<Sale> {

        Sale sale = new Sale();

        public Builder setStoreProductUPC(String storeProductUPC) {
            sale.storeProductUPC = storeProductUPC;
            return this;
        }

        public Builder setCheckNumber(String checkNumber) {
            sale.checkNumber = checkNumber;
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

    public String getStoreProductUPC() {
        return storeProductUPC;
    }

    public void setStoreProductUPC(String storeProductUPC) {
        this.storeProductUPC = storeProductUPC;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
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
