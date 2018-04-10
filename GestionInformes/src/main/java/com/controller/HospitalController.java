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

import com.model.Hospital;
import com.service.HospitalService;

@Controller()
@RequestMapping(value = "/hospitales")
public class HospitalController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HospitalController.class);

	@Autowired()
	private HospitalService hospitalService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody() List<Hospital> getHospitales(Model model) {
		HospitalController.LOGGER.info("[getAll]");
		List<Hospital> lista = this.hospitalService.getAll();
		model.addAttribute("listaHospitales", lista);
		return lista;
	}
}
