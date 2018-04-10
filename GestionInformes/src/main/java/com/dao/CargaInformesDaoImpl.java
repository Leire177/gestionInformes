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
import com.model.Informe;

@Repository()
@Transactional()
public class CargaInformesDaoImpl implements CargaInformesDao {
	private JdbcTemplate jdbcTemplate;

	/*
	 * ROW_MAPPERS
	 */

	private RowMapper<Informe> rwMap = new RowMapper<Informe>() {
		public Informe mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Informe bean = new Informe();
			bean.setId(resultSet.getInt("ID"));
			bean.setFecha(resultSet.getDate("FECHA"));
			Hospital hospital = new Hospital();
			hospital.setId(resultSet.getInt("IDHOSPITAL"));
			// hospital.setNombre(resultSet.getString("DESCRIPCION"));
			bean.setHospital(hospital);
			return bean;
		}
	};

	protected String getPK() {
		return "ID";
	}

	protected String getSelect() {
		StringBuilder selectFichero = new StringBuilder();
		selectFichero.append(" SELECT T1.ID  ");
		selectFichero.append(", T1.IDHOSPITAL ");
		selectFichero.append(", T1.FECHA ");
		return selectFichero.toString();

	}

	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM INFORMES T1 ");
		return from.toString();
	}

	protected String getWherePK(Informe informe, List<Object> params) {
		params.add(informe.getId());
		StringBuilder where = new StringBuilder();
		where.append(" WHERE T1.ID = ?");
		return where.toString();
	}

	protected String getWhere(Informe informe, List<Object> params) {
		StringBuilder where = new StringBuilder();
		if (informe.getId() != null) {
			where.append(" AND T1.ID = ? ");
			params.add(informe.getId());
		}
		return where.toString();

	}

	public Informe add(Informe informe) {
		// List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("INSERT INTO INFORMES(IDHOSPITAL,FECHA,FICHERO_TXT,FICHERO_ANN)");
		sql.append(" VALUES (?,?,?,?) ");
		// params.add(informe.getHospital().getId());
		// params.add(informe.getFecha());
		// params.add(informe.getContenidoTXT());
		// params.add(informe.getContenidoANN());
		this.jdbcTemplate.update(sql.toString(), informe.getHospital().getId(), informe.getFecha(),
				informe.getContenidoTXT(), informe.getContenidoANN());

		int rdo = this.obtenerId();
		informe.setId(rdo);
		return informe;

	}

	public List<Informe> getAll() {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(this.getSelect());
		sql.append(this.getFrom());
		sql.append(" WHERE 1 = 1 ");
		sql.append(" ORDER BY FECHA ASC ");
		return this.jdbcTemplate.query(sql.toString(), this.rwMap, params.toArray());
	}

	private int obtenerId() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT MAX(ID) FROM INFORMES");
		return this.jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}

	@Resource()
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
