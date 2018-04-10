package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Hospital;

@Repository()
@Transactional()
public class HospitalDaoImpl implements HospitalDao {
	private JdbcTemplate jdbcTemplate;
	// Locale locale = LocaleContextHolder.getLocale();

	/*
	 * ROW_MAPPERS
	 */

	private RowMapper<Hospital> rwMap = new RowMapper<Hospital>() {
		public Hospital mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Hospital bean = new Hospital();
			bean.setId(resultSet.getInt("ID"));
			bean.setNombre(resultSet.getString("DESCRIPCION"));
			return bean;
		}
	};

	protected String getPK() {
		return "ID";
	}

	protected String getSelect() {
		StringBuilder selectFichero = new StringBuilder();
		selectFichero.append(" SELECT T1.ID  ");
		selectFichero.append(", T1.DESCRIPCION ");
		return selectFichero.toString();

	}

	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM HOSPITALES T1 ");
		return from.toString();
	}

	protected String getWherePK(Hospital hospital, List<Object> params) {
		params.add(hospital.getId());
		StringBuilder where = new StringBuilder();
		where.append(" WHERE T1.ID = ?");
		return where.toString();
	}

	protected String getWhere(Hospital hospital, List<Object> params) {
		StringBuilder where = new StringBuilder();
		if (hospital.getId() != null) {
			where.append(" AND T1.ID = ? ");
			params.add(hospital.getId());
		}
		if (hospital.getNombre() != null) {
			where.append(" AND T1.DESCRIPCION = ? ");
			params.add(hospital.getNombre());
		}
		return where.toString();

	}

	protected String getWhereLike(Hospital bean) {
		StringBuilder where = new StringBuilder();
		// where.append(X73bSqlUtils.generarWhereIgual("COD_FICHERO_H1",
		// bean.getCodigo(), params));

		return where.toString();
	}

	public List<Hospital> getAll() {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(this.getSelect());
		sql.append(this.getFrom());
		sql.append(" WHERE 1 = 1 ");
		sql.append(" ORDER BY DESCRIPCION ASC ");
		return this.jdbcTemplate.query(sql.toString(), this.rwMap, params.toArray());
	}

	@Resource()
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
