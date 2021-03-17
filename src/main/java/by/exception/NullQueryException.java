package by.exception;

import by.exception.abstract_model.AbstractException;
import by.exception.abstract_model.BaseErrorCodes;

public class NullQueryException extends AbstractException {
    public NullQueryException(Throwable cause) {
        super(cause, BaseErrorCodes.NULL_SQL_QUERY_EXCEPTION);
    }

    public NullQueryException() {
        super(BaseErrorCodes.NULL_SQL_QUERY_EXCEPTION);
    }
}