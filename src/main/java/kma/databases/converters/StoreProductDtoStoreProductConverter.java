package kma.databases.converters;

import kma.databases.dto.StoreProductDto;
import kma.databases.entities.StoreProduct;

import java.math.BigDecimal;
import java.util.UUID;

public class StoreProductDtoStoreProductConverter {

    public static StoreProduct toStoreProduct(StoreProductDto storeProductDto) {
        StoreProduct.Builder builder = new StoreProduct.Builder()
                .setUPC(storeProductDto.getUPC())
                .setPromUPC(storeProductDto.getPromUPC())
                .setProductId(storeProductDto.getProductId())
                .setPrice(new BigDecimal(storeProductDto.getPrice()))
                .setAmount(Long.parseLong(storeProductDto.getAmount()))
                .setPromotional(storeProductDto.isPromotional().equals("true"));
        if(storeProductDto.getUPC()==null) {
            builder.setUPC(UUID.randomUUID().toString());
        }
        if(storeProductDto.getPromUPC() == null || storeProductDto.getPromUPC().isEmpty()) {
            builder.setPromUPC(null);
        }
        return builder.build();
    }
}
