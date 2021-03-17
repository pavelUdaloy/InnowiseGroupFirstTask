package by.exception;

import by.exception.abstract_model.AbstractException;
import by.exception.abstract_model.BaseErrorCodes;

public class IncorrectSQLParametersException extends AbstractException {
    public IncorrectSQLParametersException(Throwable cause) {
        super(cause, BaseErrorCodes.INCORRECT_SQL_PARAMETERS_EXCEPTION);
    }

    public IncorrectSQLParametersException() {
        super(BaseErrorCodes.INCORRECT_SQL_PARAMETERS_EXCEPTION);
    }
}
