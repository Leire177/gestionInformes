package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MedicamentosDao;
import com.model.Medicamento;

@Service(value = "MedicamentosService")

public class MedicamentosServiceImpl implements MedicamentosService {

	@Autowired()
	private MedicamentosDao medicamentosDao;

	public Medicamento find(Medicamento medicamento) {
		return this.medicamentosDao.find(medicamento);
	}

	public Medicamento insertar(Medicamento medicamento) {
		return this.medicamentosDao.insertar(medicamento);
	}

	public List<Medicamento> getAll() {
		return this.medicamentosDao.getAll();
	}
}
