package pl.programistazacny.javadeveloperexercise.adapter.csv;

class CsvException extends RuntimeException {

    CsvException(String message) {
        super(message);
    }

    CsvException(String message, Throwable cause) {
        super(message, cause);
    }
}
