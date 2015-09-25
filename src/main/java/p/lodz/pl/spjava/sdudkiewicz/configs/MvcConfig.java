package p.lodz.pl.spjava.sdudkiewicz.configs;

import java.util.logging.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    private static final Logger LOGGER = Logger.getLogger( MvcConfig.class.getName() );
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        LOGGER.info(registry.toString());
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/user").setViewName("user");
    }

}
