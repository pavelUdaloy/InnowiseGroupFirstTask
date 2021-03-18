package by.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.util.TextLabels.FILTER_DESTROY;
import static by.util.TextLabels.FILTER_INIT;
import static by.util.TextLabels.HTTP_METHOD_MESSAGE;
import static by.util.TextLabels.PROPERTIES_PATH;
import static by.util.TextLabels.property;

@WebFilter(filterName = "by/filter/MainFilter.java", servletNames =
        {"by/servlet/CarAdServlet.java", "by/servlet/UserServlet.java", "by/servlet/ImageServlet.java"})
public class MainFilter extends HttpFilter {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(MainFilter.class));

    public MainFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) {
        LOGGER.log(Level.INFO, FILTER_INIT);
        try (FileInputStream fis = new FileInputStream(PROPERTIES_PATH)) {
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        LOGGER.log(Level.INFO, FILTER_DESTROY);
    }

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        request.
//        if (request instanceof HttpServletRequest) {
//            LOGGER.log(Level.INFO, ((HttpServletRequest) request).getRequestURL().toString());
//        }
//        chain.doFilter(request, response);
//    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        LOGGER.log(Level.INFO, req.getRequestURL().toString() + HTTP_METHOD_MESSAGE + req.getMethod());
        super.doFilter(req, res, chain);
    }
}
