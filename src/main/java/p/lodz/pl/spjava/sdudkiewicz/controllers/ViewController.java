/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.controllers;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import p.lodz.pl.spjava.sdudkiewicz.ProcessStream;
import p.lodz.pl.spjava.sdudkiewicz.Processing;
import p.lodz.pl.spjava.sdudkiewicz.models.Domain;
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

	@RequestMapping(value = "/script/{subject}/{name}", method = RequestMethod.GET)
	public String scriptShow(@PathVariable String subject,
			@PathVariable String name, Model model, Principal principal) {
		LOGGER.info("/script/" + subject + "/" + name);
		Script script = scriptRepository.findByName(name);
		Domain domain = domainRepository.findBySubject(subject);
		model.addAttribute("script", script);
		model.addAttribute("domain", domain);
		return "script";

	}

	@RequestMapping(value = "/script/run", method = RequestMethod.POST)
	public String scriptRun(@RequestParam String subject,
			@RequestParam String name, Model model, Principal principal) {
		LOGGER.info("/script/" + subject + "/" + name);
		Script script = scriptRepository.findByName(name);
		Domain domain = domainRepository.findBySubject(subject);
		model.addAttribute("domain", domain);
		model.addAttribute("script", script);

		Callable<ProcessStream> callable = new Processing(script.getCommand());
		Future<ProcessStream> future = executor.submit(callable);
		ProcessStream pr = null;
		try {
			pr = future.get();
			model.addAttribute("binput", pr.isbInput());
			model.addAttribute("berror", pr.isbError());
			model.addAttribute("input", pr.getInput());
			model.addAttribute("error", pr.getError());

		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}

		return "script";

	}

	@RequestMapping(value = "/script/save", method = RequestMethod.POST)
	public String scriptSave(@RequestParam String subject,
			@RequestParam String name, @RequestParam String command,
			Model model, Principal principal) {
		LOGGER.info("/script/" + subject + "/" + name);
		LOGGER.info(command);
		Script script = scriptRepository.findByName(name);
		script.setCommand(command);
		scriptRepository.save(script);
		Domain domain = domainRepository.findBySubject(subject);
		model.addAttribute("domain", domain);
		model.addAttribute("script", script);
		return "script";
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
