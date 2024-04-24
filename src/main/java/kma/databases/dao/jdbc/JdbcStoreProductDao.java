package kma.databases.dao.jdbc;

import kma.databases.dao.StoreProductDao;
import kma.databases.entities.StoreProduct;
import kma.databases.exceptions.ServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcStoreProductDao implements StoreProductDao {

    private static String GET_ALL = "SELECT * FROM `store_product` ORDER BY products_number";
    private static String GET_BY_ID = "SELECT * FROM `store_product` WHERE UPC=?";
    private static String CREATE = "INSERT INTO `store_product` " +
            "(UPC, UPC_prom, id_product, selling_price, products_number, promotional_product) VALUES (?, ?, ?, ?, ?, ?)";
    private static String UPDATE = "UPDATE `store_product` SET " +
            "UPC_prom=?, id_product=?, selling_price=?, products_number=?, promotional_product=?  WHERE UPC=?";
    private static String DELETE = "DELETE FROM `store_product` WHERE UPC=?";

    private static String UPC = "UPC";
    private static String PROM_UPC = "UPC_prom";
    private static String PRODUCT_ID = "id_product";
    private static String PRICE = "selling_price";
    private static String PRODUCTS_NUMBER = "products_number";
    private static String PROMOTIONAL_PRODUCT = "promotional_product";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcStoreProductDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcStoreProductDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    @Override
    public List<StoreProduct> getAll() {
        List<StoreProduct> products = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                products.add(extractStoreProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return products;
    }

    @Override
    public Optional<StoreProduct> getById(String id) {
        Optional<StoreProduct> product = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                product = Optional.of(extractStoreProductFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return product;
    }

    @Override
    public void create(StoreProduct product) {
        try (PreparedStatement query = connection.prepareStatement(CREATE)) {
            query.setString(1, product.getUpc());
            //query.setString(2, product.getProm());
            //query.setLong(3, product.getProductId());
            query.setBigDecimal(4, product.getPrice());
            query.setLong(5, product.getAmount());
            query.setBoolean(6, product.isPromotional());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void update(StoreProduct product) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            //query.setString(1, product.getProm());
            //query.setLong(2, product.getProductId());
            query.setBigDecimal(3, product.getPrice());
            query.setLong(4, product.getAmount());
            query.setBoolean(5, product.isPromotional());
            query.setString(6, product.getUpc());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ServerException(e);
            }
        }
    }

    protected static StoreProduct extractStoreProductFromResultSet(ResultSet resultSet) throws SQLException {
        return new StoreProduct.Builder().setUPC(resultSet.getString(UPC)).setPrice(resultSet.getBigDecimal(PRICE))
                .setAmount(resultSet.getLong(PRODUCTS_NUMBER)).setPromotional(resultSet.getBoolean(PROMOTIONAL_PRODUCT))
                //.setProduct(resultSet.getLong(PRODUCT_ID))
                //.setProm(resultSet.getString(PROM_UPC))
                .build();
    }

}
