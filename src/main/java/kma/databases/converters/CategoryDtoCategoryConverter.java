package kma.databases.converters;

import kma.databases.dto.CategoryDto;
import kma.databases.entities.Category;

import java.math.BigDecimal;

public class CategoryDtoCategoryConverter {

    public static Category toCategory(CategoryDto categoryDto) {
        return new Category.Builder()
                .setNumber(categoryDto.getNumber())
                .setName(categoryDto.getName())
                .build();
    }
}
