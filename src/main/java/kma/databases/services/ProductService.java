package kma.databases.services;

import kma.databases.converters.ProductDtoProductConverter;
import kma.databases.dao.DaoFactory;
import kma.databases.dao.ProductDao;
import kma.databases.dto.ProductDto;
import kma.databases.entities.Product;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private final DaoFactory daoFactory;

    ProductService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final ProductService INSTANCE = new ProductService(DaoFactory.getDaoFactory());
    }

    public static ProductService getInstance() {
        return ProductService.Holder.INSTANCE;
    }

    public List<Product> getAllProducts() {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.getAll();
        }
    }

    public Optional<Product> getProductById(Long productID) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.getById(productID);
        }
    }

    public void createProduct(ProductDto productDto) {
        Product product = ProductDtoProductConverter.toProduct(productDto);
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.create(product);
        }
    }

    public void updateProduct(ProductDto productDto) {
        Product product = ProductDtoProductConverter.toProduct(productDto);
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.update(product);
        }
    }

    public void deleteProduct(Long productID) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.delete(productID);
        }
    }

    public List<Product> searchProductByCategory(Long categoryId) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.getByCategory(categoryId);
        }
    }

    public List<Product> searchProductByName(String name) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.getByName(name);
        }
    }

    public List<Product> findCustomerFavouriteProducts(String cardId) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.getCustomerFavourite(cardId);
        }
    }
}
