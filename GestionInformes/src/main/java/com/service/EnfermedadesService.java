package com.service;

import java.util.List;

import com.model.Enfermedad;

public interface EnfermedadesService {
	public Enfermedad find(Enfermedad enfermedad);

	public Enfermedad insertar(Enfermedad enfermedad);

	public List<Enfermedad> getAll();
}
