package pl.programistazacny.javadeveloperexercise.adapter.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import pl.programistazacny.javadeveloperexercise.adapter.csv.mapper.PaymentCsvMapper;
import pl.programistazacny.javadeveloperexercise.adapter.csv.model.PaymentCsv;
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto;
import pl.programistazacny.javadeveloperexercise.domain.model.Payment;
import pl.programistazacny.javadeveloperexercise.domain.port.PaymentRepository;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class PaymentCsvRepository implements PaymentRepository {

    private final String csvFilename;

    @Override
    public Optional<Payment> findById(UUID id) {
        return findByIdRaw(id).map(PaymentCsvMapper.INSTANCE::csvToDomain);
    }

    private Optional<PaymentCsv> findByIdRaw(UUID id) {
        return findAllRaw().stream()
                .filter(payment -> payment.getId().equals(id))
                .reduce((p1, p2) -> {
                    throw new CsvException("Multiple elements: " + p1 + ", " + p2);
                });
    }

    @Override
    public List<Payment> findAll() {
        return findAllRaw().stream()
                .map(PaymentCsvMapper.INSTANCE::csvToDomain)
                .toList();
    }

    private List<PaymentCsv> findAllRaw() {
        List<PaymentCsv> payments;
        try {
            payments = new CsvToBeanBuilder<PaymentCsv>(new FileReader(csvFilename))
                    .withType(PaymentCsv.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            throw new CsvException("Problem with Payments csv file!", e);
        }
        return payments;
    }

    @Override
    public Payment create(PaymentDto paymentDto) {
        PaymentCsv paymentCsv = PaymentCsvMapper.INSTANCE.dtoToCsv(paymentDto);
        paymentCsv.setId(UUID.randomUUID());

        write(List.of(paymentCsv), true);

        return PaymentCsvMapper.INSTANCE.csvToDomain(paymentCsv);
    }

    private void write(List<PaymentCsv> paymentsCsv, boolean append) {
        try (Writer writer = new FileWriter(csvFilename, append)) {
            StatefulBeanToCsv<PaymentCsv> beanToCsv = new StatefulBeanToCsvBuilder<PaymentCsv>(writer).build();
            beanToCsv.write(paymentsCsv);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new CsvException("Problem with writing to csv file!", e);
        }
    }

    @Override
    public Optional<Payment> update(UUID id, PaymentDto paymentDto) {
        List<PaymentCsv> paymentsCsv = findAllRaw();
        Optional<PaymentCsv> paymentsCsvToUpdate = paymentsCsv.stream()
                .filter(payment -> payment.getId().equals(id))
                .reduce((p1, p2) -> {
                    throw new IllegalStateException("Multiple elements: " + p1 + ", " + p2);
                });
        return paymentsCsvToUpdate.map(paymentCsv -> {
            paymentCsv.updateFrom(paymentDto);
            write(paymentsCsv, false);
            return PaymentCsvMapper.INSTANCE.csvToDomain(paymentCsv);
        });
    }

    @Override
    public boolean delete(UUID id) {
        List<PaymentCsv> paymentsCsv = findAllRaw();
        boolean removed = paymentsCsv.removeIf(paymentCsv -> paymentCsv.getId().equals(id));
        if (removed) {
            write(paymentsCsv, false);
        }
        return removed;
    }
}
