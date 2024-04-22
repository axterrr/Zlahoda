package kma.databases.services;

import kma.databases.converters.CustomerCardDtoCustomerCardConverter;
import kma.databases.dao.CustomerCardDao;
import kma.databases.dao.DaoFactory;
import kma.databases.dto.CustomerCardDto;
import kma.databases.entities.CustomerCard;

import java.util.List;
import java.util.Optional;

public class CustomerCardService {

    private final DaoFactory daoFactory;

    CustomerCardService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final CustomerCardService INSTANCE = new CustomerCardService(DaoFactory.getDaoFactory());
    }

    public static CustomerCardService getInstance() {
        return CustomerCardService.Holder.INSTANCE;
    }

    public List<CustomerCard> getAllCustomerCards() {
        try (CustomerCardDao customerCardDao = daoFactory.createCustomerCardDao()) {
            return customerCardDao.getAll();
        }
    }

    public Optional<CustomerCard> getCustomerCardById(String cardNumber) {
        try (CustomerCardDao customerCardDao = daoFactory.createCustomerCardDao()) {
            return customerCardDao.getById(cardNumber);
        }
    }

    public void createCustomerCard(CustomerCardDto customerCardDto) {
        CustomerCard customerCard = CustomerCardDtoCustomerCardConverter.toCustomerCard(customerCardDto);
        try (CustomerCardDao customerCardDao = daoFactory.createCustomerCardDao()) {
            customerCardDao.create(customerCard);
        }
    }

    public void updateCustomerCard(CustomerCardDto customerCardDto) {
        CustomerCard customerCard = CustomerCardDtoCustomerCardConverter.toCustomerCard(customerCardDto);
        try (CustomerCardDao customerCardDao = daoFactory.createCustomerCardDao()) {
            customerCardDao.update(customerCard);
        }
    }

    public void deleteCustomerCard(String cardNumber) {
        try (CustomerCardDao customerCardDao = daoFactory.createCustomerCardDao()) {
            customerCardDao.delete(cardNumber);
        }
    }
}
