package kma.databases.dao;

import kma.databases.entities.CustomerCard;

public interface CustomerCardDao extends GenericDao<CustomerCard, String>, AutoCloseable {



    void close();
}
