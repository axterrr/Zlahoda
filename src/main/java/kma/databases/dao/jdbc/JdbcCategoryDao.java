package kma.databases.dao.jdbc;

import kma.databases.dao.CategoryDao;
import kma.databases.entities.Category;
import kma.databases.exceptions.ServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCategoryDao implements CategoryDao {

    private static String GET_ALL = "SELECT * FROM `category` ORDER BY category_name";
    private static String GET_BY_ID = "SELECT * FROM `category` WHERE category_number=?";
    private static String CREATE = "INSERT INTO `category` (category_name) VALUES (?)";
    private static String UPDATE = "UPDATE `category` SET category_name=? WHERE category_number=?";
    private static String DELETE = "DELETE FROM `category` WHERE category_number=?";

    private static String NUMBER = "category_number";
    private static String NAME = "category_name";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcCategoryDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcCategoryDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                categories.add(extractCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return categories;
    }

    @Override
    public Optional<Category> getById(Long id) {
        Optional<Category> category = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                category = Optional.of(extractCategoryFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return category;
    }

    @Override
    public void create(Category category) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, category.getName());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                category.setNumber(keys.getLong(1));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Category category) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, category.getName());
            query.setLong(2, category.getNumber());
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

    protected static Category extractCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        return new Category.Builder().setNumber(resultSet.getLong(NUMBER)).setName(resultSet.getString(NAME)).build();
    }

}
