/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.controllers;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import p.lodz.pl.spjava.sdudkiewicz.models.Script;
import p.lodz.pl.spjava.sdudkiewicz.models.User;
import p.lodz.pl.spjava.sdudkiewicz.repository.AdminRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.DomainRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.ScriptRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.UserRepository;

/**
 *
 * @author sylwekabc06
 */
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
		if (user != null) {

			model.addAttribute("domains", user.getDomains());
			model.addAttribute("name", user.getCn());
			model.addAttribute("scripts", scripts);
		}
		return "user";
	}

}
