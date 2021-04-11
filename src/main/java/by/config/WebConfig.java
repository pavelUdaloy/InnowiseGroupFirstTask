package by.config;

import by.dao.SessionFactory;
import by.interseptor.LogInterceptor;
import by.mapper.CarAdMapper;
import by.mapper.ImageMapper;
import by.mapper.UserMapper;
import by.repository.CarAdRepository;
import by.repository.ImageRepository;
import by.repository.UserRepository;
import by.service.AdService;
import by.service.AdServiceImpl;
import by.service.ImageService;
import by.service.ImageServiceImpl;
import by.service.UserService;
import by.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static by.util.TextLabels.INTERCEPTOR_PATH_PATTERN;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by")
public class WebConfig implements WebMvcConfigurer {
    private final LogInterceptor logInterceptor;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CarAdRepository carAdRepository;
    private final CarAdMapper carAdMapper;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public WebConfig(LogInterceptor logInterceptor, UserRepository userRepository, UserMapper userMapper, CarAdRepository carAdRepository, CarAdMapper carAdMapper, ImageRepository imageRepository, ImageMapper imageMapper) {
        this.logInterceptor = logInterceptor;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.carAdRepository = carAdRepository;
        this.carAdMapper = carAdMapper;
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor).addPathPatterns(INTERCEPTOR_PATH_PATTERN);
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository, userMapper, sessionFactory());
    }

    @Bean
    public ImageService imageService() {
        return new ImageServiceImpl(imageRepository, imageMapper);
    }

    @Bean
    public AdService adService() {
        return new AdServiceImpl(carAdRepository, userService(), carAdMapper, userMapper, sessionFactory());
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule()).findAndRegisterModules();
    }

    @Bean(destroyMethod = "preDestroy")
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SessionFactory sessionFactory() {
        return new SessionFactory();
    }
}
