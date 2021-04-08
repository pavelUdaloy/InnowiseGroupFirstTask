package by.controller;

import by.controller.response.error.ErrorResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.CustomFileToJsonException;
import by.exception.CustomRequestException;
import by.exception.DaoOperationException;
import by.exception.JsonParserException;
import by.exception.NullQueryException;
import by.exception.abstract_model.AbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import static by.util.TextLabels.EXCEPTION_MESSAGE;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ErrorHandler.class));

    @ExceptionHandler(value = {JsonParserException.class, CustomFileToJsonException.class, CustomRequestException.class, ConnectionWithDBLostException.class, NullQueryException.class, DaoOperationException.class})
    protected ResponseEntity<ErrorResponse> handleConnectionConflict(AbstractException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorId(), ex.getErrorCode(), ex.getErrorStatusCode());
        LOGGER.log(Level.SEVERE, EXCEPTION_MESSAGE + errorResponse.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
