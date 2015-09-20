///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package p.lodz.pl.spjava.sdudkiewicz.controllers;
//
//import java.security.Principal;
//import java.util.List;
//import java.util.logging.Logger;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import p.lodz.pl.spjava.sdudkiewicz.models.Domain;
//import p.lodz.pl.spjava.sdudkiewicz.models.User;
//import p.lodz.pl.spjava.sdudkiewicz.repository.DomainRepository;
//import p.lodz.pl.spjava.sdudkiewicz.repository.UserRepository;
//import p.lodz.pl.spjava.sdudkiewicz.utils.UsersUtils;
//
///**
// *
// * @author sylwekabc06
// */
//@Controller
//public class ScriptController {
//
//	@Autowired
//	DomainRepository domainRepository;
//	@Autowired
//	UserRepository userRepository;
//
//	private static final Logger LOGGER = Logger
//			.getLogger(ScriptController.class.getName());
//
//	@RequestMapping(value = "/script/start", method = RequestMethod.GET)
//	public String greetingForm(Model model, Principal principal) {
//		LOGGER.info("/script/start");
//        String name = principal.getName();
//        
//        User user = userRepository.findByUid(name);
//        if(user!=null){
//        	model.addAttribute("domains", user.getDomains());
//        	model.addAttribute("name", user.getCn());
//        	return "user";
//        }else{
//        	
//        	return "user";
//        }
//
//	}
//
//	// @RequestMapping(value = "/domain", method = RequestMethod.GET)
//	// public String domain(Model model) {
//	// model.addAttribute("domains",domainRepository.findAll());
//	// model.addAttribute("users",userRepository.findAll());
//	// return "domain_list";
//	// }
//
//	// @RequestMapping(value = "/domain/{domainName}", method =
//	// RequestMethod.GET)
//	// public String showDomain(@PathVariable String domainName, Model model) {
//	// model.addAttribute("domain",domainRepository.findBySubject(domainName));
//	// return "show_domain";
//	// }
//
//	// @RequestMapping(value = "/script/start", method = RequestMethod.GET)
//	// public String showDomain() {
//	//
//	// return "show_domain";
//	// }
//
//	// @RequestMapping(value = "/userList", method = RequestMethod.GET)
//	// public String userList(Model model, Principal principal) {
//	// LOGGER.info(model.toString());
//	// LOGGER.info(principal.toString());
//	// List<User> users = UsersUtils.getUsers();
//	// String name = principal.getName();
//	// List<Domain> domains = Domains.getDomains(name);
//	// model.addAttribute("users", users);
//	// if (Domains.containsUser(principal.getName())) {
//	// model.addAttribute("name", principal.getName());
//	// } else {
//	// model.addAttribute("name", "Anonymus");
//	// }
//	//
//	// LOGGER.info(model.toString());
//	// return "userList";
//	// }
//
//}
