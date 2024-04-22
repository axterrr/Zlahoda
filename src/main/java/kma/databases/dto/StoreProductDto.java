package kma.databases.dto;

import kma.databases.entities.IBuilder;

public class StoreProductDto {

    private String UPC;
    private String promUPC;
    private Long productId;
    private String price;
    private String amount;
    private String promotional;

    public static class Builder implements IBuilder<StoreProductDto> {

        StoreProductDto storeProduct = new StoreProductDto();

        public Builder setUPC(String upc) {
            storeProduct.UPC = upc;
            return this;
        }

        public Builder setPromUPC(String promUPC) {
            storeProduct.promUPC = promUPC;
            return this;
        }

        public Builder setProductId(Long productId) {
            storeProduct.productId = productId;
            return this;
        }

        public Builder setPrice(String price) {
            storeProduct.price = price;
            return this;
        }

        public Builder setAmount(String amount) {
            storeProduct.amount = amount;
            return this;
        }

        public Builder setPromotional(String promotional) {
            storeProduct.promotional = promotional;
            return this;
        }

        @Override
        public StoreProductDto build() {
            return storeProduct;
        }
    }

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String upc) {
        this.UPC = upc;
    }

    public String getPromUPC() {
        return promUPC;
    }

    public void setPromUPC(String promUPC) {
        this.promUPC = promUPC;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String isPromotional() {
        return promotional;
    }

    public void setPromotional(String promotional) {
        this.promotional = promotional;
    }
}
