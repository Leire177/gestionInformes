package com.service;

import java.util.List;

import com.model.Medicamento;

public interface MedicamentosService {
	public Medicamento find(Medicamento medicamento);

	public Medicamento insertar(Medicamento medicamento);

	public List<Medicamento> getAll();
}
