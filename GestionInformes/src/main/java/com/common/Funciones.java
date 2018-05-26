package com.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Funciones {
	private static final Logger LOGGER = LoggerFactory.getLogger(Funciones.class);

	/**
	 * The method generarWhereIn.*
	 *
	 * @param campo
	 *            String*
	 * @param valores
	 *            List<?>*
	 * @param params
	 *            List<Object>*@return String
	 */

	public static String generarWhereIn(String campo, List<?> valores, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valores != null && !valores.isEmpty() && !(valores.size() == 1 && "[]".equals(valores.get(0)))) {
			where.append(" AND ").append(campo).append(" IN (");
			for (Integer i = 0; i < valores.size(); i++) {
				if (i > 0) {
					where.append(", ");
				}
				where.append("?");
			}
			where.append(") ");
			params.addAll(valores);
		}
		return where.toString();
	}

	/**
	 * The method generarWhereNotIn.
	 *
	 * @param campo
	 *            String
	 * @param valores
	 *            List<?>
	 * @param params
	 *            List<Object>
	 * @return String
	 */
	public static String generarWhereNotIn(String campo, List<?> valores, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valores != null && !valores.isEmpty()) {
			where.append(" AND ").append(campo).append(" NOT IN (");
			for (Integer i = 0; i < valores.size(); i++) {
				if (i > 0) {
					where.append(", ");
				}
				where.append("?");
			}
			where.append(") ");
			params.addAll(valores);
		}
		return where.toString();
	}

	public static String tratarFecha(String fecha) {
		String rdo = "";
		Date date = formatearFecha(fecha);
		rdo = obtFechaFormateada(date);
		return rdo;
	}

	public static Date formatearFecha(String fecha) {
		SimpleDateFormat formato;
		formato = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			if (fecha != null) {
				date = formato.parse(fecha);
			}
		} catch (ParseException e) {
			LOGGER.error("Funciones.formatearFecha", e);
		}
		return date;
	}

	public static Date formatearFecha(String fecha, SimpleDateFormat format) {
		SimpleDateFormat formato;
		formato = format;
		Date date = null;
		try {
			if (fecha != null) {
				date = formato.parse(fecha);
			}
		} catch (ParseException e) {
			LOGGER.error("Funciones.formatearFecha", e);
		}
		return date;
	}

	public static String obtFechaFormateada(Date fecha) {
		SimpleDateFormat formato;
		formato = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		if (fecha != null) {
			date = formato.format(fecha);
		}
		return date;
	}

	public static String obtFechaFormateada(Date fecha, SimpleDateFormat format) {
		SimpleDateFormat formato;
		formato = format;
		String date = "";
		if (fecha != null) {
			date = formato.format(fecha);
		}
		return date;
	}
}
