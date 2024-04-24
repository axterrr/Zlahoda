package kma.databases.dao;

import kma.databases.entities.Product;
import kma.databases.entities.StoreProduct;

import java.util.List;

public interface StoreProductDao extends GenericDao<StoreProduct, String>, AutoCloseable {

    List<StoreProduct> getByUPC(String upc);
    List<StoreProduct> getPromotionalOrderByAmount();
    List<StoreProduct> getPromotionalOrderByName();
    List<StoreProduct> getNotPromotionalOrderByAmount();
    List<StoreProduct> getNotPromotionalOrderByName();
    List<StoreProduct> getAllOrderByName();
    void close();
}
