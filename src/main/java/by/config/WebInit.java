package by.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.io.FileInputStream;
import java.io.IOException;

import static by.util.TextLabels.MAIN_SERVLET_MAPPING_PATH;
import static by.util.TextLabels.MAIN_SERVLET_NAME;
import static by.util.TextLabels.PROPERTIES_PATH;
import static by.util.TextLabels.property;

public class WebInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic dispatcher
                = servletContext.addServlet(MAIN_SERVLET_NAME, new DispatcherServlet(ctx));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(MAIN_SERVLET_MAPPING_PATH);

        try (FileInputStream fis = new FileInputStream(PROPERTIES_PATH)) {
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
