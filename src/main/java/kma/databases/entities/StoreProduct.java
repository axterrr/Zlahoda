package kma.databases.entities;

import java.math.BigDecimal;

public class StoreProduct {

    private String upc;
    private StoreProduct prom;
    private Product product;
    private BigDecimal price;
    private Long amount;
    private boolean promotional;

    public static class Builder implements IBuilder<StoreProduct> {

        StoreProduct storeProduct = new StoreProduct();

        public Builder setUPC(String upc) {
            storeProduct.upc = upc;
            return this;
        }

        public Builder setProm(StoreProduct prom) {
            storeProduct.prom = prom;
            return this;
        }

        public Builder setProduct(Product product) {
            storeProduct.product = product;
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

    public StoreProduct getProm() {
        return prom;
    }

    public void setProm(StoreProduct prom) {
        this.prom = prom;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
