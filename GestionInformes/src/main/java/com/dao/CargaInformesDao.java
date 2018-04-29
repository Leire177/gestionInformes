package com.dao;

import com.model.Informe;

public interface CargaInformesDao {

	public Informe add(Informe informe);

	public int findCausadoPor(Integer idInforme, Integer idEnfermedad, Integer idMedicamento);

	public void addCausadoPor(Integer idInforme, Integer idEnfermedad, Integer idMedicamento);
}
