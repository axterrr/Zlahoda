package kma.databases.converters;

import kma.databases.dto.SaleDto;
import kma.databases.entities.Sale;

import java.math.BigDecimal;

public class SaleDtoSaleConverter {

    public static Sale toSale(SaleDto saleDto) {
        return new Sale.Builder()
                .setStoreProduct(saleDto.getStoreProduct())
                .setCheck(saleDto.getCheck())
                .setProductsNumber(Long.parseLong(saleDto.getProductsNumber()))
                .setPrice(new BigDecimal(saleDto.getPrice()))
                .build();
    }
}
