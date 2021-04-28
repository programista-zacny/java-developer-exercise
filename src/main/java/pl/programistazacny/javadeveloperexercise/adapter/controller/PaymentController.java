package pl.programistazacny.javadeveloperexercise.adapter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.programistazacny.javadeveloperexercise.adapter.controller.mapper.PaymentControllerMapper;
import pl.programistazacny.javadeveloperexercise.adapter.controller.model.PaymentRequest;
import pl.programistazacny.javadeveloperexercise.adapter.controller.model.PaymentResponse;
import pl.programistazacny.javadeveloperexercise.domain.PaymentService;
import pl.programistazacny.javadeveloperexercise.domain.model.Payment;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/payment/{id}")
    ResponseEntity<PaymentResponse> get(@PathVariable UUID id) {
        return ResponseEntity.of(paymentService.get(id).map(PaymentControllerMapper.INSTANCE::domainToResponse));
    }

    @GetMapping("/payments")
    ResponseEntity<List<PaymentResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        paymentService.getAll().stream()
                                .map(PaymentControllerMapper.INSTANCE::domainToResponse)
                                .collect(Collectors.toUnmodifiableList())
                );
    }

    @PostMapping("/payment")
    ResponseEntity<PaymentResponse> create(@RequestBody @Valid PaymentRequest paymentRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        PaymentControllerMapper.INSTANCE.domainToResponse(
                                paymentService.add(PaymentControllerMapper.INSTANCE.requestToDto(paymentRequest))
                        )
                );
    }

    @PutMapping("/payment/{id}")
    ResponseEntity<PaymentResponse> edit(@PathVariable UUID id, @RequestBody @Valid PaymentRequest paymentRequest) {
        Optional<Payment> paymentEdited = paymentService.edit(id, PaymentControllerMapper.INSTANCE.requestToDto(paymentRequest));
        return ResponseEntity
                .status(paymentEdited.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(paymentEdited.map(PaymentControllerMapper.INSTANCE::domainToResponse).orElse(null));
    }

    @DeleteMapping("/payment/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id) {
        return ResponseEntity
                .status(paymentService.delete(id) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND)
                .build();
    }
}
