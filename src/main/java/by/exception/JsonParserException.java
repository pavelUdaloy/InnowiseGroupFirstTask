package by.exception;

import by.exception.abstract_model.AbstractException;
import by.exception.abstract_model.BaseErrorCodes;

public class JsonParserException extends AbstractException {
    public JsonParserException(Throwable cause) {
        super(cause, BaseErrorCodes.JSON_PARSER_EXCEPTION);
    }

    public JsonParserException() {
        super(BaseErrorCodes.JSON_PARSER_EXCEPTION);
    }
}