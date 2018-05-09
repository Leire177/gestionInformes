package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes
@RequestMapping("/gesInformesBasico")
public class FiltroBasicoController {
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(FiltroBasicoController.class);

	@Autowired()
	// private CargaInformesService cargaInformesService;

	@RequestMapping("/maint")
	public ModelAndView maint() {
		return new ModelAndView("gesInformesBasico");
	}
}