package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.Funciones;
import com.model.Informe;

@Repository()
@Transactional()
public class GesInformesDaoImpl implements GesInformesDao {
	private JdbcTemplate jdbcTemplate;

	/*
	 * ROW_MAPPERS
	 */

	private RowMapper<Informe> rwMap = new RowMapper<Informe>() {
		public Informe mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Informe bean = new Informe();
			bean.setNumInformes(resultSet.getInt("NUMINFORMES"));
			bean.setFecha(resultSet.getDate("FECHA"));
			return bean;
		}
	};

	protected String getPK() {
		return "ID";
	}

	protected String getSelect() {
		StringBuilder selectFichero = new StringBuilder();
		selectFichero.append(" SELECT COUNT(FECHA) AS NUMINFORMES, FECHA ");
		return selectFichero.toString();

	}

	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM INFORMES T1 JOIN HOSPITALES T2 ON T1.IDHOSPITAL = T2.ID");
		from.append(" JOIN ENFERMEDAD_INFORME T3 ON T1.ID = T3.IDINFORME");
		from.append(" JOIN ENFERMEDADES T5 ON T3.IDENFERMEDAD = T5.ID");
		from.append(" LEFT JOIN MEDICAMENTO_INFORME T4 ON T1.ID = T4.IDINFORME");
		from.append(" JOIN MEDICAMENTOS T6 ON T4.IDMEDICAMENTO = T6.ID");
		return from.toString();
	}

	protected String getWherePK(Informe informe, List<Object> params) {
		params.add(informe.getId());
		StringBuilder where = new StringBuilder();
		where.append(" WHERE T1.ID = ?");
		return where.toString();
	}

	protected Map<String, ?> getWhereMap(Informe informe) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder where = new StringBuilder();
		if (informe.getId() != null) {
			where.append(" AND T1.ID = ? ");
			params.add(informe.getId());
		}
		if (informe.getHospital().getId() != null) {
			where.append(" AND T2.ID = ? ");
			params.add(informe.getHospital().getId());
		}
		List<String> listaEnfs = new ArrayList<String>();
		List<String> listaMeds = new ArrayList<String>();
		if (informe.getListaEnfsFiltro() != null && !informe.getListaEnfsFiltro().isEmpty()) {
			for (int i = 0; i < informe.getListaEnfsFiltro().size(); i++) {
				if (!"".equals(informe.getListaEnfsFiltro().get(i).getDescripcion())) {
					listaEnfs.add(informe.getListaEnfsFiltro().get(i).getDescripcion());
				}
			}
		}
		if (informe.getListaMedsFiltro() != null && !informe.getListaMedsFiltro().isEmpty()) {
			for (int i = 0; i < informe.getListaMedsFiltro().size(); i++) {
				if (!"".equals(informe.getListaMedsFiltro().get(i).getDescripcion())) {
					listaMeds.add(informe.getListaMedsFiltro().get(i).getDescripcion());
				}
			}
		}
		where.append(Funciones.generarWhereIn("T5.DESCRIPCION", listaEnfs, params));
		where.append(Funciones.generarWhereIn("T6.DESCRIPCION", listaMeds, params));

		if (informe.getFechaDesde() != null && !"".equals(informe.getFechaDesde())) {
			where.append("AND T1.FECHA > ?");
			params.add(Funciones.tratarFecha(informe.getFechaDesde()));
		}
		if (informe.getFechaHasta() != null && !"".equals(informe.getFechaHasta())) {
			where.append(" AND T1.FECHA < ?");
			params.add(Funciones.tratarFecha(informe.getFechaHasta()));
		}
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return mapWhere;

	}

	public List<Informe> getAll(Informe informe) {
		StringBuilder sql = new StringBuilder(this.getSelect());
		sql.append(this.getFrom());

		Map<String, ?> mapaWhere = this.getWhereMap(informe);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		sql.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");
		sql.append(" GROUP BY FECHA  ");
		sql.append(" ORDER BY FECHA ASC ");
		return this.jdbcTemplate.query(sql.toString(), this.rwMap, params.toArray());
	}

	@Resource()
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
