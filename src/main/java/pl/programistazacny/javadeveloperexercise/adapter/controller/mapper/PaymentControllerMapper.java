package pl.programistazacny.javadeveloperexercise.adapter.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.programistazacny.javadeveloperexercise.adapter.controller.model.PaymentRequest;
import pl.programistazacny.javadeveloperexercise.adapter.controller.model.PaymentResponse;
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto;
import pl.programistazacny.javadeveloperexercise.domain.model.Payment;

@Mapper
public interface PaymentControllerMapper {
    PaymentControllerMapper INSTANCE = Mappers.getMapper(PaymentControllerMapper.class);

    PaymentDto requestToDto(PaymentRequest paymentRequest);

    PaymentResponse domainToResponse(Payment payment);
}
