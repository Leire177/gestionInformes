package com.model;

import java.util.ArrayList;
import java.util.List;

public class Enfermedad {
	private Integer id;
	private String descripcion;
	private String idEnInforme;
	private List<Medicamento> causadoPor;

	public Enfermedad() {

	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the idEnInforme
	 */
	public String getIdEnInforme() {
		return idEnInforme;
	}

	/**
	 * @param idEnInforme
	 *            the idEnInforme to set
	 */
	public void setIdEnInforme(String idEnInforme) {
		this.idEnInforme = idEnInforme;
	}

	/**
	 * @return the causadoPor
	 */
	public List<Medicamento> getCausadoPor() {
		if (this.causadoPor == null) {
			this.causadoPor = new ArrayList<Medicamento>();
		}
		return causadoPor;
	}

	/**
	 * @param causadoPor
	 *            the causadoPor to set
	 */
	public void setCausadoPor(List<Medicamento> causadoPor) {
		this.causadoPor = causadoPor;
	}

}
