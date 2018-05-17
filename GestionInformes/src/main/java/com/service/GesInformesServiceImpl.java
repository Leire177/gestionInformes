package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.GesInformesDao;
import com.model.Informe;

@Service(value = "GesInformesService")
public class GesInformesServiceImpl implements GesInformesService {
	@Autowired()
	private GesInformesDao gesInformesDao;

	public List<Informe> buscarFiltroBasico(Informe informe) {
		return this.gesInformesDao.getAll(informe);
	}

}
