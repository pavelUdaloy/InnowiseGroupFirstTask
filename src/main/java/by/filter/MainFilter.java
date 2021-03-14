package by.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/main")
public class MainFilter implements Filter {
    public MainFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
//        System.out.println("LogFilter init!");
    }

    @Override
    public void destroy() {
        System.out.println("LogFilter destroy!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("BBBBBBBBBBBBBB");
//        String ipAddress = request.getRemoteAddr();
//        String dateTime = new Date().toString();
//
//        System.out.println("\n\n==============================================\n");
//
//        System.out.println("Request...");
//        System.out.println("Date/Time: " + dateTime);
//        System.out.println("IP:" + ipAddress);
//
//        System.out.println("\n==============================================\n");
//
//        response.setContentType("text/html");
//        PrintWriter printWriter = response.getWriter();
//        printWriter.println("Hello from your mom!");
//
//        printWriter.close();
//
//        request.setAttribute("hhh", "hello dad");


        chain.doFilter(request, response);
    }
}
