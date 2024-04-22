package kma.databases.dao;

import kma.databases.entities.Sale;
import org.apache.commons.lang3.tuple.Pair;

public interface SaleDao extends GenericDao<Sale, Pair<String, String>>, AutoCloseable {



    void close();
}
