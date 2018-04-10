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

import com.model.InformeMedicamento;

@Repository()
@Transactional()
public class InformeMedicamentoDaoImpl implements InformeMedicamentoDao {
	private JdbcTemplate jdbcTemplate;
	// Locale locale = LocaleContextHolder.getLocale();

	/*
	 * ROW_MAPPERS
	 */

	private RowMapper<InformeMedicamento> rwMap = new RowMapper<InformeMedicamento>() {
		public InformeMedicamento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			InformeMedicamento bean = new InformeMedicamento();
			bean.setIdInforme(resultSet.getInt("IDINFORME"));
			bean.setIdMedicamento(resultSet.getInt("IDMEDICAMENTO"));
			return bean;
		}
	};

	protected String getPK() {
		return "IDINFORME,IDMEDICAMENTO";
	}

	protected String getSelect() {
		StringBuilder select = new StringBuilder();
		select.append(" SELECT T1.IDINFORME, T1.IDMEDICAMENTO  ");
		return select.toString();

	}

	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM MEDICAMENTO_INFORME T1 ");
		return from.toString();
	}

	protected String getWherePK(InformeMedicamento informe, List<Object> params) {
		params.add(informe.getIdInforme());
		params.add(informe.getIdMedicamento());
		StringBuilder where = new StringBuilder();
		where.append(" WHERE T1.IDINFORME = ? AND T1.IDMEDICAMENTO = ?");
		return where.toString();
	}

	public InformeMedicamento find(InformeMedicamento informeMedicamento) {
		StringBuilder sql = new StringBuilder(this.getSelect());
		sql.append(this.getFrom());

		Map<String, ?> mapaWhere = this.getWhereMap(informeMedicamento);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		sql.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		List<InformeMedicamento> lista = this.jdbcTemplate.query(sql.toString(), this.rwMap, params.toArray());
		if (lista != null & !lista.isEmpty()) {
			return DataAccessUtils.uniqueResult(lista);
		} else {
			return null;
		}
	}

	private Map<String, ?> getWhereMap(InformeMedicamento informeMedicamento) {

		StringBuilder where = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		if (informeMedicamento != null && informeMedicamento.getIdInforme() != null) {
			where.append(" AND T1.IDINFORME = ?");
			params.add(informeMedicamento.getIdInforme());
		}
		if (informeMedicamento != null && informeMedicamento.getIdMedicamento() != null) {
			where.append(" AND T1.IDMEDICAMENTO = ?");
			params.add(informeMedicamento.getIdMedicamento());
		}
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return mapWhere;
	}

	public void addRelacionEnfermedad(InformeMedicamento informeMedicamento) {
		String query = "INSERT INTO MEDICAMENTO_INFORME (IDINFORME,IDMEDICAMENTO) VALUES (?,?)";
		this.jdbcTemplate.update(query, informeMedicamento.getIdInforme(), informeMedicamento.getIdMedicamento());
	}

	@Resource()
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
