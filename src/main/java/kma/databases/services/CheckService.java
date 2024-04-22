package kma.databases.services;

import kma.databases.converters.CheckDtoСheckConverter;
import kma.databases.dao.CheckDao;
import kma.databases.dao.DaoFactory;
import kma.databases.dto.CheckDto;
import kma.databases.entities.Check;

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
        Check check = CheckDtoСheckConverter.toCheck(checkDto);
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.create(check);
        }
    }

    public void updateCheck(CheckDto checkDto) {
        Check check = CheckDtoСheckConverter.toCheck(checkDto);
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.update(check);
        }
    }

    public void deleteCheck(String checkNumber) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.delete(checkNumber);
        }
    }
}
