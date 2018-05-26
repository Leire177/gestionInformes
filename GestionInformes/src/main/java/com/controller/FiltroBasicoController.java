package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.model.Informe;
import com.service.GesInformesService;

@Controller
@SessionAttributes
@RequestMapping("/gesInformesBasico")
public class FiltroBasicoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FiltroBasicoController.class);

	@Autowired()
	private GesInformesService gesInformesService;

	@RequestMapping("/maint")
	public ModelAndView maint() {
		return new ModelAndView("gesInformesBasico");
	}

	@RequestMapping(value = "/filtroBasico", method = RequestMethod.POST)
	public @ResponseBody() List<Informe> filtroBasico(@RequestBody() Informe informe) {
		FiltroBasicoController.LOGGER.info("[filtroBasico]");
		return this.gesInformesService.buscarFiltroBasico(informe);
	}
}