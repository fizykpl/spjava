/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import p.lodz.pl.spjava.sdudkiewicz.domain.Domain;
import p.lodz.pl.spjava.sdudkiewicz.fakeDB.Domains;

/**
 *
 * @author sylwekabc06
 */
@Controller
public class UserController {
    private static final Logger LOGGER = Logger.getLogger( UserController.class.getName() );
    

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String greetingForm(Model model, Principal principal) {
        LOGGER.info(model.toString());
        LOGGER.info(principal.toString());
        String name = principal.getName();
        List<Domain> domains= Domains.getDomains(name);
        model.addAttribute("domain", new Domain("java", "01", true, "bob", "agata"));
        if (Domains.containsUser(principal.getName())) {
            model.addAttribute("name", principal.getName());
        } else {
            model.addAttribute("name", "Anonymus");
        }      
        
        
        LOGGER.info(model.toString());
        return "user";
    }
}
