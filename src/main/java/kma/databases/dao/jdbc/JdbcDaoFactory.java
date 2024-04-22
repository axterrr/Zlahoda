package kma.databases.dao.jdbc;

import kma.databases.dao.*;
import kma.databases.exceptions.ServerException;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/zlahoda");
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public CategoryDao createCategoryDao() {
        try {
            return new JdbcCategoryDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public CategoryDao createCategoryDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCategoryDao(sqlConnection);
    }

    @Override
    public CheckDao createCheckDao() {
        try {
            return new JdbcCheckDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public CheckDao createCheckDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCheckDao(sqlConnection);
    }

    @Override
    public CustomerCardDao createCustomerCardDao() {
        try {
            return new JdbcCustomerCardDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public CustomerCardDao createCustomerCardDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCustomerCardDao(sqlConnection);
    }

    @Override
    public EmployeeDao createEmployeeDao() {
        try {
            return new JdbcEmployeeDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public EmployeeDao createEmployeeDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcEmployeeDao(sqlConnection);
    }

    @Override
    public ProductDao createProductDao() {
        try {
            return new JdbcProductDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public ProductDao createProductDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcProductDao(sqlConnection);
    }

    @Override
    public SaleDao createSaleDao() {
        try {
            return new JdbcSaleDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public SaleDao createSaleDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcSaleDao(sqlConnection);
    }

    @Override
    public StoreProductDao createStoreProductDao() {
        try {
            return new JdbcStoreProductDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public StoreProductDao createStoreProductDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcStoreProductDao(sqlConnection);
    }
}
