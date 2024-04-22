package kma.databases.validators.entities;

import java.util.List;

public interface Validator<T> {
    List<String> validate(T dto);
}
