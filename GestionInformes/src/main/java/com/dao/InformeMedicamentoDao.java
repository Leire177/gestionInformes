package com.dao;

import com.model.InformeMedicamento;

public interface InformeMedicamentoDao {
	public void addRelacionEnfermedad(InformeMedicamento informeMedicamento);

	public InformeMedicamento find(InformeMedicamento informeMedicamento);
}
