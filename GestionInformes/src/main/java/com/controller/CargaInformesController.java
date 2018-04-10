package com.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.model.Hospital;
import com.model.Informe;
import com.service.CargaInformesService;

@Controller
@SessionAttributes
@RequestMapping("/cargaInformes")
public class CargaInformesController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CargaInformesController.class);

	@Autowired()
	private CargaInformesService cargaInformesService;

	@RequestMapping("/maint")
	public ModelAndView maint() {
		return new ModelAndView("cargaInformes");
	}

	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public String uploadFiles(@RequestParam("ficheros") MultipartFile[] files, Model model, Hospital hospital,
			MultipartRequest request) throws IOException {
		CargaInformesController.LOGGER.info("[uploadFiles]");
		List<Informe> informesErroneos = this.cargaInformesService.procesarFicheros(files, hospital);
		// ModelAndView carga = new ModelAndView("cargaInformes");
		// carga.getModel().put("listaInformesErroneos", informesErroneos);
		model.addAttribute("listaErroneos", informesErroneos);
		model.addAttribute("feedback", informesErroneos.isEmpty());
		return "cargaInformes";
	}
}