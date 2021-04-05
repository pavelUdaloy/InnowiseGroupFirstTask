package by.filter;

import by.servlet.utils.ErrorUtils;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.util.TextLabels.AUTH_FILTER_DESTROY;
import static by.util.TextLabels.AUTH_FILTER_INIT;

@WebFilter(filterName = "by/filter/AuthFilter.java", servletNames =
        {"by/servlet/RegServlet.java", "by/servlet/AdsServlet.java",
                "by/servlet/AuthServlet.java", "by/servlet/LogoutServlet.java"})
public class AuthFilter extends HttpFilter {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(AuthFilter.class));

    private final ErrorUtils errorUtils = new ErrorUtils();

    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) {
        LOGGER.log(Level.INFO, AUTH_FILTER_INIT);
    }

    @Override
    public void destroy() {
        LOGGER.log(Level.INFO, AUTH_FILTER_DESTROY);
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
//        Integer id = null;
//        try {
//            id = Integer.valueOf(req.getHeader(ID));
//        } catch (IllegalArgumentException ignored) {
//        }
//        switch (req.getMethod()) {
//            case HttpMethod.GET:
//                super.doFilter(req, res, chain);
//                break;
//            case HttpMethod.DELETE:
//            case HttpMethod.POST:
//            case HttpMethod.PUT:
//                switch (req.getServletPath()) {
//                    case "/auth":
//                    case "/reg":
//                        if (id != null) {
//                            errorUtils.sendErrorJson(res, new RequestHeaderException());
//                        } else {
//                            super.doFilter(req, res, chain);
//                        }
//                        break;
//                    case "/ads":
//                    case "/logout":
//                        if (id == null) {
//                            errorUtils.sendErrorJson(res, new RequestHeaderException());
//                        } else {
//                            super.doFilter(req, res, chain);
//                        }
//                }
//        }
    }
}
