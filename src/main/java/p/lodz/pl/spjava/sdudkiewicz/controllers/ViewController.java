/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import p.lodz.pl.spjava.sdudkiewicz.models.Domain;
import p.lodz.pl.spjava.sdudkiewicz.models.User;
import p.lodz.pl.spjava.sdudkiewicz.repository.DomainRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.UserRepository;
import p.lodz.pl.spjava.sdudkiewicz.script.Script;
import p.lodz.pl.spjava.sdudkiewicz.utils.UsersUtils;

/**
 *
 * @author sylwekabc06
 */
@Controller
public class ViewController {

	@Autowired
	DomainRepository domainRepository;
	@Autowired
	UserRepository userRepository;

	private static final Logger LOGGER = Logger.getLogger(ViewController.class
			.getName());

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String greetingForm(Model model, Principal principal) {
		LOGGER.info(model.toString());
		LOGGER.info(principal.toString());

		

		return toUser(model, principal);

	}

	@RequestMapping(value = "/script/start/{subject}", method = RequestMethod.GET)
	public String scriptStart(@PathVariable String subject, Model model,
			Principal principal) {
		LOGGER.info("/script/start/" + subject);

		User user = userRepository.findByUid(principal.getName());
		Set<Domain> domain = user.getDomains();
		if (true) {
			Script scipt = new Script();
			scipt.startDomain(subject);
			return toUser(model, principal);
		} else {
			return "errorDomain";
		}

	}

	@RequestMapping(value = "/script/stop/{subject}", method = RequestMethod.GET)
	public String scriptStop(@PathVariable String subject, Model model,
			Principal principal) {
		LOGGER.info("/script/stop/" + subject);
		if (true) {
			Script scipt = new Script();
			scipt.stopDomain(subject);
			return toUser(model, principal);
		} else {
			return "errorDomain";
		}

	}

	private String toUser(Model model, Principal principal) {
		String name = principal.getName();

		User user = userRepository.findByUid(name);
		if (user != null) {
			model.addAttribute("domains", user.getDomains());
			model.addAttribute("name", user.getCn());
			return "user";
		} else {

			return "user";
		}
	}

	

}
