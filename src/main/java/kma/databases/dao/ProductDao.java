package kma.databases.dao;

import kma.databases.entities.Product;

public interface ProductDao extends GenericDao<Product, Long>, AutoCloseable {



    void close();
}
