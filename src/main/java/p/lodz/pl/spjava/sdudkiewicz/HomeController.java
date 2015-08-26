package p.lodz.pl.spjava.sdudkiewicz;

import java.security.Principal;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
private static final Logger LOGGER = Logger.getLogger( HomeController.class.getName() );
    @RequestMapping("/")
    public String index(Principal principal) {
        LOGGER.info("index " + principal.toString());
        return "Welcome to the home page! " +principal.getName();
    }
}
