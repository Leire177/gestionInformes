package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.HospitalDao;
import com.model.Hospital;

@Service(value = "HospitalService")

public class HospitalServiceImpl implements HospitalService {

	@Autowired()
	private HospitalDao hospitalDao;

	public List<Hospital> getAll() {
		return this.hospitalDao.getAll();
	}
}
