package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Enfermedad;
import com.service.EnfermedadesService;

@Controller()
@RequestMapping(value = "/enfermedades")
public class EnfermedadesController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EnfermedadesController.class);

	@Autowired()
	private EnfermedadesService enfermedadService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody() List<Enfermedad> getEnfermedades(Model model) {
		EnfermedadesController.LOGGER.info("[getAll]");
		List<Enfermedad> lista = this.enfermedadService.getAll();
		model.addAttribute("listaEnfermedades", lista);
		return lista;
	}
}
