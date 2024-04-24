package kma.databases.dao.jdbc;

import kma.databases.dao.CheckDao;
import kma.databases.entities.Check;
import kma.databases.exceptions.ServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCheckDao implements CheckDao {

    private static String GET_ALL = "SELECT * FROM `check` JOIN `employee` USING (id_employee) JOIN " +
            "`customer_card` USING (card_number) ORDER BY print_date";
    private static String GET_BY_ID = "SELECT * FROM `check` JOIN `employee` USING (id_employee) JOIN " +
            "`customer_card` USING (card_number) WHERE check_number=?";
    private static String CREATE = "INSERT INTO `check` " +
            "(check_number, id_employee, card_number, print_date, sum_total, vat) VALUES (?, ?, ?, ?, ?, ?)";
    private static String UPDATE = "UPDATE `check` SET " +
            "id_employee=?, card_number=?, print_date=?, sum_total=?, vat=? WHERE check_number=?";
    private static String DELETE = "DELETE FROM `check` WHERE check_number=?";

    private static String NUMBER = "check_number";
    private static String EMPLOYEE_ID = "id_employee";
    private static String CUSTOMER_CARD_NUMBER = "card_number";
    private static String PRINT_DATE = "print_date";
    private static String SUM_TOTAL = "sum_total";
    private static String VAT = "vat";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcCheckDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcCheckDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    @Override
    public List<Check> getAll() {
        List<Check> checks = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                checks.add(extractCheckFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return checks;
    }

    @Override
    public Optional<Check> getById(String id) {
        Optional<Check> check = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                check = Optional.of(extractCheckFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return check;
    }

    @Override
    public void create(Check check) {
        try (PreparedStatement query = connection.prepareStatement(CREATE)) {
            query.setString(1, check.getNumber());
            query.setString(2, check.getEmployee().getId());
            query.setString(3, check.getCustomerCard().getNumber());
            query.setTimestamp(4, Timestamp.valueOf(check.getPrintDate()));
            query.setBigDecimal(5, check.getTotalSum());
            query.setBigDecimal(6, check.getVat());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Check check) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, check.getEmployee().getId());
            query.setString(2, check.getCustomerCard().getNumber());
            query.setTimestamp(3, Timestamp.valueOf(check.getPrintDate()));
            query.setBigDecimal(4, check.getTotalSum());
            query.setBigDecimal(5, check.getVat());
            query.setString(6, check.getNumber());
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

    protected static Check extractCheckFromResultSet(ResultSet resultSet) throws SQLException {
        return new Check.Builder().setNumber(resultSet.getString(NUMBER)).setTotalSum(resultSet.getBigDecimal(SUM_TOTAL))
                .setVat(resultSet.getBigDecimal(VAT)).setPrintDate(resultSet.getTimestamp(PRINT_DATE).toLocalDateTime())
                .setEmployee(JdbcEmployeeDao.extractEmployeeFromResultSet(resultSet))
                .setCustomerCard(JdbcCustomerCardDao.extractCustomerCardFromResultSet(resultSet))
                .build();
    }

}
