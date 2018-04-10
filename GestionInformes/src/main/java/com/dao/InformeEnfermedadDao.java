package com.dao;

import com.model.InformeEnfermedad;

public interface InformeEnfermedadDao {
	public InformeEnfermedad find(InformeEnfermedad informeEnfermedad);

	public void addRelacionEnfermedad(InformeEnfermedad informeEnfermedad);
}
