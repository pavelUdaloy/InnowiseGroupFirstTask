package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.REQUEST_PARSER_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.REQUEST_PARSER_STATUS;

public class IncorrectRequestParameterException extends AbstractException {
    public IncorrectRequestParameterException(Throwable cause) {
        super(cause, REQUEST_PARSER_EXCEPTION, REQUEST_PARSER_STATUS);
    }

    public IncorrectRequestParameterException() {
        super(REQUEST_PARSER_EXCEPTION, REQUEST_PARSER_STATUS);
    }
}
