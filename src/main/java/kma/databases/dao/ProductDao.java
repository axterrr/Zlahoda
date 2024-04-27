package kma.databases.dao;

import kma.databases.entities.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product, Long>, AutoCloseable {

    List<Product> getByCategory(Long categoryId);
    List<Product> getByName(String name);
    List<Product> getCustomerFavourite(String customerCardNumber);
    void close();
}
