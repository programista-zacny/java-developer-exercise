package pl.programistazacny.javadeveloperexercise.adapter.h2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.programistazacny.javadeveloperexercise.adapter.h2.model.PaymentH2;
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto;
import pl.programistazacny.javadeveloperexercise.domain.model.Payment;

@Mapper
public interface PaymentH2Mapper {
    PaymentH2Mapper INSTANCE = Mappers.getMapper(PaymentH2Mapper.class);

    Payment h2ToDomain(PaymentH2 paymentCsv);

    @Mapping(target = "id", ignore = true)
    PaymentH2 dtoToH2(PaymentDto paymentDto);
}
