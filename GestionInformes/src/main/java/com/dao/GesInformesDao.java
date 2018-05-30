package com.dao;

import java.util.List;

import com.model.Informe;

public interface GesInformesDao {
	public List<Informe> getAll(Informe informe);

	public List<Informe> getAllAvanzado(Informe informe);
}
