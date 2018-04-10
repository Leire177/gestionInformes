package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Informe implements Serializable {

	private static final long serialVersionUID = 1L;
	private MultipartFile contenidoANN;
	private MultipartFile contenidoTXT;
	private Integer id;
	private Date fecha;
	private Hospital hospital;
	private String nombreInforme;
	private List<Enfermedad> listaEnfermedades;
	private List<Medicamento> listaMedicamentos;

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
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the hospital
	 */
	public Hospital getHospital() {
		if (this.hospital == null) {
			this.hospital = new Hospital();
		}
		return hospital;
	}

	/**
	 * @param hospital
	 *            the hospital to set
	 */
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	/**
	 * @return the contenidoANN
	 */
	public MultipartFile getContenidoANN() {
		return contenidoANN;
	}

	/**
	 * @param contenidoANN
	 *            the contenidoANN to set
	 */
	public void setContenidoANN(MultipartFile contenidoANN) {
		this.contenidoANN = contenidoANN;
	}

	/**
	 * @return the contenidoTXT
	 */
	public MultipartFile getContenidoTXT() {
		return contenidoTXT;
	}

	/**
	 * @param contenidoTXT
	 *            the contenidoTXT to set
	 */
	public void setContenidoTXT(MultipartFile contenidoTXT) {
		this.contenidoTXT = contenidoTXT;
	}

	/**
	 * @return the nombreInforme
	 */
	public String getNombreInforme() {
		return nombreInforme;
	}

	/**
	 * @param nombreInforme
	 *            the nombreInforme to set
	 */
	public void setNombreInforme(String nombreInforme) {
		this.nombreInforme = nombreInforme;
	}

	/**
	 * @return the listaEnfermedades
	 */
	public List<Enfermedad> getListaEnfermedades() {
		if (this.listaEnfermedades == null) {
			this.listaEnfermedades = new ArrayList<Enfermedad>();
		}
		return listaEnfermedades;
	}

	/**
	 * @param listaEnfermedades
	 *            the listaEnfermedades to set
	 */
	public void setListaEnfermedades(List<Enfermedad> listaEnfermedades) {
		this.listaEnfermedades = listaEnfermedades;
	}

	/**
	 * @return the listaMedicamentos
	 */
	public List<Medicamento> getListaMedicamentos() {
		if (this.listaMedicamentos == null) {
			this.listaMedicamentos = new ArrayList<Medicamento>();
		}
		return listaMedicamentos;
	}

	/**
	 * @param listaMedicamentos
	 *            the listaMedicamentos to set
	 */
	public void setListaMedicamentos(List<Medicamento> listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}

}
