package by.exception;

import by.exception.abstract_model.AbstractException;
import by.exception.abstract_model.BaseErrorCodes;

public class CustomFileToJsonException extends AbstractException {
    public CustomFileToJsonException(Throwable cause) {
        super(cause, BaseErrorCodes.FILE_JSON_EXCEPTION);
    }

    public CustomFileToJsonException() {
        super(BaseErrorCodes.FILE_JSON_EXCEPTION);
    }
}
