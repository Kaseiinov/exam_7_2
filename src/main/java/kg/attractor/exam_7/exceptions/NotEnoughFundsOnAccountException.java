package kg.attractor.exam_7.exceptions;

public class NotEnoughFundsOnAccountException extends Exception {
    public NotEnoughFundsOnAccountException() {
        super("Not enough funds on account");
    }
}
