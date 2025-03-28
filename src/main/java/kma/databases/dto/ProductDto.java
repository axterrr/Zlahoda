package kma.databases.dto;

import kma.databases.entities.Category;
import kma.databases.entities.IBuilder;

public class ProductDto {

    private Long id;
    private Category category;
    private String name;
    private String characteristics;

    public static class Builder implements IBuilder<ProductDto> {

        ProductDto product = new ProductDto();

        public Builder setId(Long id) {
            product.id = id;
            return this;
        }

        public Builder setCategory(Category category) {
            product.category = category;
            return this;
        }

        public Builder setName(String name) {
            product.name = name;
            return this;
        }

        public Builder setCharacteristics(String characteristics) {
            product.characteristics = characteristics;
            return this;
        }

        @Override
        public ProductDto build() {
            return product;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
}
