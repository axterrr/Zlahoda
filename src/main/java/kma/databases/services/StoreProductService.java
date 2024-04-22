package kma.databases.services;

import kma.databases.converters.StoreProductDtoStoreProductConverter;
import kma.databases.dao.StoreProductDao;
import kma.databases.dao.DaoFactory;
import kma.databases.dto.StoreProductDto;
import kma.databases.entities.StoreProduct;

import java.util.List;
import java.util.Optional;

public class StoreProductService {

    private final DaoFactory daoFactory;

    StoreProductService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final StoreProductService INSTANCE = new StoreProductService(DaoFactory.getDaoFactory());
    }

    public static StoreProductService getInstance() {
        return StoreProductService.Holder.INSTANCE;
    }

    public List<StoreProduct> getAllStoreProducts() {
        try (StoreProductDao storeProductDao = daoFactory.createStoreProductDao()) {
            return storeProductDao.getAll();
        }
    }

    public Optional<StoreProduct> getStoreProductById(String UPC) {
        try (StoreProductDao storeProductDao = daoFactory.createStoreProductDao()) {
            return storeProductDao.getById(UPC);
        }
    }

    public void createStoreProduct(StoreProductDto storeProductDto) {
        StoreProduct storeProduct = StoreProductDtoStoreProductConverter.toStoreProduct(storeProductDto);
        try (StoreProductDao storeProductDao = daoFactory.createStoreProductDao()) {
            storeProductDao.create(storeProduct);
        }
    }

    public void updateStoreProduct(StoreProductDto storeProductDto) {
        StoreProduct storeProduct = StoreProductDtoStoreProductConverter.toStoreProduct(storeProductDto);
        try (StoreProductDao storeProductDao = daoFactory.createStoreProductDao()) {
            storeProductDao.update(storeProduct);
        }
    }

    public void deleteStoreProduct(String UPC) {
        try (StoreProductDao storeProductDao = daoFactory.createStoreProductDao()) {
            storeProductDao.delete(UPC);
        }
    }
}
