package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.DAO_OPERATION_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.DAO_OPERATION_STATUS;

public class DaoOperationException extends AbstractException {
    public DaoOperationException(Throwable cause) {
        super(cause, DAO_OPERATION_EXCEPTION, DAO_OPERATION_STATUS);
    }

    public DaoOperationException() {
        super(DAO_OPERATION_EXCEPTION, DAO_OPERATION_STATUS);
    }
}
