package kma.databases.dao;

import kma.databases.entities.Check;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CheckDao extends GenericDao<Check, String>, AutoCloseable {

    List<Check> getByDate(LocalDate fromDate, LocalDate toDate);
    List<Check> getByCashierAndDate(String cashierId, LocalDate fromDate, LocalDate toDate);
    BigDecimal getTotalSumByDate(LocalDate fromDate, LocalDate toDate);
    BigDecimal getTotalSumByCashierAndDate(String cashierId, LocalDate fromDate, LocalDate toDate);
    Long getTotalProductAmountByDate(Long productId, LocalDate fromDate, LocalDate toDate);
    List<Check> getAllByCashier(String cashierId);
    List<Check> getByNumber(String checkNumber);
    List<Check> getPromotionalCheck();
    void close();
}
