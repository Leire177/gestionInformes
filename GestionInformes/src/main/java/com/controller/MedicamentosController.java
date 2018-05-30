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

import com.model.Medicamento;
import com.service.MedicamentosService;

@Controller()
@RequestMapping(value = "/medicamentos")
public class MedicamentosController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MedicamentosController.class);

	@Autowired()
	private MedicamentosService medicamentosService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody() List<Medicamento> getHospitales(Model model) {
		MedicamentosController.LOGGER.info("[getAll]");
		List<Medicamento> lista = this.medicamentosService.getAll();
		model.addAttribute("listaMedicamentos", lista);
		return lista;
	}
}
