package by.exception;

import by.exception.abstract_model.AbstractException;
import by.exception.abstract_model.BaseErrorCodes;

public class CustomResponseException extends AbstractException {
    public CustomResponseException(Throwable cause) {
        super(cause, BaseErrorCodes.RESPONSE_EXCEPTION);
    }

    public CustomResponseException() {
        super(BaseErrorCodes.RESPONSE_EXCEPTION);
    }
}
