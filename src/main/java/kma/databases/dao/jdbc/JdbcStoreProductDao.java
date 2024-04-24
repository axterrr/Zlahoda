package kma.databases.dao.jdbc;

import kma.databases.dao.StoreProductDao;
import kma.databases.entities.Product;
import kma.databases.entities.StoreProduct;
import kma.databases.exceptions.ServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcStoreProductDao implements StoreProductDao {

    private static String GET_ALL = "SELECT * FROM (`store_product` SP1 JOIN `product` USING (id_product) " +
            "JOIN `category` USING (category_number)) LEFT JOIN `store_product` SP2 ON SP1.UPC_prom = SP2.UPC " +
            "ORDER BY SP1.products_number";
    private static String GET_BY_ID = "SELECT * FROM (`store_product` SP1 JOIN `product` USING (id_product) " +
            "JOIN `category` USING (category_number)) LEFT JOIN `store_product` SP2 ON SP1.UPC_prom = SP2.UPC " +
            "WHERE SP1.UPC=?";
    private static String CREATE = "INSERT INTO `store_product` " +
            "(UPC, UPC_prom, id_product, selling_price, products_number, promotional_product) VALUES (?, ?, ?, ?, ?, ?)";
    private static String UPDATE = "UPDATE `store_product` SET " +
            "UPC_prom=?, id_product=?, selling_price=?, products_number=?, promotional_product=?  WHERE UPC=?";
    private static String DELETE = "DELETE FROM `store_product` WHERE UPC=?";
    private static String GET_BY_UPC = "SELECT * FROM (`store_product` SP1 JOIN `product` USING (id_product)" +
            "JOIN `category` USING (category_number)) LEFT JOIN `store_product` SP2 ON SP1.UPC_prom = SP2.UPC " +
            "WHERE LOWER(SP1.UPC) LIKE CONCAT('%', LOWER(?), '%')";
    private static String GET_PROMOTIONAL_ORDER_BY_AMOUNT = "SELECT * FROM (`store_product` SP1 JOIN `product` USING (id_product)" +
            "JOIN `category` USING (category_number)) LEFT JOIN `store_product` SP2 ON SP1.UPC_prom = SP2.UPC " +
            "WHERE SP1.promotional_product ORDER BY SP1.products_number";
    private static String GET_PROMOTIONAL_ORDER_BY_NAME = "SELECT * FROM (`store_product` SP1 JOIN `product` USING (id_product)" +
            "JOIN `category` USING (category_number)) LEFT JOIN `store_product` SP2 ON SP1.UPC_prom = SP2.UPC " +
            "WHERE SP1.promotional_product ORDER BY product_name";
    private static String GET_NOT_PROMOTIONAL_ORDER_BY_AMOUNT = "SELECT * FROM (`store_product` SP1 JOIN `product` USING (id_product)" +
            "JOIN `category` USING (category_number)) LEFT JOIN `store_product` SP2 ON SP1.UPC_prom = SP2.UPC " +
            "WHERE NOT SP1.promotional_product ORDER BY SP1.products_number";
    private static String GET_NOT_PROMOTIONAL_ORDER_BY_NAME = "SELECT * FROM (`store_product` SP1 JOIN `product` USING (id_product)" +
            "JOIN `category` USING (category_number)) LEFT JOIN `store_product` SP2 ON SP1.UPC_prom = SP2.UPC " +
            "WHERE NOT SP1.promotional_product ORDER BY product_name";
    private static String GET_ALL_ORDER_BY_NAME = "SELECT * FROM (`store_product` SP1 JOIN `product` USING (id_product) " +
            "JOIN `category` USING (category_number)) LEFT JOIN `store_product` SP2 ON SP1.UPC_prom = SP2.UPC " +
            "ORDER BY product_name";

    private static String SP1 = "SP1.";
    private static String SP2 = "SP2.";
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
            query.setString(2, product.getProm().getUpc());
            query.setLong(3, product.getProduct().getId());
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
            query.setString(1, product.getProm().getUpc());
            query.setLong(2, product.getProduct().getId());
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

    public List<StoreProduct> getByUPC(String upc) {
        List<StoreProduct> storeProducts = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_UPC)) {
            query.setString(1, upc);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                storeProducts.add(extractStoreProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return storeProducts;
    }

    @Override
    public List<StoreProduct> getPromotionalOrderByAmount() {
        List<StoreProduct> products = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_PROMOTIONAL_ORDER_BY_AMOUNT)) {
            while (resultSet.next()) {
                products.add(extractStoreProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return products;
    }

    @Override
    public List<StoreProduct> getPromotionalOrderByName() {
        List<StoreProduct> products = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_PROMOTIONAL_ORDER_BY_NAME)) {
            while (resultSet.next()) {
                products.add(extractStoreProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return products;
    }

    @Override
    public List<StoreProduct> getNotPromotionalOrderByAmount() {
        List<StoreProduct> products = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_NOT_PROMOTIONAL_ORDER_BY_AMOUNT)) {
            while (resultSet.next()) {
                products.add(extractStoreProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return products;
    }

    @Override
    public List<StoreProduct> getNotPromotionalOrderByName() {
        List<StoreProduct> products = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_NOT_PROMOTIONAL_ORDER_BY_NAME)) {
            while (resultSet.next()) {
                products.add(extractStoreProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return products;
    }

    @Override
    public List<StoreProduct> getAllOrderByName() {
        List<StoreProduct> products = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL_ORDER_BY_NAME)) {
            while (resultSet.next()) {
                products.add(extractStoreProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return products;
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
        return new StoreProduct.Builder()
                .setUPC(resultSet.getString(SP1+UPC))
                .setPrice(resultSet.getBigDecimal(SP1+PRICE))
                .setAmount(resultSet.getLong(SP1+PRODUCTS_NUMBER))
                .setPromotional(resultSet.getBoolean(SP1+PROMOTIONAL_PRODUCT))
                .setProduct(JdbcProductDao.extractProductFromResultSet(resultSet))
                .setProm(extractPromotionalProductFromResultSet(resultSet))
                .build();
    }

    private static StoreProduct extractPromotionalProductFromResultSet(ResultSet resultSet) throws SQLException {
        return new StoreProduct.Builder()
                .setUPC(resultSet.getString(SP2+UPC))
                .setPrice(resultSet.getBigDecimal(SP2+PRICE))
                .setAmount(resultSet.getLong(SP2+PRODUCTS_NUMBER))
                .setPromotional(resultSet.getBoolean(SP2+PROMOTIONAL_PRODUCT))
                .setProduct(JdbcProductDao.extractProductFromResultSet(resultSet))
                .setProm(null)
                .build();
    }
}
