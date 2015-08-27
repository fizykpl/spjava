package p.lodz.pl.spjava.sdudkiewicz;

import java.util.logging.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    private static final Logger LOGGER = Logger.getLogger( MvcConfig.class.getName() );
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        LOGGER.info("<<<addViewControllers" + registry.toString());
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("user");
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }

}
