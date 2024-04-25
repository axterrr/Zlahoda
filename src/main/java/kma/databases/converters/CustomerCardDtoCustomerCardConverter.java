package kma.databases.converters;

import kma.databases.dto.CustomerCardDto;
import kma.databases.entities.CustomerCard;

import java.util.UUID;

public class CustomerCardDtoCustomerCardConverter {

    public static CustomerCard toCustomerCard(CustomerCardDto customerCardDto) {
        CustomerCard.Builder builder = new CustomerCard.Builder()
                .setNumber(customerCardDto.getNumber())
                .setSurname(customerCardDto.getSurname())
                .setName(customerCardDto.getName())
                .setPatronymic(customerCardDto.getPatronymic())
                .setPhoneNumber(customerCardDto.getPhoneNumber())
                .setCity(customerCardDto.getCity())
                .setStreet(customerCardDto.getStreet())
                .setZipCode(customerCardDto.getZipCode())
                .setPercent(Long.parseLong(customerCardDto.getPercent()));
        if(customerCardDto.getNumber()==null) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 13);
            builder.setNumber(uuid);
            customerCardDto.setNumber(uuid);
        }
        if(customerCardDto.getPatronymic()==null || customerCardDto.getPatronymic().isEmpty()) {
            builder.setPatronymic(null);
        }
        if(customerCardDto.getCity()==null || customerCardDto.getCity().isEmpty()) {
            builder.setCity(null);
        }
        if(customerCardDto.getStreet()==null || customerCardDto.getStreet().isEmpty()) {
            builder.setStreet(null);
        }
        if(customerCardDto.getPatronymic()==null || customerCardDto.getPatronymic().isEmpty()) {
            builder.setPatronymic(null);
        } if(customerCardDto.getZipCode()==null || customerCardDto.getZipCode().isEmpty()) {
            builder.setZipCode(null);
        }
        return builder.build();
    }
}
