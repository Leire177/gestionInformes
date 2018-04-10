package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/inicio")
public class ContactController {

	@RequestMapping("/maint")
	public ModelAndView showContacts() {
		return new ModelAndView("inicio");
	}
}