package kma.databases.converters;

import kma.databases.dto.CheckDto;
import kma.databases.entities.Check;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CheckDtoСheckConverter {

    public static Check toCheck(CheckDto checkDto) {
        Check.Builder builder = new Check.Builder()
                .setNumber(checkDto.getNumber())
                .setEmployee(checkDto.getEmployee())
                .setCustomerCard(checkDto.getCustomerCard())
                .setPrintDate(LocalDateTime.parse(checkDto.getPrintDate()))
                .setTotalSum(new BigDecimal(checkDto.getTotalSum()))
                .setSales(checkDto.getSales())
                .setVat(new BigDecimal(checkDto.getVat()));
        if(checkDto.getNumber() == null) {
            builder.setNumber(UUID.randomUUID().toString());
        }
        if(checkDto.getCustomerCard() == null || checkDto.getCustomerCard().getNumber() == null) {
            builder.setCustomerCard(null);
        }
        return builder.build();
    }
}
