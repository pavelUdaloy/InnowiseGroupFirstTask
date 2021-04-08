package by.exception.abstract_model;

import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class AbstractException extends RuntimeException implements CommonException {
    private String errorId;
    private String errorCode;
    private Integer errorStatusCode;

    public AbstractException(Throwable cause,
                             ExceptionErrorCode exceptionErrorCode, ExceptionStatusCode exceptionStatusCode) {
        super(cause);
        init(exceptionErrorCode, exceptionStatusCode);
    }

    public AbstractException(ExceptionErrorCode exceptionErrorCode, ExceptionStatusCode exceptionStatusCode) {
        init(exceptionErrorCode, exceptionStatusCode);
    }

    private void init(ExceptionErrorCode exceptionErrorCode, ExceptionStatusCode exceptionStatusCode) {
        errorId = UUID.randomUUID().toString();
        errorCode = exceptionErrorCode.getCode();
        errorStatusCode = exceptionStatusCode.getStatus();
    }

}
