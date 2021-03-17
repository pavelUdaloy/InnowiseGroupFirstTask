package by.exception;

import by.exception.abstract_model.AbstractException;
import by.exception.abstract_model.BaseErrorCodes;

public class IncorrectRequestParameterException extends AbstractException {
    public IncorrectRequestParameterException(Throwable cause) {
        super(cause, BaseErrorCodes.REQUEST_PARSER_EXCEPTION);
    }

    public IncorrectRequestParameterException() {
        super(BaseErrorCodes.REQUEST_PARSER_EXCEPTION);
    }
}
