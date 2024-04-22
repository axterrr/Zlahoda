package kma.databases.services;

import kma.databases.converters.CategoryDtoCategoryConverter;
import kma.databases.dao.CategoryDao;
import kma.databases.dao.DaoFactory;
import kma.databases.dto.CategoryDto;
import kma.databases.entities.Category;

import java.util.List;
import java.util.Optional;

public class CategoryService {

    private final DaoFactory daoFactory;

    CategoryService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final CategoryService INSTANCE = new CategoryService(DaoFactory.getDaoFactory());
    }

    public static CategoryService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Category> getAllCategories() {
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            return categoryDao.getAll();
        }
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            return categoryDao.getById(categoryId);
        }
    }

    public void createCategory(CategoryDto categoryDto) {
        Category category = CategoryDtoCategoryConverter.toCategory(categoryDto);
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            categoryDao.create(category);
        }
    }

    public void updateCategory(CategoryDto categoryDto) {
        Category category = CategoryDtoCategoryConverter.toCategory(categoryDto);
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            categoryDao.update(category);
        }
    }

    public void deleteCategory(Long categoryId) {
        try (CategoryDao categoryDao = daoFactory.createCategoryDao()) {
            categoryDao.delete(categoryId);
        }
    }
}
