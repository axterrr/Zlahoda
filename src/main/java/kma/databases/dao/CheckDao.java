package kma.databases.dao;

import kma.databases.entities.Check;

public interface CheckDao extends GenericDao<Check, String>, AutoCloseable {



    void close();
}
