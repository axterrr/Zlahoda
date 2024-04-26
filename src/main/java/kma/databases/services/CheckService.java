package kma.databases.services;

import kma.databases.converters.CheckDtoCheckConverter;
import kma.databases.dao.CheckDao;
import kma.databases.dao.DaoFactory;
import kma.databases.dto.CheckDto;
import kma.databases.entities.Check;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CheckService {

    private final DaoFactory daoFactory;

    CheckService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final CheckService INSTANCE = new CheckService(DaoFactory.getDaoFactory());
    }

    public static CheckService getInstance() {
        return CheckService.Holder.INSTANCE;
    }

    public List<Check> getAllChecks() {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getAll();
        }
    }

    public Optional<Check> getCheckById(String checkNumber) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getById(checkNumber);
        }
    }

    public void createCheck(CheckDto checkDto) {
        Check check = CheckDtoCheckConverter.toCheck(checkDto);
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.create(check);
        }
    }

    public void updateCheck(CheckDto checkDto) {
        Check check = CheckDtoCheckConverter.toCheck(checkDto);
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.update(check);
        }
    }

    public void deleteCheck(String checkNumber) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.delete(checkNumber);
        }
    }

    public List<Check> getChecksByDate(LocalDate dateFrom, LocalDate dateTo) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getByDate(dateFrom, dateTo);
        }
    }

    public List<Check> getChecksByCashierAndDate(String cashier, LocalDate dateFrom, LocalDate dateTo) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getByCashierAndDate(cashier, dateFrom, dateTo);
        }
    }

    public BigDecimal getTotalSumByDate(LocalDate dateFrom, LocalDate dateTo) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getTotalSumByDate(dateFrom, dateTo);
        }
    }

    public BigDecimal getTotalSumByCashierAndDate(String cashier, LocalDate dateFrom, LocalDate dateTo) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getTotalSumByCashierAndDate(cashier, dateFrom, dateTo);
        }
    }

    public Long getTotalProductAmountByDate(Long productId, LocalDate dateFrom, LocalDate dateTo) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getTotalProductAmountByDate(productId, dateFrom, dateTo);
        }
    }

    public List<Check> getAllChecksByCashier(String cashierId) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getAllByCashier(cashierId);
        }
    }

    public List<Check> getChecksByNumber(String checkId) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            return checkDao.getByNumber(checkId);
        }
    }
}
