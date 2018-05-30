package com.dao;

import java.util.List;

import com.model.Medicamento;

public interface MedicamentosDao {

	public Medicamento find(Medicamento medicamento);

	public Medicamento insertar(Medicamento medicamento);

	public List<Medicamento> getAll();
}
