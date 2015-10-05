/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import p.lodz.pl.spjava.sdudkiewicz.models.Domain;
import p.lodz.pl.spjava.sdudkiewicz.models.User;
import p.lodz.pl.spjava.sdudkiewicz.repository.AdminRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.DomainRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.UserRepository;

/**
 *
 * @author sylwekabc06
 */
@Controller
public class DomainViewController {

	@Autowired
	DomainRepository domainRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	AdminRepository adminRepository;

	private static final Logger LOGGER = Logger
			.getLogger(DomainViewController.class.getName());

	@RequestMapping(value = "/domains", method = RequestMethod.GET)
	public String domains(Model model, Principal principal) {

		domainsMain(model, principal);
		return "domains";
	}
	
	@RequestMapping(value = "/domains", method = RequestMethod.POST)
	public String addDomains(@RequestParam String domain, @RequestParam String remove ,Model model, Principal principal) {
		if(remove.equals("true")){
			domainRepository.delete(domainRepository.findBySubject(domain));
			
		}else{
			domainRepository.save(new Domain(domain));
			
		}
		domainsMain(model, principal);
		return "domains";
	}

	private void domainsMain(Model model, Principal principal) {
		List<Domain> domains = (List<Domain>) domainRepository.findAll();

		model.addAttribute("domains", domains);
		model.addAttribute("name", principal.getName());
	}

	@RequestMapping(value = "/domain/{subject}", method = RequestMethod.GET)
	public String showDdomain(@PathVariable String subject, Model model,
			Principal principal) {
		LOGGER.info("/domain/" + subject);
		Domain domain = domainRepository.findBySubject(subject);

		Set<User> userInSubject = domain.getUsers();
		List<User> userNotInSubject = (List<User>) userRepository.findAll();
		userNotInSubject.removeAll(userInSubject);

		model.addAttribute("userToAdd", userNotInSubject);
		model.addAttribute("userToRemove", userInSubject);
		model.addAttribute("domain", domain);
		return "domain";
	}

	@RequestMapping(value = "/domain/{subject}", method = RequestMethod.POST)
	public String showFilerDomain(@RequestParam String filter,
			@PathVariable String subject, Model model, Principal principal) {
		LOGGER.info("/domain/" + subject);
		LOGGER.info("filter =" + filter );
		Domain domain = domainRepository.findBySubject(subject);

		Set<User> userInSubject = domain.getUsers();
		List<User> userNotInSubject = (List<User>) userRepository.findAll();
		userNotInSubject.removeAll(userInSubject);

		if (filter.length() > 2) {
			List<User> filterUserInSubject = new ArrayList<User>();
			List<User> filterUserNotInSubject = new ArrayList<User>();
			for (User u : userInSubject) {
				if (u.getCn().toLowerCase().contains(filter.toLowerCase())) {
					filterUserInSubject.add(u);
				}
			}
			for (User u : userNotInSubject) {
				if (u.getCn().toLowerCase().contains(filter.toLowerCase())) {
					filterUserNotInSubject.add(u);
				}
			}
			model.addAttribute("userToAdd", filterUserNotInSubject);
			model.addAttribute("userToRemove", filterUserInSubject);
			model.addAttribute("domain", domain);
			return "domain";
		}

		model.addAttribute("userToAdd", userNotInSubject);
		model.addAttribute("userToRemove", userInSubject);
		model.addAttribute("domain", domain);
		return "domain";
	}

	@RequestMapping(value = "/domain/add/{subject}/{uid}", method = RequestMethod.GET)
	public String addUser(@PathVariable String subject,
			@PathVariable String uid, Model model, Principal principal) {
		LOGGER.info("/domain/add/");

		addPairSubjectUser(subject, uid);

		Domain domain = domainRepository.findBySubject(subject);

		Set<User> userInSubject = domain.getUsers();
		List<User> userNotInSubject = (List<User>) userRepository.findAll();
		userNotInSubject.removeAll(userInSubject);

		model.addAttribute("userToAdd", userNotInSubject);
		model.addAttribute("userToRemove", userInSubject);
		model.addAttribute("domain", domain);
		return "domain";
	}

	@RequestMapping(value = "/domain/remove/{subject}/{uid}", method = RequestMethod.GET)
	public String removeUser(@PathVariable String subject,
			@PathVariable String uid, Model model, Principal principal) {
		LOGGER.info("/domain/add/");

		removePairSubjectUser(subject, uid);
		Domain domain = domainRepository.findBySubject(subject);
		Set<User> userInSubject = domain.getUsers();
		List<User> userNotInSubject = (List<User>) userRepository.findAll();
		userNotInSubject.removeAll(userInSubject);
		model.addAttribute("userToAdd", userNotInSubject);
		model.addAttribute("userToRemove", userInSubject);
		model.addAttribute("domain", domain);
		return "domain";
	}

	private void addPairSubjectUser(String subject, String uid) {
		Domain findBySubject = domainRepository.findBySubject(subject);
		User findByUid = userRepository.findByUid(uid);

		findBySubject.getUsers().add(findByUid);
		findByUid.getDomains().add(findBySubject);
		domainRepository.save(findBySubject);
	}

	private void removePairSubjectUser(String subject, String uid) {
		Domain findBySubject = domainRepository.findBySubject(subject);
		User findByUid = userRepository.findByUid(uid);

		findBySubject.getUsers().remove(findByUid);
		findByUid.getDomains().remove(findBySubject);
		domainRepository.save(findBySubject);
	}

}
