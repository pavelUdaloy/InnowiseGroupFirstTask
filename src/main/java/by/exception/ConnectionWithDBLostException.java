package by.exception;

import lombok.Getter;
import lombok.ToString;

import static by.util.TextLabels.DB_LOST_MESSAGE;

@ToString
public class ConnectionWithDBLostException extends Exception {
    @Getter
    private final String message = DB_LOST_MESSAGE;

    private final String errorType;

    public ConnectionWithDBLostException(String errorType) {
        super(errorType);
        this.errorType = errorType;
    }
}
