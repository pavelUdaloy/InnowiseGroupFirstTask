package by.servlet.utils;

import by.exception.abstract_model.AbstractException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.util.TextLabels.EXCEPTION_MESSAGE;
import static by.util.TextLabels.JSON_FILE;
import static by.util.TextLabels.UTF8;

public class ErrorUtils {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule()).findAndRegisterModules();

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ErrorUtils.class));

    @SneakyThrows
    public void sendErrorJson(HttpServletResponse resp, AbstractException exception) {
        PrintWriter out = resp.getWriter();
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorId(), exception.getErrorCode(), exception.getErrorStatusCode());
        String jsonString = objectMapper.writeValueAsString(errorResponse);
        resp.setContentType(JSON_FILE);
        resp.setCharacterEncoding(UTF8);
        out.print(jsonString);
        out.flush();
        LOGGER.log(Level.SEVERE, EXCEPTION_MESSAGE + jsonString);
    }
}
