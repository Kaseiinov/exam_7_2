package kg.attractor.exam_7.exceptions;

import java.util.NoSuchElementException;

public class NotFoundException extends NoSuchElementException {
    public NotFoundException() {
        super("Not found");
    }
}
