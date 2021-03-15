package by.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(filterName = "by/filter/MainFilter.java", servletNames =
        {"by/servlet/CarAdServlet.java", "by/servlet/UserServlet.java"})
public class MainFilter extends HttpFilter {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(MainFilter.class));

    public MainFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) {
        LOGGER.log(Level.INFO, "MainFilter initialized");
    }

    @Override
    public void destroy() {
        LOGGER.log(Level.INFO, "MainFilter destroyed");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            LOGGER.log(Level.INFO, ((HttpServletRequest) request).getRequestURL().toString());
        }
        chain.doFilter(request, response);
    }
}
