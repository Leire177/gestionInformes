package com.service;

import org.springframework.web.multipart.MultipartFile;

import com.model.Hospital;

public interface CargaInformesService {
	public int procesarFicheros(MultipartFile[] files, Hospital hospital);
}
