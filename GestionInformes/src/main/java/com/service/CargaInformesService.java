package com.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.model.Hospital;
import com.model.Informe;

public interface CargaInformesService {
	public List<Informe> procesarFicheros(MultipartFile[] files, Hospital hospital);
}
