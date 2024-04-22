package kma.databases.services;

import kma.databases.converters.SaleDtoSaleConverter;
import kma.databases.dao.DaoFactory;
import kma.databases.dao.SaleDao;
import kma.databases.dto.SaleDto;
import kma.databases.entities.Sale;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

public class SaleService {

    private final DaoFactory daoFactory;

    SaleService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final SaleService INSTANCE = new SaleService(DaoFactory.getDaoFactory());
    }

    public static SaleService getInstance() {
        return SaleService.Holder.INSTANCE;
    }

    public List<Sale> getAllSales() {
        try (SaleDao saleDao = daoFactory.createSaleDao()) {
            return saleDao.getAll();
        }
    }

    public Optional<Sale> getSaleById(Pair<String, String> product_check) {
        try (SaleDao saleDao = daoFactory.createSaleDao()) {
            return saleDao.getById(product_check);
        }
    }

    public void createSale(SaleDto saleDto) {
        Sale sale = SaleDtoSaleConverter.toSale(saleDto);
        try (SaleDao saleDao = daoFactory.createSaleDao()) {
            saleDao.create(sale);
        }
    }

    public void updateSale(SaleDto saleDto) {
        Sale sale = SaleDtoSaleConverter.toSale(saleDto);
        try (SaleDao saleDao = daoFactory.createSaleDao()) {
            saleDao.update(sale);
        }
    }

    public void deleteSale(Pair<String, String> product_check) {
        try (SaleDao saleDao = daoFactory.createSaleDao()) {
            saleDao.delete(product_check);
        }
    }
}
