package kma.databases.entities;

public class Category {

    private Long number;
    private String name;

    public static class Builder implements IBuilder<Category> {

        Category category = new Category();

        public Builder setNumber(Long number) {
            category.number = number;
            return this;
        }

        public Builder setName(String name) {
            category.name = name;
            return this;
        }

        @Override
        public Category build() {
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
