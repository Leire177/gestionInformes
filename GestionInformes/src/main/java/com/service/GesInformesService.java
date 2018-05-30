package com.service;

import java.util.List;

import com.model.Informe;

public interface GesInformesService {

	public List<Informe> buscarFiltroBasico(Informe informe);

	public List<Informe> buscarFiltroAvanzado(Informe informe);
}
