package by.exception;

import lombok.Getter;
import lombok.ToString;

import java.sql.SQLException;

@ToString
public class IncorrectSQLParametersException extends SQLException {
    @Getter
    private static final String message = "Incorrect params for sql request";

    private final SQLException exception;

    public IncorrectSQLParametersException(SQLException exception) {
        super(exception.getNextException());
        this.exception = exception;
    }
}
