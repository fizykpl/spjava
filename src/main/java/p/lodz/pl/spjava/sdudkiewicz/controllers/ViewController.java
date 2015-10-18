/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Sets;

import antlr.Utils;
import p.lodz.pl.spjava.sdudkiewicz.models.Admin;
import p.lodz.pl.spjava.sdudkiewicz.models.Domain;
import p.lodz.pl.spjava.sdudkiewicz.models.Script;
import p.lodz.pl.spjava.sdudkiewicz.models.User;
import p.lodz.pl.spjava.sdudkiewicz.repository.AdminRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.DomainRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.ScriptRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.UserRepository;
import p.lodz.pl.spjava.sdudkiewicz.utils.UsersUtils;

@Controller
public class ViewController {

	ExecutorService executor = Executors.newFixedThreadPool(10);

	@Autowired
	DomainRepository domainRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	ScriptRepository scriptRepository;

	private static final Logger LOGGER = Logger.getLogger(ViewController.class
			.getName());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, Principal principal) {
		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String greetingForm(Model model, Principal principal) {
		LOGGER.info(model.toString());
		LOGGER.info(principal.toString());
		return toUser(model, principal);
	}


	private String toUser(Model model, Principal principal) {
		String name = principal.getName();
		List<Script> scripts = (List<Script>) scriptRepository.findAll();
		User user = userRepository.findByUid(name);
		Set<Domain> domains = getDomains(user);
		if (user != null) {
			model.addAttribute("domains", domains);
			model.addAttribute("name", user.getCn());
			model.addAttribute("scripts", scripts);
		}
		return "user";
	}
	
	
    private Set<Domain> getDomains(User user){
    	Set<Domain> domains = null; 
    	Admin admin = adminRepository.findByUid(user.getUid());
    	if(null != admin ){
    		return Sets.newHashSet(domainRepository.findAll());
    	}else{
    		return user.getDomains();
    	}
    }

}
