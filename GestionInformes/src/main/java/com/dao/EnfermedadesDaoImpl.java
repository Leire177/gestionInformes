package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Enfermedad;

@Repository()
@Transactional()
public class EnfermedadesDaoImpl implements EnfermedadesDao {
	private JdbcTemplate jdbcTemplate;
	// Locale locale = LocaleContextHolder.getLocale();

	/*
	 * ROW_MAPPERS
	 */

	private RowMapper<Enfermedad> rwMap = new RowMapper<Enfermedad>() {
		public Enfermedad mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Enfermedad bean = new Enfermedad();
			bean.setId(resultSet.getInt("ID"));
			bean.setDescripcion(resultSet.getString("DESCRIPCION"));
			return bean;
		}
	};

	protected String getPK() {
		return "ID";
	}

	protected String getSelect() {
		StringBuilder selectEnfermedad = new StringBuilder();
		selectEnfermedad.append(" SELECT T1.ID  ");
		selectEnfermedad.append(", T1.DESCRIPCION ");
		return selectEnfermedad.toString();

	}

	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM ENFERMEDADES T1 ");
		return from.toString();
	}

	protected String getWherePK(Enfermedad enfermedad, List<Object> params) {
		params.add(enfermedad.getId());
		StringBuilder where = new StringBuilder();
		where.append(" WHERE T1.ID = ?");
		return where.toString();
	}

	private Map<String, ?> getWhereMap(Enfermedad enfermedad) {

		StringBuilder where = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		if (enfermedad != null && enfermedad.getId() != null) {
			where.append(" AND T1.ID = ?");
			params.add(enfermedad.getId());
		}
		if (enfermedad != null && enfermedad.getDescripcion() != null) {
			where.append(" AND T1.DESCRIPCION = ?");
			params.add(enfermedad.getDescripcion());
		}
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return mapWhere;
	}

	private Map<String, ?> getWhereLikeMap(Enfermedad enfermedad, Boolean startsWith) {

		StringBuilder where = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		if (enfermedad != null && enfermedad.getId() != null) {
			where.append(" AND T1.ID = ?");
			params.add(enfermedad.getId());
		}
		if (enfermedad != null && enfermedad.getDescripcion() != null) {
			where.append(" AND UPPER(T1.DESCRIPCION) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(enfermedad.getDescripcion().toUpperCase() + "%");
			} else {
				params.add("%" + enfermedad.getDescripcion().toUpperCase() + "%");
			}
			where.append(" AND T1.DESCRIPCION IS NOT NULL");
		}
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return mapWhere;
	}

	public Enfermedad find(Enfermedad enfermedad) {
		StringBuilder sql = new StringBuilder(this.getSelect());
		sql.append(this.getFrom());

		Map<String, ?> mapaWhere = this.getWhereMap(enfermedad);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		sql.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		List<Enfermedad> lista = this.jdbcTemplate.query(sql.toString(), this.rwMap, params.toArray());
		if (lista != null & !lista.isEmpty()) {
			return DataAccessUtils.uniqueResult(lista);
		} else {
			return null;
		}
	}

	public Enfermedad insertar(Enfermedad enfermedad) {
		String query = "INSERT INTO ENFERMEDADES (DESCRIPCION) VALUES (?)";
		this.jdbcTemplate.update(query, enfermedad.getDescripcion().toUpperCase());
		return enfermedad;
	}

	@Resource()
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
