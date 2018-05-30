package com.dao;

import java.util.List;

import com.model.Enfermedad;

public interface EnfermedadesDao {

	public Enfermedad find(Enfermedad enfermedad);

	public Enfermedad insertar(Enfermedad enfermedad);

	public List<Enfermedad> getAll();
}
