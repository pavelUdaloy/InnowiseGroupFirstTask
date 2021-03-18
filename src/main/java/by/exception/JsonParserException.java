package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.JSON_PARSER_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.JSON_PARSER_STATUS;

public class JsonParserException extends AbstractException {
    public JsonParserException(Throwable cause) {
        super(cause, JSON_PARSER_EXCEPTION, JSON_PARSER_STATUS);
    }

    public JsonParserException() {
        super(JSON_PARSER_EXCEPTION, JSON_PARSER_STATUS);
    }
}