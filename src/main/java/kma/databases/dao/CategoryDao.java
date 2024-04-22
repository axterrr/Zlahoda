package kma.databases.dao;

import kma.databases.entities.Category;

import java.util.List;

public interface CategoryDao extends GenericDao<Category, Long>, AutoCloseable {



    void close();
}
