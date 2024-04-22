package kma.databases.dao;

import kma.databases.entities.StoreProduct;

public interface StoreProductDao extends GenericDao<StoreProduct, String>, AutoCloseable {



    void close();
}
