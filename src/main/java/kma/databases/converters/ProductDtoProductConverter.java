package kma.databases.converters;

import kma.databases.dto.ProductDto;
import kma.databases.entities.Product;

public class ProductDtoProductConverter {

    public static Product toProduct(ProductDto productDto) {
        return new Product.Builder()
                .setId(productDto.getId())
                .setCategory(productDto.getCategory())
                .setName(productDto.getName())
                .setCharacteristics(productDto.getCharacteristics())
                .build();
    }
}
