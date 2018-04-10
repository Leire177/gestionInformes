package com.dao;

import com.model.Enfermedad;

public interface EnfermedadesDao {

	public Enfermedad find(Enfermedad enfermedad);

	public Enfermedad insertar(Enfermedad enfermedad);
}
