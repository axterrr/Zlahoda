package kma.databases.entities;

import java.math.BigDecimal;

public class StoreProduct {

    private String upc;
    private String promUPC;
    private Long productId;
    private BigDecimal price;
    private Long amount;
    private boolean promotional;

    public static class Builder implements IBuilder<StoreProduct> {

        StoreProduct storeProduct = new StoreProduct();

        public Builder setUPC(String upc) {
            storeProduct.upc = upc;
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

        public Builder setPrice(BigDecimal price) {
            storeProduct.price = price;
            return this;
        }

        public Builder setAmount(Long amount) {
            storeProduct.amount = amount;
            return this;
        }

        public Builder setPromotional(boolean promotional) {
            storeProduct.promotional = promotional;
            return this;
        }

        @Override
        public StoreProduct build() {
            return storeProduct;
        }
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public boolean isPromotional() {
        return promotional;
    }

    public void setPromotional(boolean promotional) {
        this.promotional = promotional;
    }
}
