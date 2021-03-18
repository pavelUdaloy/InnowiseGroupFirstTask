package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.CONNECTION_WITH_DB_LOST_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.CONNECTION_WITH_DB_LOST_STATUS;

public class ConnectionWithDBLostException extends AbstractException {
    public ConnectionWithDBLostException(Throwable cause) {
        super(cause, CONNECTION_WITH_DB_LOST_EXCEPTION, CONNECTION_WITH_DB_LOST_STATUS);
    }

    public ConnectionWithDBLostException() {
        super(CONNECTION_WITH_DB_LOST_EXCEPTION, CONNECTION_WITH_DB_LOST_STATUS);
    }
}
