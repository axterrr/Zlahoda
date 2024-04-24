package kma.databases.dao.jdbc;

import kma.databases.dao.SaleDao;
import kma.databases.entities.Sale;
import kma.databases.exceptions.ServerException;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcSaleDao implements SaleDao {

    private static String GET_ALL = "SELECT * FROM `sale` ORDER BY selling_price";
    private static String GET_BY_ID = "SELECT * FROM `sale` WHERE UPC=? AND check_number=?";
    private static String CREATE = "INSERT INTO `sale` (UPC, check_number, product_number, selling_price) VALUES (?, ?, ?, ?)";
    private static String UPDATE = "UPDATE `sale` SET product_number=?, selling_price=? WHERE UPC=? AND check_number=?";
    private static String DELETE = "DELETE FROM `sale` WHERE UPC=? AND check_number=?";

    private static String STORE_PRODUCT_UPC = "UPC";
    private static String CHECK_NUMBER = "check_number";
    private static String PRODUCTS_NUMBER = "product_number";
    private static String PRICE = "selling_price";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcSaleDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcSaleDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    @Override
    public List<Sale> getAll() {
        List<Sale> sales = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                sales.add(extractSaleFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return sales;
    }

    @Override
    public Optional<Sale> getById(Pair<String, String> product_check) {
        Optional<Sale> sale = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, product_check.getLeft());
            query.setString(2, product_check.getRight());
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                sale = Optional.of(extractSaleFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return sale;
    }

    @Override
    public void create(Sale sale) {
        try (PreparedStatement query = connection.prepareStatement(CREATE)) {
            query.setString(1, sale.getStoreProduct().getUpc());
            query.setString(2, sale.getCheck().getNumber());
            query.setLong(3, sale.getProductsNumber());
            query.setBigDecimal(4, sale.getPrice());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Sale sale) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setLong(1, sale.getProductsNumber());
            query.setBigDecimal(2, sale.getPrice());
            query.setString(3, sale.getStoreProduct().getUpc());
            query.setString(4, sale.getCheck().getNumber());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Pair<String, String> product_check) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, product_check.getLeft());
            query.setString(2, product_check.getRight());
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

    protected static Sale extractSaleFromResultSet(ResultSet resultSet) throws SQLException {
        return new Sale.Builder()
                .setPrice(resultSet.getBigDecimal(PRICE))
                .setProductsNumber(resultSet.getLong(PRODUCTS_NUMBER))
                .setStoreProduct(JdbcStoreProductDao.extractStoreProductFromResultSet(resultSet))
                .setCheck(JdbcCheckDao.extractCheckFromResultSet(resultSet))
                .build();
    }

}
