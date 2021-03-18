package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.REQUEST_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.REQUEST_STATUS;

public class CustomRequestException extends AbstractException {
    public CustomRequestException(Throwable cause) {
        super(cause, REQUEST_EXCEPTION, REQUEST_STATUS);
    }

    public CustomRequestException() {
        super(REQUEST_EXCEPTION, REQUEST_STATUS);
    }
}
