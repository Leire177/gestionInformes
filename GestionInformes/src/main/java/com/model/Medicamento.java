package com.model;

public class Medicamento {
	private Integer id;
	private String descripcion;
	private String idEnInforme;

	public Medicamento() {

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

}
