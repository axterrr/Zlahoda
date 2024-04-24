package kma.databases.dao.jdbc;

import kma.databases.dao.ProductDao;
import kma.databases.entities.Product;
import kma.databases.exceptions.ServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcProductDao implements ProductDao {

    private static String GET_ALL = "SELECT * FROM `product` INNER JOIN `category` " +
            "ON product.category_number = category.category_number ORDER BY id_product";
    private static String GET_BY_ID = "SELECT * FROM `product` INNER JOIN `category` " +
            "ON product.category_number = category.category_number WHERE id_product=?";
    private static String CREATE = "INSERT INTO `product` (category_number, product_name, characteristics) VALUES (?, ?, ?)";
    private static String UPDATE = "UPDATE `product` SET category_number=?, product_name=?, characteristics=? WHERE id_product=?";
    private static String DELETE = "DELETE FROM `product` WHERE id_product=?";

    private static String ID = "id_product";
    private static String CATEGORY_NUMBER = "category_number";
    private static String NAME = "product_name";
    private static String CHARACTERISTICS = "characteristics";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcProductDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcProductDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return products;
    }

    @Override
    public Optional<Product> getById(Long id) {
        Optional<Product> product = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                product = Optional.of(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return product;
    }

    @Override
    public void create(Product product) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setLong(1, product.getCategory().getNumber());
            query.setString(2, product.getName());
            query.setString(3, product.getCharacteristics());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                product.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Product product) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setLong(1, product.getCategory().getNumber());
            query.setString(2, product.getName());
            query.setString(3, product.getCharacteristics());
            query.setLong(4, product.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setLong(1, id);
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

    protected static Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        return new Product.Builder()
                .setId(resultSet.getLong(ID))
                .setName(resultSet.getString(NAME))
                .setCharacteristics(resultSet.getString(CHARACTERISTICS))
                .setCategory(JdbcCategoryDao.extractCategoryFromResultSet(resultSet))
                .build();
    }

}
