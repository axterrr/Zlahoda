package kma.databases.dao;

import kma.databases.exceptions.ServerException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DaoFactory {

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";

    private static DaoFactory daoFactory;

    public abstract DaoConnection getConnection();

    public abstract CategoryDao createCategoryDao();
    public abstract CategoryDao createCategoryDao(DaoConnection connection);

    public abstract CheckDao createCheckDao();
    public abstract CheckDao createCheckDao(DaoConnection connection);

    public abstract CustomerCardDao createCustomerCardDao();
    public abstract CustomerCardDao createCustomerCardDao(DaoConnection connection);

    public abstract EmployeeDao createEmployeeDao();
    public abstract EmployeeDao createEmployeeDao(DaoConnection connection);

    public abstract ProductDao createProductDao();
    public abstract ProductDao createProductDao(DaoConnection connection);

    public abstract SaleDao createSaleDao();
    public abstract SaleDao createSaleDao(DaoConnection connection);

    public abstract StoreProductDao createStoreProductDao();
    public abstract StoreProductDao createStoreProductDao(DaoConnection connection);

    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
                daoFactory = (DaoFactory) Class.forName(factoryClass).newInstance();
            } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                throw new ServerException(e);
            }
        }
        return daoFactory;
    }
}
