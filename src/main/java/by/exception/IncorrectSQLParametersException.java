package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.INCORRECT_SQL_PARAMETERS_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.INCORRECT_SQL_PARAMETERS_STATUS;

public class IncorrectSQLParametersException extends AbstractException {
    public IncorrectSQLParametersException(Throwable cause) {
        super(cause, INCORRECT_SQL_PARAMETERS_EXCEPTION, INCORRECT_SQL_PARAMETERS_STATUS);
    }

    public IncorrectSQLParametersException() {
        super(INCORRECT_SQL_PARAMETERS_EXCEPTION, INCORRECT_SQL_PARAMETERS_STATUS);
    }
}
