package com.dao;

import com.model.Medicamento;

public interface MedicamentosDao {

	public Medicamento find(Medicamento medicamento);

	public Medicamento insertar(Medicamento medicamento);
}
