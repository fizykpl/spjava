/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.controllers;

import java.security.Principal;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorViewController {

	private static final Logger LOGGER = Logger
			.getLogger(ErrorViewController.class.getName());

	@RequestMapping(value = "/permissionDenied", method = RequestMethod.GET)
	public String permissionDenied(Model model, Principal principal) {

		return "permissionDenied";

	}

}
