package kma.databases.dto;

import kma.databases.entities.IBuilder;
import kma.databases.entities.Product;
import kma.databases.entities.StoreProduct;

public class StoreProductDto {

    private String UPC;
    private StoreProduct prom;
    private Product product;
    private String price;
    private String amount;
    private String promotional;

    public static class Builder implements IBuilder<StoreProductDto> {

        StoreProductDto storeProduct = new StoreProductDto();

        public Builder setUPC(String upc) {
            storeProduct.UPC = upc;
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

    public String getUpc() {
        return UPC;
    }

    public void setUpc(String upc) {
        this.UPC = upc;
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
