package kma.databases.dao;

import kma.databases.entities.CustomerCard;

import java.util.List;

public interface CustomerCardDao extends GenericDao<CustomerCard, String>, AutoCloseable {

    List<CustomerCard> getByPercent(Long from, Long to);
    List<CustomerCard> getBySurname(String surname);
    void close();
}
