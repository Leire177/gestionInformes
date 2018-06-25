package com.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.Constantes;
import com.dao.CargaInformesDao;
import com.dao.EnfermedadesDao;
import com.dao.InformeEnfermedadDao;
import com.dao.InformeMedicamentoDao;
import com.dao.MedicamentosDao;
import com.model.Enfermedad;
import com.model.Hospital;
import com.model.Informe;
import com.model.InformeEnfermedad;
import com.model.InformeMedicamento;
import com.model.Medicamento;

@Service(value = "CargaInformesService")
public class CargaInformesServiceImpl implements CargaInformesService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CargaInformesServiceImpl.class);
	@Autowired()
	private CargaInformesDao cargaInformesDao;
	@Autowired()
	private EnfermedadesDao enfermedadesDao;
	@Autowired()
	private MedicamentosDao medicamentosDao;
	@Autowired()
	private InformeMedicamentoDao informeMedicamentoDao;
	@Autowired()
	private InformeEnfermedadDao informeEnfermedadDao;

	public List<Informe> procesarFicheros(MultipartFile[] files, Hospital hospital) {

		/**
		 * MIRAR SI ES ANN O TXT INSERTAR EN DOS LISTAS DISTINTAS ANN Y TXT RECORRER
		 * LISTA ANN Y BUSCAR SI EL MISMO NOMBRE DE ARCHIVO SIN LA EXTENSION EXISTE EN
		 * LA LISTA TXT SI NO EXISTE SE A�ADE A LISTA FICHEROS ERRONEOS SI EXISTE SE
		 * A�ADE A LISTA FICHEROS CORRECTOS PARA PROCESARLOS (ANN) PARA PROCESAR LOS
		 * CORRECTOS SE PROCESA LA FECHA DEL ANN Y GUARDA EN BEAN INFORME DESPUES DE
		 * PROCESA LA INFORMACION DEL ANN PARA GUARDARLA EN LA BD SE INSERTA EL INFORME
		 * TAMBIEN CON EL CONTENIDO TXT Y ANN SE DEVUELVE LA LISTA DE ERRONEOS PARA
		 * MOSTRARLO EN PANTALLA
		 */

		List<MultipartFile> informesANN = new ArrayList<MultipartFile>();
		List<MultipartFile> informesTXT = new ArrayList<MultipartFile>();
		List<Informe> informesCorrectos = new ArrayList<Informe>();
		List<Informe> informesErroneos = new ArrayList<Informe>();
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String fileName = file.getOriginalFilename();
			String extension = file.getOriginalFilename().substring(fileName.length() - 3, fileName.length());
			// MIRAR SI ES ANN O TXT
			if ("ANN".equals(extension) || "ann".equals(extension)) {
				informesANN.add(file);
			} else if ("TXT".equals(extension) || "txt".equals(extension)) {
				informesTXT.add(file);
			}
		}
		if (informesANN.isEmpty()) {
			for (MultipartFile multipartFileTXT : informesTXT) {
				Informe informe = new Informe();
				informe.setNombreInforme(multipartFileTXT.getOriginalFilename());
				informe.setHospital(hospital);
				informesErroneos.add(informe);
			}
		} else {

			for (MultipartFile multipartFileANN : informesANN) {
				boolean esIgual = false;
				Informe informe = new Informe();
				informe.setNombreInforme(multipartFileANN.getOriginalFilename());
				informe.setHospital(hospital);
				for (MultipartFile multipartFileTXT : informesTXT) {
					String nombreANN = multipartFileANN.getOriginalFilename().substring(0,
							multipartFileANN.getOriginalFilename().length() - 4);
					String nombreTXT = multipartFileTXT.getOriginalFilename().substring(0,
							multipartFileTXT.getOriginalFilename().length() - 4);
					if (nombreANN.equals(nombreTXT)) {
						esIgual = true;
						try {
							informe.setContenidoANN(multipartFileANN);
							informe.setContenidoTXT(multipartFileTXT);

							Object result = this.procesarFecha(nombreANN);
							if (result != null) {
								informe.setFecha((Date) result);
							} else {
								esIgual = false;
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
				if (esIgual) {
					informesCorrectos.add(informe);

					// TODO MIRAR SI EXISTE EL ID, Y SI EXISTE PONERLO COMO INCORRECTO
				} else {
					informesErroneos.add(informe);
				}
			}
		}
		List<Informe> resultado = this.procesar(informesCorrectos);
		for (int i = 0; i < resultado.size(); i++) {
			this.cargaInformesDao.add(resultado.get(i));
		}
		this.addRelacionEnfermedad(resultado);
		this.addRelacionMedicamento(resultado);
		this.procesarCausadoPor(resultado);

		return informesErroneos;
	}

	private Object procesarFecha(String nombreInforme) throws ParseException {
		String[] nombre = nombreInforme.split("_");

		String fecha = nombre[1];
		Object rdo = null;
		if (fecha != null && !"".equals(fecha)) {
			if (fecha.matches(Constantes.PATTERN_FECHA_GUION_CORTA)) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				rdo = sdf.parse(fecha);

			} else if (fecha.matches(Constantes.PATTERN_FECHA_GUION)) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -100);
				sdf.set2DigitYearStart(cal.getTime());
				rdo = sdf.parse(fecha);
			}
			if (fecha.matches(Constantes.PATTERN_FECHA_SEPARADOR_CORTA)) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				rdo = sdf.parse(fecha);

			} else if (fecha.matches(Constantes.PATTERN_FECHA_SEPARADOR)) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -100);
				sdf.set2DigitYearStart(cal.getTime());
				rdo = sdf.parse(fecha);
			}

		}
		return rdo;
	}

	private List<Informe> procesar(List<Informe> informes) {
		for (Informe informe : informes) {
			List<Enfermedad> enfermedadEspecial = new ArrayList<Enfermedad>();
			MultipartFile file = informe.getContenidoANN();
			try {
				InputStream inputStream = file.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				String line;

				while ((line = bufferedReader.readLine()) != null) {
					// PROCESAR FICHERO
					if (line.contains("Grp_Enfermedad:")) {
						// LINEA ESPECIAL
						String[] linea = line.split("\\t");

						if (linea[1].contains("Causada_por")) {
							String[] a = linea[1].split(" ");
							String e = a[0];
							Enfermedad enf = new Enfermedad();
							for (String string : a) {
								if (string.contains("Grp_Enfermedad:")) {
									String[] enfs = string.split("Grp_Enfermedad:");
									enf.setIdEnInforme(enfs[enfs.length - 1]);
								}
								if (string.contains("Causada_por")) {
									String[] meds = string.split(":");
									Medicamento med = new Medicamento();
									med.setIdEnInforme(meds[meds.length - 1]);
									enf.getCausadoPor().add(med);
								}
							}
							enfermedadEspecial.add(enf);
						}

					} else if (line.contains("Grp_Enfermedad")) {
						String[] linea = line.split("\\t");
						Enfermedad enf = new Enfermedad();
						enf.setIdEnInforme(linea[0]);
						enf.setDescripcion(linea[2]);
						Enfermedad enfermedad = this.procesarEnfermedad(enf);
						informe.getListaEnfermedades().put(linea[0], enfermedad);

					} else if (line.contains("Grp_Medicamento")) {
						String[] linea = line.split("\\t");
						Medicamento med = new Medicamento();
						med.setIdEnInforme(linea[0]);
						med.setDescripcion(linea[2]);
						Medicamento medicamento = this.procesarMedicamento(med);
						informe.getListaMedicamentos().put(linea[0], medicamento);
					}

				}

				Iterator iteraEnfEspecial = enfermedadEspecial.iterator();
				while (iteraEnfEspecial.hasNext()) {
					Enfermedad enf = (Enfermedad) iteraEnfEspecial.next();
					Enfermedad aux = informe.getListaEnfermedades().get(enf.getIdEnInforme());
					enf.setDescripcion(aux.getDescripcion());
					enf.setId(aux.getId());

					Iterator itera = enf.getCausadoPor().iterator();

					while (itera.hasNext()) {

						Medicamento med = (Medicamento) itera.next();
						System.out.println(enf.getDescripcion() + " " + enf.getIdEnInforme());
						if (informe.getListaMedicamentos().containsKey(med.getIdEnInforme())) {
							Medicamento medAux = informe.getListaMedicamentos().get(med.getIdEnInforme());
							med.setDescripcion(medAux.getDescripcion());
							med.setId(medAux.getId());

						} else {
							iteraEnfEspecial.remove();
							break;
						}
					}

				}
				informe.setEnfermedadesEspeciales(enfermedadEspecial);
				// INSERTAR RELACION ENFERMEDAD CAUSADA POR MEDICAMENTOS

			} catch (Exception e) {
				CargaInformesServiceImpl.LOGGER.info("[Error] " + e);
			}

		}

		return informes;
	}

	private void procesarCausadoPor(List<Informe> informes) {
		for (Informe inf : informes) {
			// for (Enfermedad enf : inf.getListaEnfermedades()) {
			for (Enfermedad enf : inf.getEnfermedadesEspeciales()) {
				for (Medicamento med : enf.getCausadoPor()) {
					int count = this.cargaInformesDao.findCausadoPor(inf.getId(), enf.getId(), med.getId());
					if (count == 0) {
						this.cargaInformesDao.addCausadoPor(inf.getId(), enf.getId(), med.getId());
					}

				}
			}
		}
	}

	private Enfermedad procesarEnfermedad(Enfermedad enfermedad) {
		Enfermedad aux = this.enfermedadesDao.find(enfermedad);
		if (aux == null) {
			// INSERTAR NUEVA ENFERMEDAD EN LA BASE DE DATOS
			System.out.println(enfermedad.getDescripcion());
			this.enfermedadesDao.insertar(enfermedad);
			// BUSCAR ENFERMEDAD INSERTADA PARA OBTENER EL ID
			return this.enfermedadesDao.find(enfermedad);
		} else {
			return aux;
		}

	}

	private Medicamento procesarMedicamento(Medicamento medicamento) {
		Medicamento aux = this.medicamentosDao.find(medicamento);
		if (aux == null) {
			// INSERTAR NUEVO MEDICAMENTO EN LA BASE DE DATOS
			System.out.println(medicamento.getDescripcion());
			this.medicamentosDao.insertar(medicamento);
			// BUSCAR MEDICAMENTO INSERTADO PARA OBTENER EL ID
			return this.medicamentosDao.find(medicamento);
		} else {
			return aux;
		}
	}

	private void addRelacionEnfermedad(List<Informe> informes) {
		for (Informe inf : informes) {
			// for (Enfermedad enf : inf.getListaEnfermedades()) {
			for (Map.Entry<String, Enfermedad> entry : inf.getListaEnfermedades().entrySet()) {
				InformeEnfermedad informe = new InformeEnfermedad();
				informe.setIdInforme(inf.getId());
				informe.setIdEnfermedad(entry.getValue().getId());
				InformeEnfermedad aux = this.informeEnfermedadDao.find(informe);
				if (aux == null) {
					this.informeEnfermedadDao.addRelacionEnfermedad(informe);
				}
			}
		}
	}

	private void addRelacionMedicamento(List<Informe> informes) {
		for (Informe inf : informes) {
			// for (Medicamento med : inf.getListaMedicamentos()) {
			for (Map.Entry<String, Medicamento> entry : inf.getListaMedicamentos().entrySet()) {
				InformeMedicamento informe = new InformeMedicamento();
				informe.setIdInforme(inf.getId());
				informe.setIdMedicamento(entry.getValue().getId());
				InformeMedicamento aux = this.informeMedicamentoDao.find(informe);
				if (aux == null) {
					this.informeMedicamentoDao.addRelacionEnfermedad(informe);
				}
			}
		}
	}

}
