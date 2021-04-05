package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.AUTH_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.AUTH_STATUS;

public class AuthException extends AbstractException {
    public AuthException(Throwable cause) {
        super(cause, AUTH_EXCEPTION, AUTH_STATUS);
    }

    public AuthException() {
        super(AUTH_EXCEPTION, AUTH_STATUS);
    }
}