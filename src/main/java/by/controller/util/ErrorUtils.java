package by.controller.util;

import by.exception.abstract_model.AbstractException;
import by.controller.response.error.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

import static by.util.TextLabels.EXCEPTION_MESSAGE;

@Component
public class ErrorUtils {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ErrorUtils.class));

    public ResponseEntity getErrorResponse(AbstractException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorId(), exception.getErrorCode(), exception.getErrorStatusCode());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        LOGGER.log(Level.SEVERE, EXCEPTION_MESSAGE + errorResponse.getErrorId() + " " + errorResponse.getErrorCode());
        return new ResponseEntity(errorResponse, headers, HttpStatus.CONFLICT);
    }
}
