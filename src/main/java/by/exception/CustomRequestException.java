package by.exception;

import by.exception.abstract_model.AbstractException;
import by.exception.abstract_model.BaseErrorCodes;

public class CustomRequestException extends AbstractException {
    public CustomRequestException(Throwable cause) {
        super(cause, BaseErrorCodes.REQUEST_EXCEPTION);
    }

    public CustomRequestException() {
        super(BaseErrorCodes.REQUEST_EXCEPTION);
    }
}
