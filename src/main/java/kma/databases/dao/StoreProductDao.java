package kma.databases.dao;

import kma.databases.entities.Product;
import kma.databases.entities.StoreProduct;
import kma.databases.exceptions.ServerException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StoreProductDao extends GenericDao<StoreProduct, String>, AutoCloseable {

    List<StoreProduct> getByUPC(String upc);
    List<StoreProduct> getPromotionalOrderByAmount();
    List<StoreProduct> getPromotionalOrderByName();
    List<StoreProduct> getNotPromotionalOrderByAmount();
    List<StoreProduct> getNotPromotionalOrderByName();
    List<StoreProduct> getAllOrderByName();
    Optional<StoreProduct> getPromByProductId(Long id);
    Optional<StoreProduct> getNotPromByProductId(Long id);
    void addPromotionalProduct(String upc, String upcProm);
    void updateAmount(String productId, Long newAmount);
    void close();
}
