package kma.databases.dao;

public interface DaoConnection extends AutoCloseable {

    void begin();

    void commit();

    void rollback();

    void close();
}
