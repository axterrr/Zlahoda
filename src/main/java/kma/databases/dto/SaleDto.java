package kma.databases.dto;

import kma.databases.entities.IBuilder;

public class SaleDto {

    private String storeProductUPC;
    private String checkNumber;
    private String productsNumber;
    private String price;

    public static class Builder implements IBuilder<SaleDto> {

        SaleDto sale = new SaleDto();

        public Builder setStoreProductUPC(String storeProductUPC) {
            sale.storeProductUPC = storeProductUPC;
            return this;
        }

        public Builder setCheckNumber(String checkNumber) {
            sale.checkNumber = checkNumber;
            return this;
        }

        public Builder setProductsNumber(String productsNumber) {
            sale.productsNumber = productsNumber;
            return this;
        }

        public Builder setPrice(String price) {
            sale.price = price;
            return this;
        }

        @Override
        public SaleDto build() {
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

    public String getProductsNumber() {
        return productsNumber;
    }

    public void setProductsNumber(String productsNumber) {
        this.productsNumber = productsNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
