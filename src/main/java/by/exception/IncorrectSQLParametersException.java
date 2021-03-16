package by.exception;

import lombok.Getter;
import lombok.ToString;

import java.sql.SQLException;

import static by.util.TextLabels.INCORRECT_SQL_MESSAGE;

@ToString
public class IncorrectSQLParametersException extends SQLException {
    @Getter
    private final String message = INCORRECT_SQL_MESSAGE;

    private final SQLException exception;

    public IncorrectSQLParametersException(SQLException exception) {
        super(exception.getNextException());
        this.exception = exception;
    }
}
