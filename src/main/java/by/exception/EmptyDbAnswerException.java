package by.exception;

import by.exception.abstract_model.AbstractException;

import static by.exception.abstract_model.ErrorCodes.EMPTY_DB_ANSWER_EXCEPTION;
import static by.exception.abstract_model.ErrorStatusCodes.EMPTY_DB_ANSWER_EXCEPTION_STATUS;

public class EmptyDbAnswerException extends AbstractException {
    public EmptyDbAnswerException(Throwable cause) {
        super(cause, EMPTY_DB_ANSWER_EXCEPTION, EMPTY_DB_ANSWER_EXCEPTION_STATUS);
    }

    public EmptyDbAnswerException() {
        super(EMPTY_DB_ANSWER_EXCEPTION, EMPTY_DB_ANSWER_EXCEPTION_STATUS);
    }
}