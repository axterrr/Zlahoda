package kma.databases.converters;

import kma.databases.dto.CheckDto;
import kma.databases.entities.Check;
import kma.databases.entities.CustomerCard;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CheckDtoCheckConverter {

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
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
            builder.setNumber(uuid);
            checkDto.setNumber(uuid);
        }
        if(checkDto.getCustomerCard() == null || checkDto.getCustomerCard().getNumber() == null
            || checkDto.getCustomerCard().getNumber().isEmpty()) {
            builder.setCustomerCard(new CustomerCard.Builder().setNumber(null).build());
        }
        return builder.build();
    }
}
