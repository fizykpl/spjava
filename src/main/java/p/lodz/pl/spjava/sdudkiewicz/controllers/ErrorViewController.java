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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import p.lodz.pl.spjava.sdudkiewicz.models.Domain;
import p.lodz.pl.spjava.sdudkiewicz.models.User;
import p.lodz.pl.spjava.sdudkiewicz.repository.DomainRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.UserRepository;
import p.lodz.pl.spjava.sdudkiewicz.utils.UsersUtils;

/**
 *
 * @author sylwekabc06
 */
@Controller
public class ErrorViewController {


	private static final Logger LOGGER = Logger.getLogger(ErrorViewController.class
			.getName());

	@RequestMapping(value = "/errorDomain", method = RequestMethod.GET)
	public String greetingForm(Model model, Principal principal) {

		return "errorDomain";

	}


}
