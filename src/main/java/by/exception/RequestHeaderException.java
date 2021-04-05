package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.REQUEST_HEADER_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.REQUEST_HEADER_STATUS;

public class RequestHeaderException extends AbstractException {
    public RequestHeaderException(Throwable cause) {
        super(cause, REQUEST_HEADER_EXCEPTION, REQUEST_HEADER_STATUS);
    }

    public RequestHeaderException() {
        super(REQUEST_HEADER_EXCEPTION, REQUEST_HEADER_STATUS);
    }
}