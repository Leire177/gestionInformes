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

import com.model.Medicamento;

@Repository()
@Transactional()
public class MedicamentosDaoImpl implements MedicamentosDao {
	private JdbcTemplate jdbcTemplate;
	// Locale locale = LocaleContextHolder.getLocale();

	private RowMapper<Medicamento> rwMap = new RowMapper<Medicamento>() {
		public Medicamento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Medicamento bean = new Medicamento();
			bean.setId(resultSet.getInt("ID"));
			bean.setDescripcion(resultSet.getString("DESCRIPCION"));
			return bean;
		}
	};

	protected String getPK() {
		return "ID";
	}

	protected String getSelect() {
		StringBuilder selectMedicamento = new StringBuilder();
		selectMedicamento.append(" SELECT T1.ID  ");
		selectMedicamento.append(", T1.DESCRIPCION ");
		return selectMedicamento.toString();

	}

	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM MEDICAMENTOS T1 ");
		return from.toString();
	}

	protected String getWherePK(Medicamento medicamento, List<Object> params) {
		params.add(medicamento.getId());
		StringBuilder where = new StringBuilder();
		where.append(" WHERE T1.ID = ?");
		return where.toString();
	}

	private Map<String, ?> getWhereMap(Medicamento medicamento) {

		StringBuilder where = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		if (medicamento != null && medicamento.getId() != null) {
			where.append(" AND T1.ID = ?");
			params.add(medicamento.getId());
		}
		if (medicamento != null && medicamento.getDescripcion() != null) {
			where.append(" AND T1.DESCRIPCION = ?");
			params.add(medicamento.getDescripcion());
		}
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return mapWhere;
	}

	private Map<String, ?> getWhereLikeMap(Medicamento medicamento, Boolean startsWith) {

		StringBuilder where = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		if (medicamento != null && medicamento.getId() != null) {
			where.append(" AND T1.ID = ?");
			params.add(medicamento.getId());
		}
		if (medicamento != null && medicamento.getDescripcion() != null) {
			where.append(" AND UPPER(T1.DESCRIPCION) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(medicamento.getDescripcion().toUpperCase() + "%");
			} else {
				params.add("%" + medicamento.getDescripcion().toUpperCase() + "%");
			}
			where.append(" AND T1.DESCRIPCION IS NOT NULL");
		}
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return mapWhere;
	}

	public Medicamento find(Medicamento medicamento) {
		StringBuilder sql = new StringBuilder(this.getSelect());
		sql.append(this.getFrom());

		Map<String, ?> mapaWhere = this.getWhereMap(medicamento);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		sql.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		List<Medicamento> lista = this.jdbcTemplate.query(sql.toString(), this.rwMap, params.toArray());
		if (lista != null & !lista.isEmpty()) {
			return DataAccessUtils.uniqueResult(lista);
		} else {
			return null;
		}
	}

	public List<Medicamento> getAll() {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(this.getSelect());
		sql.append(this.getFrom());
		sql.append(" WHERE 1 = 1 ");
		sql.append(" ORDER BY DESCRIPCION ASC ");
		return this.jdbcTemplate.query(sql.toString(), this.rwMap, params.toArray());
	}

	public Medicamento insertar(Medicamento medicamento) {
		String query = "INSERT INTO MEDICAMENTOS (DESCRIPCION) VALUES (?)";
		this.jdbcTemplate.update(query, medicamento.getDescripcion().toUpperCase());
		return medicamento;
	}

	@Resource()
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
