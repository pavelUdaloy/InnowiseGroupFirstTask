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

import static by.util.TextLabels.JSON_FILE;
import static by.util.TextLabels.UTF8;

public class ErrorUtils {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    void sendErrorJson(HttpServletResponse resp, AbstractException exception) {
        PrintWriter out = resp.getWriter();
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorId(), exception.getErrorCode());
        String jsonString = objectMapper.writeValueAsString(errorResponse);
        resp.setContentType(JSON_FILE);
        resp.setCharacterEncoding(UTF8);
        out.print(jsonString);
        out.flush();
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
