package kg.attractor.exam_7.exceptions;

import java.util.NoSuchElementException;

public class CurrencyNotFoundException extends NoSuchElementException {
    public CurrencyNotFoundException() {
        super("Currency not found");
    }
}
