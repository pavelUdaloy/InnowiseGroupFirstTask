package by.exception;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ConnectionWithDBLostException extends Exception {
    @Getter
    private static final String message = "Connection with db lost (wrong user, password or url)";

    private final String errorType;

    public ConnectionWithDBLostException(String errorType) {
        super(errorType);
        this.errorType = errorType;
    }
}
