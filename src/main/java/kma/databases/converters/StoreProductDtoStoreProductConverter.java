package kma.databases.converters;

import kma.databases.dto.StoreProductDto;
import kma.databases.entities.StoreProduct;

import java.math.BigDecimal;
import java.util.UUID;

public class StoreProductDtoStoreProductConverter {

    public static StoreProduct toStoreProduct(StoreProductDto storeProductDto) {
        StoreProduct.Builder builder = new StoreProduct.Builder()
                .setUPC(storeProductDto.getUpc())
                .setProm(storeProductDto.getProm())
                .setProduct(storeProductDto.getProduct())
                .setPrice(new BigDecimal(storeProductDto.getPrice()))
                .setAmount(Long.parseLong(storeProductDto.getAmount()))
                .setPromotional(storeProductDto.isPromotional().equals("true"));
        if(storeProductDto.getUpc()==null) {
            String upc = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12);
            builder.setUPC(upc);
            storeProductDto.setUpc(upc);
        }
        return builder.build();
    }
}
