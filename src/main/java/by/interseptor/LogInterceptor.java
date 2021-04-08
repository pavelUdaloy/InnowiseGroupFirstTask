package by.interseptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.util.TextLabels.HTTP_METHOD_MESSAGE;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(LogInterceptor.class));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LOGGER.log(Level.INFO, "pre method ----" + request.getRequestURI() + HTTP_METHOD_MESSAGE + request.getServletPath());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        LOGGER.log(Level.INFO, "post method ----" + request.getRequestURI() + HTTP_METHOD_MESSAGE + request.getServletPath());
    }
}