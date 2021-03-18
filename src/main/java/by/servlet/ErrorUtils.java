package by.servlet;

import by.exception.abstract_model.AbstractException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.util.TextLabels.EXCEPTION_MESSAGE;
import static by.util.TextLabels.JSON_FILE;
import static by.util.TextLabels.UTF8;

public class ErrorUtils {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ErrorUtils.class));

    @SneakyThrows
    void sendErrorJson(HttpServletResponse resp, AbstractException exception) {
        PrintWriter out = resp.getWriter();
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorId(), exception.getErrorCode());
        String jsonString = objectMapper.writeValueAsString(errorResponse);
        resp.setContentType(JSON_FILE);
        resp.setCharacterEncoding(UTF8);
        out.print(jsonString);
        out.flush();
        LOGGER.log(Level.SEVERE, EXCEPTION_MESSAGE + jsonString);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorResponse {
        private String errorId;
        private String errorCode;
    }
}
