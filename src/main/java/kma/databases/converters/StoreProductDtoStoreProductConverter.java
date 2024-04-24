package kma.databases.converters;

import kma.databases.dto.StoreProductDto;
import kma.databases.entities.StoreProduct;

import java.math.BigDecimal;
import java.util.UUID;

public class StoreProductDtoStoreProductConverter {

    public static StoreProduct toStoreProduct(StoreProductDto storeProductDto) {
        StoreProduct.Builder builder = new StoreProduct.Builder()
                .setUPC(storeProductDto.getUPC())
                .setProm(storeProductDto.getProm())
                .setProduct(storeProductDto.getProduct())
                .setPrice(new BigDecimal(storeProductDto.getPrice()))
                .setAmount(Long.parseLong(storeProductDto.getAmount()))
                .setPromotional(storeProductDto.isPromotional().equals("true"));
        if(storeProductDto.getUPC()==null) {
            builder.setUPC(UUID.randomUUID().toString());
        }
        if(storeProductDto.getProm() == null || storeProductDto.getProm().getUpc() == null) {
            builder.setProm(null);
        }
        return builder.build();
    }
}
