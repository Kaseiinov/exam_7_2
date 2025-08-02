package kg.attractor.exam_7.exceptions;

public class InvalidCurrencyException extends Exception {
    public InvalidCurrencyException() {
        super("Currencies do not match");
    }
}
