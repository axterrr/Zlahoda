package kma.databases.dao.jdbc;

import kma.databases.dao.CheckDao;
import kma.databases.entities.Check;
import kma.databases.entities.Sale;
import kma.databases.exceptions.ServerException;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCheckDao implements CheckDao {

    private static String GET_ALL =
            "SELECT * " +
            "FROM `check` " +
            "JOIN `employee` USING (id_employee) " +
            "LEFT JOIN `customer_card` USING (card_number) " +
            "JOIN `sale` USING (check_number) " +
            "JOIN `store_product` USING (UPC) " +
            "JOIN `product` USING (id_product) " +
            "JOIN `category` USING (category_number) " +
            "ORDER BY check_number";
    private static String GET_BY_ID = "SELECT * " +
            "FROM `check` " +
            "JOIN `employee` USING (id_employee) " +
            "LEFT JOIN `customer_card` USING (card_number) " +
            "JOIN `sale` USING (check_number) " +
            "JOIN `store_product` USING (UPC) " +
            "JOIN `product` USING (id_product) " +
            "JOIN `category` USING (category_number) " +
            "WHERE check_number=?";
    private static String CREATE = "INSERT INTO `check` " +
            "(check_number, id_employee, card_number, print_date, sum_total, vat) VALUES (?, ?, ?, ?, ?, ?)";
    private static String UPDATE = "UPDATE `check` SET " +
            "id_employee=?, card_number=?, print_date=?, sum_total=?, vat=? WHERE check_number=?";
    private static String DELETE = "DELETE FROM `check` WHERE check_number=?";
    private static String GET_BY_DATE =
            "SELECT * " +
            "FROM `check` " +
            "JOIN `employee` USING (id_employee) " +
            "LEFT JOIN `customer_card` USING (card_number) " +
            "JOIN `sale` USING (check_number) " +
            "JOIN `store_product` USING (UPC) " +
            "JOIN `product` USING (id_product) " +
            "JOIN `category` USING (category_number) " +
            "WHERE print_date BETWEEN ? AND ?" +
            "ORDER BY check_number";
    private static String GET_BY_CASHIER_AND_DATE =
            "SELECT * " +
            "FROM `check` " +
            "JOIN `employee` USING (id_employee) " +
            "LEFT JOIN `customer_card` USING (card_number) " +
            "JOIN `sale` USING (check_number) " +
            "JOIN `store_product` USING (UPC) " +
            "JOIN `product` USING (id_product) " +
            "JOIN `category` USING (category_number) " +
            "WHERE id_employee=?" +
            "AND print_date BETWEEN ? AND ?" +
            "ORDER BY check_number";
    private static String GET_TOTAL_SUM_BY_DATE =
            "SELECT SUM(sum_total) AS result " +
            "FROM `check` " +
            "WHERE print_date BETWEEN ? AND ?";
    private static String GET_TOTAL_SUM_BY_CASHIER_AND_DATE =
            "SELECT SUM(sum_total) AS result " +
            "FROM `check` " +
            "JOIN `employee` USING (id_employee) " +
            "WHERE id_employee=? " +
            "AND print_date BETWEEN ? AND ?";
    private static String GET_TOTAL_PRODUCT_AMOUNT_BY_DATE =
            "SELECT SUM(sale.product_number) AS result " +
            "FROM `check` " +
            "JOIN `sale` USING (check_number) " +
            "JOIN `store_product` USING (UPC) " +
            "JOIN `product` USING (id_product) " +
            "WHERE id_product=? " +
            "AND print_date BETWEEN ? AND ?";
    private static String GET_BY_CASHIER =
            "SELECT * " +
            "FROM `check` " +
            "JOIN `employee` USING (id_employee) " +
            "LEFT JOIN `customer_card` USING (card_number) " +
            "JOIN `sale` USING (check_number) " +
            "JOIN `store_product` USING (UPC) " +
            "JOIN `product` USING (id_product) " +
            "JOIN `category` USING (category_number) " +
            "WHERE id_employee=? " +
            "ORDER BY check_number";
    private static String GET_BY_NUMBER = "SELECT * " +
            "FROM `check` " +
            "JOIN `employee` USING (id_employee) " +
            "LEFT JOIN `customer_card` USING (card_number) " +
            "JOIN `sale` USING (check_number) " +
            "JOIN `store_product` USING (UPC) " +
            "JOIN `product` USING (id_product) " +
            "JOIN `category` USING (category_number) " +
            "WHERE LOWER(check_number) LIKE CONCAT('%', LOWER(?), '%') " +
            "ORDER BY check_number";

    private static String NUMBER = "check_number";
    private static String EMPLOYEE_ID = "id_employee";
    private static String CUSTOMER_CARD_NUMBER = "card_number";
    private static String PRINT_DATE = "print_date";
    private static String SUM_TOTAL = "sum_total";
    private static String VAT = "vat";
    private static String PRICE = "sale.selling_price";
    private static String AMOUNT = "sale.product_number";

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
        try (Statement query = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                checks.add(extractCheckWithSalesFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return checks;
    }

    @Override
    public Optional<Check> getById(String id) {
        Optional<Check> check = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                check = Optional.of(extractCheckWithSalesFromResultSet(resultSet));
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
    public List<Check> getByDate(LocalDate fromDate, LocalDate toDate) {
        List<Check> checks = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_DATE,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            query.setDate(1, Date.valueOf(fromDate));
            query.setDate(2, Date.valueOf(toDate));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                checks.add(extractCheckWithSalesFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return checks;
    }

    @Override
    public List<Check> getByCashierAndDate(String cashierId, LocalDate fromDate, LocalDate toDate) {
        List<Check> checks = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_CASHIER_AND_DATE,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            query.setString(1, cashierId);
            query.setDate(2, Date.valueOf(fromDate));
            query.setDate(3, Date.valueOf(toDate));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                checks.add(extractCheckWithSalesFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return checks;
    }

    @Override
    public BigDecimal getTotalSumByDate(LocalDate fromDate, LocalDate toDate) {
        try (PreparedStatement query = connection.prepareStatement(GET_TOTAL_SUM_BY_DATE)) {
            query.setDate(1, Date.valueOf(fromDate));
            query.setDate(2, Date.valueOf(toDate));
            ResultSet resultSet = query.executeQuery();
            resultSet.next();
            return resultSet.getBigDecimal("result");
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public BigDecimal getTotalSumByCashierAndDate(String cashierId, LocalDate fromDate, LocalDate toDate) {
        try (PreparedStatement query = connection.prepareStatement(GET_TOTAL_SUM_BY_CASHIER_AND_DATE)) {
            query.setString(1, cashierId);
            query.setDate(2, Date.valueOf(fromDate));
            query.setDate(3, Date.valueOf(toDate));
            ResultSet resultSet = query.executeQuery();
            resultSet.next();
            return resultSet.getBigDecimal("result");
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public Long getTotalProductAmountByDate(Long productId, LocalDate fromDate, LocalDate toDate) {
        try (PreparedStatement query = connection.prepareStatement(GET_TOTAL_PRODUCT_AMOUNT_BY_DATE)) {
            query.setLong(1, productId);
            query.setDate(2, Date.valueOf(fromDate));
            query.setDate(3, Date.valueOf(toDate));
            ResultSet resultSet = query.executeQuery();
            resultSet.next();
            return resultSet.getLong("result");
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public List<Check> getAllByCashier(String cashierId) {
        List<Check> checks = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_CASHIER,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            query.setString(1, cashierId);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                checks.add(extractCheckWithSalesFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return checks;
    }

    @Override
    public List<Check> getByNumber(String checkNumber) {
        List<Check> checks = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_NUMBER,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            query.setString(1, checkNumber);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                checks.add(extractCheckWithSalesFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return checks;
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

    protected static Check extractCheckWithSalesFromResultSet(ResultSet resultSet) throws SQLException {
        Check check = extractCheckFromResultSet(resultSet);
        check.addSale(extractSaleFromResultSet(resultSet));
        while (resultSet.next() && check.getNumber().equals(resultSet.getString(NUMBER))) {
            check.addSale(extractSaleFromResultSet(resultSet));
        }
        resultSet.previous();
        return check;
    }

    protected static Sale extractSaleFromResultSet(ResultSet resultSet) throws SQLException {
        return new Sale.Builder()
                .setPrice(resultSet.getBigDecimal(PRICE))
                .setProductsNumber(resultSet.getLong(AMOUNT))
                .setStoreProduct(JdbcStoreProductDao.extractStoreProductFromResultSet(resultSet))
                .build();
    }

    protected static Check extractCheckFromResultSet(ResultSet resultSet) throws SQLException {
        return new Check.Builder()
                .setNumber(resultSet.getString(NUMBER))
                .setTotalSum(resultSet.getBigDecimal(SUM_TOTAL))
                .setVat(resultSet.getBigDecimal(VAT))
                .setPrintDate(resultSet.getTimestamp(PRINT_DATE).toLocalDateTime())
                .setEmployee(JdbcEmployeeDao.extractEmployeeFromResultSet(resultSet))
                .setCustomerCard(JdbcCustomerCardDao.extractCustomerCardFromResultSet(resultSet))
                .build();
    }

}
