package by.servlet;

import by.entity.dto.UserDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.CustomResponseException;
import by.exception.IncorrectRequestParameterException;
import by.exception.JsonParserException;
import by.exception.NullQueryException;
import by.service.UserService;
import by.service.UserServiceImpl;
import by.servlet.utils.ErrorUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static by.util.TextLabels.JSON_FILE;
import static by.util.TextLabels.UTF8;

@WebServlet(name = "by/servlet/RegServlet.java", urlPatterns = "/reg")
public class RegServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule()).findAndRegisterModules();

    private final UserService userService = new UserServiceImpl();

    private final ErrorUtils errorUtils = new ErrorUtils();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            UserDto userDto = objectMapper.readValue(req.getReader(), UserDto.class);
            if (userDto == null) {
                throw new IncorrectRequestParameterException();
            }

            UserDto addedUser = userService.add(userDto);

            String jsonString = objectMapper.writeValueAsString(addedUser);
            resp.setContentType(JSON_FILE);
            resp.setCharacterEncoding(UTF8);
            PrintWriter out = resp.getWriter();
            out.print(jsonString);
            out.flush();
        } catch (IncorrectRequestParameterException | ConnectionWithDBLostException | NullQueryException ex) {
            errorUtils.sendErrorJson(resp, ex);
        } catch (JsonProcessingException ex) {
            errorUtils.sendErrorJson(resp, new JsonParserException());
        } catch (IOException ex) {
            errorUtils.sendErrorJson(resp, new CustomResponseException());
        }
    }
}