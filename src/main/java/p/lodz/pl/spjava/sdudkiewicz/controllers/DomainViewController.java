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
public class DomainViewController {

	@Autowired
	DomainRepository domainRepository;
	@Autowired
	UserRepository userRepository;

	private static final Logger LOGGER = Logger
			.getLogger(DomainViewController.class.getName());

	@RequestMapping(value = "/domains", method = RequestMethod.GET)
	public String domains(Model model, Principal principal) {
		//TODO lista domen per użytkownik
		List<Domain> domains = (List<Domain>) domainRepository.findAll();
		
		model.addAttribute("domains", domains);
		model.addAttribute("name", principal.getName());
		return "domains";
	}
	
	@RequestMapping(value = "/domain/{subject}", method = RequestMethod.GET)
	public String showDdomain(@PathVariable String subject, Model model, Principal principal) {
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
	
	@RequestMapping(value = "/domain/add/{subject}/{uid}", method = RequestMethod.GET)
	public String addUser(@PathVariable String subject,@PathVariable String uid, Model model, Principal principal) {
		LOGGER.info("/domain/add/");
		
		addPairSubjectUser(subject,uid);
		
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
	public String removeUser(@PathVariable String subject,@PathVariable String uid, Model model, Principal principal) {
		LOGGER.info("/domain/add/");
		
		removePairSubjectUser(subject,uid);
		
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
