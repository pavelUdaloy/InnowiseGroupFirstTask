package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.NULL_SQL_QUERY_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.NULL_SQL_QUERY_STATUS;

public class NullQueryException extends AbstractException {
    public NullQueryException(Throwable cause) {
        super(cause, NULL_SQL_QUERY_EXCEPTION, NULL_SQL_QUERY_STATUS);
    }

    public NullQueryException() {
        super(NULL_SQL_QUERY_EXCEPTION, NULL_SQL_QUERY_STATUS);
    }
}