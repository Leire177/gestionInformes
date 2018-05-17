package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.web.multipart.MultipartFile;

import com.common.JsonDateDeserializer;
import com.common.JsonDateSerializer;

public class Informe implements Serializable {

	private static final long serialVersionUID = 1L;
	private MultipartFile contenidoANN;
	private MultipartFile contenidoTXT;
	private Integer id;
	private Date fecha;
	private Hospital hospital;
	private String nombreInforme;
	private Map<String, Enfermedad> listaEnfermedades;
	private Map<String, Medicamento> listaMedicamentos;
	private List<Enfermedad> enfermedadesEspeciales;
	private List<Enfermedad> listaEnfsFiltro;
	private List<Enfermedad> listaMedsFiltro;
	private String fechaDesde;
	private String fechaHasta;
	private Integer numInformes;

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
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
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
	public Map<String, Enfermedad> getListaEnfermedades() {
		if (this.listaEnfermedades == null) {
			this.listaEnfermedades = new HashMap<String, Enfermedad>();
		}
		return listaEnfermedades;
	}

	/**
	 * @param listaEnfermedades
	 *            the listaEnfermedades to set
	 */
	public void setListaEnfermedades(Map<String, Enfermedad> listaEnfermedades) {
		this.listaEnfermedades = listaEnfermedades;
	}

	/**
	 * @return the listaMedicamentos
	 */
	public Map<String, Medicamento> getListaMedicamentos() {
		if (this.listaMedicamentos == null) {
			this.listaMedicamentos = new HashMap<String, Medicamento>();
		}
		return listaMedicamentos;
	}

	/**
	 * @param listaMedicamentos
	 *            the listaMedicamentos to set
	 */
	public void setListaMedicamentos(Map<String, Medicamento> listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}

	/**
	 * @return the enfermedadesEspeciales
	 */
	public List<Enfermedad> getEnfermedadesEspeciales() {
		if (this.enfermedadesEspeciales == null) {
			this.enfermedadesEspeciales = new ArrayList<Enfermedad>();
		}
		return enfermedadesEspeciales;
	}

	/**
	 * @param enfermedadesEspeciales
	 *            the enfermedadesEspeciales to set
	 */
	public void setEnfermedadesEspeciales(List<Enfermedad> enfermedadesEspeciales) {
		this.enfermedadesEspeciales = enfermedadesEspeciales;
	}

	/**
	 * @return the fechaDesde
	 */
	// @JsonSerialize(using = JsonDateSerializer.class)
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
	// @JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	// @JsonSerialize(using = JsonDateSerializer.class)
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta
	 *            the fechaHasta to set
	 */
	// @JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @return the listaEnfsFiltro
	 */
	public List<Enfermedad> getListaEnfsFiltro() {
		return listaEnfsFiltro;
	}

	/**
	 * @param listaEnfsFiltro
	 *            the listaEnfsFiltro to set
	 */
	public void setListaEnfsFiltro(List<Enfermedad> listaEnfsFiltro) {
		this.listaEnfsFiltro = listaEnfsFiltro;
	}

	/**
	 * @return the listaMedsFiltro
	 */
	public List<Enfermedad> getListaMedsFiltro() {
		return listaMedsFiltro;
	}

	/**
	 * @param listaMedsFiltro
	 *            the listaMedsFiltro to set
	 */
	public void setListaMedsFiltro(List<Enfermedad> listaMedsFiltro) {
		this.listaMedsFiltro = listaMedsFiltro;
	}

	/**
	 * @return the numInformes
	 */
	public Integer getNumInformes() {
		return numInformes;
	}

	/**
	 * @param numInformes
	 *            the numInformes to set
	 */
	public void setNumInformes(Integer numInformes) {
		this.numInformes = numInformes;
	}

}
