package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.RESPONSE_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.RESPONSE_STATUS;

public class CustomResponseException extends AbstractException {
    public CustomResponseException(Throwable cause) {
        super(cause, RESPONSE_EXCEPTION, RESPONSE_STATUS);
    }

    public CustomResponseException() {
        super(RESPONSE_EXCEPTION, RESPONSE_STATUS);
    }
}
