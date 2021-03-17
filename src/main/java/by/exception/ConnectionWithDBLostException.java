package by.exception;

import by.exception.abstract_model.AbstractException;
import by.exception.abstract_model.BaseErrorCodes;

public class ConnectionWithDBLostException extends AbstractException {
    public ConnectionWithDBLostException(Throwable cause) {
        super(cause, BaseErrorCodes.CONNECTION_WITH_DB_LOST_EXCEPTION);
    }

    public ConnectionWithDBLostException() {
        super(BaseErrorCodes.CONNECTION_WITH_DB_LOST_EXCEPTION);
    }
}
