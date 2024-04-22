package kma.databases.dto;

import kma.databases.entities.IBuilder;

public class CategoryDto {

    private Long number;
    private String name;

    public static class Builder implements IBuilder<CategoryDto> {

        CategoryDto category = new CategoryDto();

        public Builder setNumber(Long number) {
            category.number = number;
            return this;
        }

        public Builder setName(String name) {
            category.name = name;
            return this;
        }

        @Override
        public CategoryDto build() {
            return category;
        }
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long id) {
        this.number = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
