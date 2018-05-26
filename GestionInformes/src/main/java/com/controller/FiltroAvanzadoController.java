package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.service.GesInformesService;

@Controller
@SessionAttributes
@RequestMapping("/gesInformesAvanzado")
public class FiltroAvanzadoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FiltroAvanzadoController.class);

	@Autowired()
	private GesInformesService gesInformesService;

	@RequestMapping("/maint")
	public ModelAndView maint() {
		return new ModelAndView("gesInformesAvanzado");
	}

	// @RequestMapping(value = "/filtroAvanzado", method = RequestMethod.POST)
	// public @ResponseBody() List<Informe> filtroBasico(
	// // @RequestBody(required = false) List<Map<String, Object>> editar
	// @RequestBody() Informe informe) {
	// FiltroAvanzadoController.LOGGER.info("[filtroBasico]");
	// return this.gesInformesService.buscarFiltroBasico(informe);
	// }
}