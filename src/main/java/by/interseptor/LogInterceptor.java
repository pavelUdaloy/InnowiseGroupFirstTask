package by.interseptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.util.TextLabels.HTTP_METHOD_MESSAGE;
import static by.util.TextLabels.POST_INTERCEPTOR_HANDLER;
import static by.util.TextLabels.PRE_INTERCEPTOR_HANDLER;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(LogInterceptor.class));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LOGGER.log(Level.INFO, PRE_INTERCEPTOR_HANDLER + request.getRequestURI() + HTTP_METHOD_MESSAGE + request.getServletPath());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        LOGGER.log(Level.INFO, POST_INTERCEPTOR_HANDLER + request.getRequestURI() + HTTP_METHOD_MESSAGE + request.getServletPath());
    }
}