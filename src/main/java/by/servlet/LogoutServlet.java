package by.servlet;

import by.exception.CustomResponseException;
import by.exception.JsonParserException;
import by.provider.AuthedUserProvider;
import by.servlet.response.user.LogoutResponse;
import by.servlet.utils.ErrorUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static by.util.TextLabels.JSON_FILE;
import static by.util.TextLabels.UTF8;

@WebServlet(name = "by/servlet/LogoutServlet.java", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ErrorUtils errorUtils = new ErrorUtils();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            AuthedUserProvider.clearThreadLocalUser();
            LogoutResponse logoutResponse = new LogoutResponse();
            logoutResponse.setLogout(true);

            String jsonString = objectMapper.writeValueAsString(logoutResponse);
            resp.setContentType(JSON_FILE);
            resp.setCharacterEncoding(UTF8);
            PrintWriter out = resp.getWriter();
            out.print(jsonString);
            out.flush();
//        } catch (IncorrectRequestParameterException | ConnectionWithDBLostException | NullQueryException ex) {
//            errorUtils.sendErrorJson(resp, ex);
        } catch (JsonProcessingException ex) {
            errorUtils.sendErrorJson(resp, new JsonParserException());
        } catch (IOException ex) {
            errorUtils.sendErrorJson(resp, new CustomResponseException());
        }
    }
}