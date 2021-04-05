package by.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.util.TextLabels.HTTP_METHOD_MESSAGE;
import static by.util.TextLabels.LOG_FILTER_DESTROY;
import static by.util.TextLabels.LOG_FILTER_INIT;
import static by.util.TextLabels.PROPERTIES_PATH;
import static by.util.TextLabels.property;

@WebFilter(filterName = "by/filter/LogFilter.java", servletNames =
        {"by/servlet/RegServlet.java", "by/servlet/AdsServlet.java", "by/servlet/AuthServlet.java", "by/servlet/ImageServlet.java"})
public class LogFilter extends HttpFilter {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(LogFilter.class));

    public LogFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) {
        LOGGER.log(Level.INFO, LOG_FILTER_INIT);
        try (FileInputStream fis = new FileInputStream(PROPERTIES_PATH)) {
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        LOGGER.log(Level.INFO, LOG_FILTER_DESTROY);
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        LOGGER.log(Level.INFO, req.getRequestURI() + HTTP_METHOD_MESSAGE + req.getServletPath());
        super.doFilter(req, res, chain);
    }
}
