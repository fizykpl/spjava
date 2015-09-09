/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.controllers;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import p.lodz.pl.spjava.sdudkiewicz.models.Domain;
import p.lodz.pl.spjava.sdudkiewicz.repository.DomainRepository;
import p.lodz.pl.spjava.sdudkiewicz.user.User;
import p.lodz.pl.spjava.sdudkiewicz.utils.UsersUtils;

/**
 *
 * @author sylwekabc06
 */
@Controller
public class ViewController {

    @Autowired
    DomainRepository domainRepository;

    private static final Logger LOGGER = Logger.getLogger(ViewController.class.getName());

    @RequestMapping(value = "/domain", method = RequestMethod.GET)
    public String domain() {
        return "domain";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String greetingForm(Model model, Principal principal) {
        LOGGER.info(model.toString());
        LOGGER.info(principal.toString());
        String name = principal.getName();
        List<Domain> domains = (List<Domain>) domainRepository.findAll();

//        model.addAttribute("domain", new Domain("java", "01", true, "bob", "agata"));
//        if (Domains.containsUser(principal.getName())) {
        model.addAttribute("domains", domains);
//        } else {
        model.addAttribute("name", principal.getName());
//        }

        LOGGER.info(model.toString());
        return "user";
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String userList(Model model, Principal principal) {
        LOGGER.info(model.toString());
        LOGGER.info(principal.toString());
        List<User> users = UsersUtils.getUsers();
//        String name = principal.getName();
//        List<Domain> domains = Domains.getDomains(name);
        model.addAttribute("users", users);
//        if (Domains.containsUser(principal.getName())) {
//            model.addAttribute("name", principal.getName());
//        } else {
//            model.addAttribute("name", "Anonymus");
//        }

        LOGGER.info(model.toString());
        return "userList";
    }

}
