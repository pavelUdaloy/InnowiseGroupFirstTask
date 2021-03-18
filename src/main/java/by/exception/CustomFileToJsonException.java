package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.FILE_JSON_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.FILE_JSON_STATUS;

public class CustomFileToJsonException extends AbstractException {
    public CustomFileToJsonException(Throwable cause) {
        super(cause, FILE_JSON_EXCEPTION, FILE_JSON_STATUS);
    }

    public CustomFileToJsonException() {
        super(FILE_JSON_EXCEPTION, FILE_JSON_STATUS);
    }
}
