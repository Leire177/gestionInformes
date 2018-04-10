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

import com.model.InformeEnfermedad;

@Repository()
@Transactional()
public class InformeEnfermedadDaoImpl implements InformeEnfermedadDao {
	private JdbcTemplate jdbcTemplate;
	// Locale locale = LocaleContextHolder.getLocale();

	/*
	 * ROW_MAPPERS
	 */

	private RowMapper<InformeEnfermedad> rwMap = new RowMapper<InformeEnfermedad>() {
		public InformeEnfermedad mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			InformeEnfermedad bean = new InformeEnfermedad();
			bean.setIdInforme(resultSet.getInt("IDINFORME"));
			bean.setIdEnfermedad(resultSet.getInt("IDENFERMEDAD"));
			return bean;
		}
	};

	protected String getPK() {
		return "IDINFORME,IDENFERMEDAD";
	}

	protected String getSelect() {
		StringBuilder select = new StringBuilder();
		select.append(" SELECT T1.IDINFORME, T1.IDENFERMEDAD  ");
		return select.toString();

	}

	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM ENFERMEDAD_INFORME T1 ");
		return from.toString();
	}

	protected String getWherePK(InformeEnfermedad informe, List<Object> params) {
		params.add(informe.getIdInforme());
		params.add(informe.getIdEnfermedad());
		StringBuilder where = new StringBuilder();
		where.append(" WHERE T1.IDINFORME = ? AND T1.IDENFERMEDAD = ?");
		return where.toString();
	}

	public InformeEnfermedad find(InformeEnfermedad informeEnfermedad) {
		StringBuilder sql = new StringBuilder(this.getSelect());
		sql.append(this.getFrom());

		Map<String, ?> mapaWhere = this.getWhereMap(informeEnfermedad);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		sql.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		List<InformeEnfermedad> lista = this.jdbcTemplate.query(sql.toString(), this.rwMap, params.toArray());
		if (lista != null & !lista.isEmpty()) {
			return DataAccessUtils.uniqueResult(lista);
		} else {
			return null;
		}
	}

	private Map<String, ?> getWhereMap(InformeEnfermedad informeEnfermedad) {

		StringBuilder where = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		if (informeEnfermedad != null && informeEnfermedad.getIdInforme() != null) {
			where.append(" AND T1.IDINFORME = ?");
			params.add(informeEnfermedad.getIdInforme());
		}
		if (informeEnfermedad != null && informeEnfermedad.getIdEnfermedad() != null) {
			where.append(" AND T1.IDENFERMEDAD = ?");
			params.add(informeEnfermedad.getIdEnfermedad());
		}
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return mapWhere;
	}

	public void addRelacionEnfermedad(InformeEnfermedad informeEnfermedad) {
		String query = "INSERT INTO ENFERMEDAD_INFORME (IDINFORME,IDENFERMEDAD) VALUES (?,?)";
		this.jdbcTemplate.update(query, informeEnfermedad.getIdInforme(), informeEnfermedad.getIdEnfermedad());
	}

	@Resource()
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
