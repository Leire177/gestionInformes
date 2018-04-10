package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EnfermedadesDao;
import com.model.Enfermedad;

@Service(value = "EnfermedadesService")

public class EnfermedadesServiceImpl implements EnfermedadesService {

	@Autowired()
	private EnfermedadesDao enfermedadesDao;

	public Enfermedad find(Enfermedad enfermedad) {
		return this.enfermedadesDao.find(enfermedad);
	}

	public Enfermedad insertar(Enfermedad enfermedad) {
		return this.enfermedadesDao.insertar(enfermedad);
	}
}
