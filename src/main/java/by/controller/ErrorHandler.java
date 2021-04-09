package by.controller;

import by.controller.response.error.ErrorResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.CustomFileToJsonException;
import by.exception.CustomRequestException;
import by.exception.DaoOperationException;
import by.exception.EmptyDbAnswerException;
import by.exception.JsonParserException;
import by.exception.abstract_model.AbstractException;
import by.interseptor.LogInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

    @ExceptionHandler(value = {JsonParserException.class, CustomFileToJsonException.class, CustomRequestException.class, ConnectionWithDBLostException.class, EmptyDbAnswerException.class, DaoOperationException.class})
    protected ResponseEntity<ErrorResponse> handleConnectionConflict(AbstractException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorId(), ex.getErrorCode(), ex.getErrorStatusCode());
        LOGGER.error(errorResponse.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
