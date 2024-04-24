package kma.databases.dto;

import kma.databases.entities.Check;
import kma.databases.entities.IBuilder;
import kma.databases.entities.StoreProduct;

public class SaleDto {

    private StoreProduct storeProduct;
    private Check check;
    private String productsNumber;
    private String price;

    public static class Builder implements IBuilder<SaleDto> {

        SaleDto sale = new SaleDto();

        public Builder setStoreProduct(StoreProduct storeProduct) {
            sale.storeProduct = storeProduct;
            return this;
        }

        public Builder setCheck(Check check) {
            sale.check = check;
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
