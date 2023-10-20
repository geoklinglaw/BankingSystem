package util.exceptions;

public class FailedToChangePin extends Exception {
    public FailedToChangePin() {
    }

    public FailedToChangePin(String msg) {
        super(msg);
    }

    public FailedToChangePin(String message, Throwable cause) {
        super(message, cause);
    }
}
