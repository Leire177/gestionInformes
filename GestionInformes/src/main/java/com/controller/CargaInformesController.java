package com.controller;

import java.io.IOException;

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
import com.service.CargaInformesService;

@Controller
@SessionAttributes
@RequestMapping("/cargaInformes")
public class CargaInformesController {
	@Autowired()
	private CargaInformesService cargaInformesService;

	@RequestMapping("/maint")
	public ModelAndView maint() {
		return new ModelAndView("cargaInformes");
	}

	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public ModelAndView uploadFiles(@RequestParam("ficheros") MultipartFile[] files, Model model, Hospital hospital,
			MultipartRequest request) throws IOException {
		this.cargaInformesService.procesarFicheros(files, hospital);
		return new ModelAndView("cargaInformes");
	}
}