package kma.databases.dao.jdbc;

import kma.databases.dao.CustomerCardDao;
import kma.databases.entities.CustomerCard;
import kma.databases.exceptions.ServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCustomerCardDao implements CustomerCardDao {

    private static String GET_ALL = "SELECT * FROM `customer_card` ORDER BY cust_surname";
    private static String GET_BY_ID = "SELECT * FROM `customer_card` WHERE card_number=?";
    private static String CREATE = "INSERT INTO `customer_card` " +
            "(card_number, cust_surname, cust_name, cust_patronymic, phone_number, city, street, zip_code, percent) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static String UPDATE = "UPDATE `customer_card` SET cust_surname=?, cust_name=?, cust_patronymic=?, " +
            "phone_number=?, city=?, street=?, zip_code=?, percent=? WHERE card_number=?";
    private static String DELETE = "DELETE FROM `customer_card` WHERE card_number=?";

    private static String NUMBER = "card_number";
    private static String SURNAME = "cust_surname";
    private static String NAME = "cust_name";
    private static String PARTONYMIC = "cust_patronymic";
    private static String PHONE_NUMBER = "customer_card.phone_number";
    private static String CITY = "customer_card.city";
    private static String STREET = "customer_card.street";
    private static String ZIP_CODE = "customer_card.zip_code";
    private static String PERCENT = "percent";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcCustomerCardDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcCustomerCardDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    @Override
    public List<CustomerCard> getAll() {
        List<CustomerCard> cards = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                cards.add(extractCustomerCardFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return cards;
    }

    @Override
    public Optional<CustomerCard> getById(String id) {
        Optional<CustomerCard> card = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                card = Optional.of(extractCustomerCardFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return card;
    }

    @Override
    public void create(CustomerCard card) {
        try (PreparedStatement query = connection.prepareStatement(CREATE)) {
            query.setString(1, card.getNumber());
            query.setString(2, card.getSurname());
            query.setString(3, card.getName());
            query.setString(4, card.getPatronymic());
            query.setString(5, card.getPhoneNumber());
            query.setString(6, card.getCity());
            query.setString(7, card.getStreet());
            query.setString(8, card.getZipCode());
            query.setLong(9, card.getPercent());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void update(CustomerCard card) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, card.getSurname());
            query.setString(2, card.getName());
            query.setString(3, card.getPatronymic());
            query.setString(4, card.getPhoneNumber());
            query.setString(5, card.getCity());
            query.setString(6, card.getStreet());
            query.setString(7, card.getZipCode());
            query.setLong(8, card.getPercent());
            query.setString(9, card.getNumber());
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

    protected static CustomerCard extractCustomerCardFromResultSet(ResultSet resultSet) throws SQLException {
        return new CustomerCard.Builder().setNumber(resultSet.getString(NUMBER)).setSurname(resultSet.getString(SURNAME))
                .setName(resultSet.getString(NAME)).setPatronymic(resultSet.getString(PARTONYMIC))
                .setPhoneNumber(resultSet.getString(PHONE_NUMBER)).setCity(resultSet.getString(CITY))
                .setStreet(resultSet.getString(STREET)).setZipCode(resultSet.getString(ZIP_CODE))
                .setPercent(resultSet.getLong(PERCENT)).build();
    }
}
