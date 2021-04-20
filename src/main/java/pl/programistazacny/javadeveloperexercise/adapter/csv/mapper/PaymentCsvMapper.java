package pl.programistazacny.javadeveloperexercise.adapter.csv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.programistazacny.javadeveloperexercise.adapter.csv.model.PaymentCsv;
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto;
import pl.programistazacny.javadeveloperexercise.domain.model.Payment;

@Mapper
public interface PaymentCsvMapper {
    PaymentCsvMapper INSTANCE = Mappers.getMapper(PaymentCsvMapper.class);

    Payment csvToDomain(PaymentCsv paymentCsv);

    @Mapping(target = "id", ignore = true)
    PaymentCsv dtoToCsv(PaymentDto paymentDto);
}
