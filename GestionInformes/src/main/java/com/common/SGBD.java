package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SGBD {
	Connection connection;
	private Statement sentencia;
	private ResultSet rs;
	private static SGBD miSGBD = null;

	private SGBD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Open connection
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "root");
			connection.setAutoCommit(true);
			sentencia = connection.createStatement();
			System.out.println("Conexion establecida");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * devuelve el atributo miSGBD
	 * 
	 * @return
	 */
	public static SGBD getSGBD() {
		if (miSGBD == null) {
			miSGBD = new SGBD();
		}
		return miSGBD;
	}

	/**
	 * metodo para realizar una insercion o actualizacion en la base de datos
	 * 
	 * @param strSQL
	 * @return
	 * @throws SQLException
	 */
	public int actualizar(String strSQL) throws SQLException {
		return sentencia.executeUpdate(strSQL);
	}

	/**
	 * M�todo para ejecutar un seleccion en la base de datos
	 * 
	 * @param strSQL
	 * @return
	 * @throws SQLException
	 */
	public ResultSet ejecutarQuery(String strSQL) throws SQLException {
		return this.sentencia.executeQuery(strSQL);
	}

	/**
	 * anade un usuario a la base de datos
	 * 
	 * @param pUsuario
	 * @param pContrasena
	 * @return
	 */
	public int ingresarJugador(String pNombre, String pContrasena) {
		int estado = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(
					"insert into jugador(nombre,contrasena) values('" + pNombre + "','" + pContrasena + "');");
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			estado = -1;
		}
		// Devuelvo un numero para saber si ha sido correcto o no el ingreso
		return estado;
	}

	/**
	 * Comprueba la puntuacion maxima del usuario pasado en el ID pasado.
	 * 
	 * @param pID
	 * @param pNombre
	 * @return la puntuacion maxima del usuario pasado en el ID pasado
	 */
	public int getPuntuacionMaxPersonal(int pID, String pNombre) {
		int aDevolver = 0;
		try {

			String sentenciaSQL = "SELECT puntos FROM puntuacion WHERE idTablero='" + pID + "' AND nombreJugador='"
					+ pNombre + "'ORDER BY puntos DESC LIMIT 1";
			rs = sentencia.executeQuery(sentenciaSQL);
			while (rs.next()) {
				aDevolver = this.rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return aDevolver;
	}

	/**
	 * recoge el ranking las 10 mejores puntuaciones de ese usuario
	 * 
	 * @return
	 */
	public String getRankingUsuario(String pNombre) {
		String aDevolver = "10 MEJORES PUNTUACIONES DE: " + pNombre;
		if (this.existePuntuacionesJugador(pNombre)) {
			try {
				String sentenciaSQL = "SELECT nivel,idTablero,puntos FROM puntuacion WHERE nombreJugador='" + pNombre
						+ "' ORDER BY puntos DESC LIMIT 10";
				rs = sentencia.executeQuery(sentenciaSQL);
				while (rs.next()) {
					aDevolver = aDevolver + "\n Nivel: " + this.rs.getString(1) + "   Identificador Tablero: "
							+ this.rs.getString(2) + "   Puntos: " + this.rs.getString(3) + "";
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		} else {
			aDevolver = aDevolver + "\n Este jugador todavia no ha ganado ninguna partida :( ";
		}
		return aDevolver;
	}

	/**
	 * comprueba si el usuario tiene puntuaciones guardadas
	 * 
	 * @param pNombre
	 * @return
	 */
	public boolean existePuntuacionesJugador(String pNombre) {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from puntuacion where nombreJugador='" + pNombre + "';");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 no tiene puntuaciones guardadas
		return (contador > 0);
	}

	/**
	 * comprueba si el tablero existe o no en la base de datos
	 * 
	 * @param idTablero
	 * @return
	 */
	public boolean existeTablero(int idTablero) {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from tablero where idTablero='" + idTablero + "';");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 no existe ese tablero
		return (contador > 0);
	}

	/**
	 * comprueba si hay puntuaciones de ese tablero
	 * 
	 * @param idTablero
	 * @return
	 */
	public boolean existePuntuacionesTablero(int idTablero) {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from puntuacion where idTablero='" + idTablero + "';");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 no tiene puntuaciones guardadas
		return (contador > 0);
	}

	/**
	 * recoge el ranking las 10 mejores puntuaciones de ese tablero
	 * 
	 * @param idTablero
	 * @return
	 */
	public String getRankingTablero(int idTablero) {
		String aDevolver = "10 MEJORES PUNTUACIONES DEL TABLERO: " + idTablero;
		if (this.existePuntuacionesTablero(idTablero)) {
			try {
				String sentenciaSQL = "SELECT nombreJugador,puntos FROM puntuacion WHERE idTablero='" + idTablero
						+ "' ORDER BY puntos DESC LIMIT 10";
				rs = sentencia.executeQuery(sentenciaSQL);
				while (rs.next()) {
					aDevolver = aDevolver + "\n Jugador: " + this.rs.getString(1) + "   Puntos: " + this.rs.getString(2)
							+ "";
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		} else {
			aDevolver = aDevolver + "\n No existen puntuaciones de este tablero :( ";
		}
		return aDevolver;
	}

	/**
	 * comprueba si hay puntuaciones de ese nivel
	 * 
	 * @param pNivel
	 * @return boolean
	 */
	public boolean existePuntuacionesNivel(int pNivel) {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from puntuacion where nivel='" + pNivel + "';");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 no tiene puntuaciones guardadas
		return (contador > 0);
	}

	/**
	 * recoge el ranking las 10 mejores puntuaciones de ese nivel
	 * 
	 * @param pNivel
	 * @return String
	 */
	public String getRankingNivel(int pNivel) {
		String aDevolver = "10 MEJORES PUNTUACIONES DEL NIVEL: " + pNivel;
		if (this.existePuntuacionesNivel(pNivel)) {
			try {
				String sentenciaSQL = "SELECT nombreJugador,puntos FROM puntuacion WHERE nivel='" + pNivel
						+ "' ORDER BY puntos DESC LIMIT 10";
				rs = sentencia.executeQuery(sentenciaSQL);
				while (rs.next()) {
					aDevolver = aDevolver + "\n Jugador: " + this.rs.getString(1) + "   Puntos: " + this.rs.getString(2)
							+ "";
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		} else {
			aDevolver = aDevolver + "\n No existen puntuaciones de este nivel :( ";
		}
		return aDevolver;
	}

	/**
	 * comprueba si hay una partida de ese jugador con ese nombre
	 * 
	 * @param pNombreJ
	 * @param pNombreP
	 * @return boolean
	 */
	public boolean existePartida(String pNombreJ, String pNombreP) {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from partida where nombreJugador='" + pNombreJ
					+ "' AND nombrePartida= '" + pNombreP + "';");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 no existe una partida con ese nombre de
		// ese jugador
		return (contador > 0);
	}

	/**
	 * Comprueba si en la BD hay jugadores
	 * 
	 * @return
	 */
	public boolean existenJugadores() {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from jugador;");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 no hay jugadores en la BD
		return (contador > 0);
	}

	/**
	 * obtiene los nombres de los jugadores
	 * 
	 * @return
	 */
	public ArrayList<String> getNombres() {
		ArrayList<String> aDevolver = new ArrayList<String>();
		if (this.existenJugadores()) {
			try {
				String sentenciaSQL = "SELECT nombre FROM jugador";
				rs = sentencia.executeQuery(sentenciaSQL);
				while (rs.next()) {
					aDevolver.add(this.rs.getString(1));
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return aDevolver;
	}

	/**
	 * Añade un reto
	 * 
	 * @param pRetador
	 * @param pRetado
	 * @param pPuntosRetador
	 * @param pIdTablero
	 * @param pFechaHora
	 * @return
	 */
	public int anadirReto(String pRetador, String pRetado, int pPuntosRetador, int pIdTablero, String pFechaHora) {
		int estado = 0;
		try {
			System.out.println("Anadiendo reto...");
			PreparedStatement statement = connection.prepareStatement(
					"insert into reto(retador, retado, puntosRetador, idTablero, fechaHora) values('" + pRetador + "','"
							+ pRetado + "','" + pPuntosRetador + "','" + pIdTablero + "','" + pFechaHora + "');");
			statement.executeUpdate();
			System.out.println("Reto anadido correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			estado = -1;
		}
		// Devuelvo un numero para saber si ha sido correcto o no el ingreso
		return estado;
	}

	/**
	 * Elimina reto
	 * 
	 * @param pRetador
	 * @param pRetado
	 * @param pFechaHora
	 * @return
	 */
	public int eliminarReto(String pRetador, String pRetado, String pFechaHora) {
		int estado = 0;
		try {
			System.out.println("Eliminando reto...");
			PreparedStatement statement = connection.prepareStatement("DELETE FROM reto WHERE(retador = '" + pRetador
					+ "' AND retado = '" + pRetado + "' AND  fechaHora = '" + pFechaHora + "');");
			statement.executeUpdate();
			System.out.println("Reto eliminado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			estado = -1;
		}
		// Devuelvo un numero para saber si ha sido correcto o no el ingreso
		return estado;
	}

	/**
	 * Comprueba si existen retos para ese jugador
	 * 
	 * @param pNombre
	 * @return
	 */
	public boolean existeReto(String pNombre) {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from reto where retado='" + pNombre + "';");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 no el jugador no tiene retos
		return (contador > 0);
	}

	/**
	 * 
	 * @param pId
	 * @return
	 */
	public int getTableroNivelPorId(int pId) {
		int aDevolver = -1;
		try {
			String sentenciaSQL = "SELECT nivel FROM tablero WHERE idTablero= '" + pId + "' ;";
			rs = sentencia.executeQuery(sentenciaSQL);
			while (rs.next()) {
				aDevolver = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return aDevolver;
	}

	/**
	 * borra un usuario de la base de datos
	 * 
	 * @param pUsuario
	 * @return
	 */
	public void borrarJugador(String pNombre) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("delete from jugador where nombre='" + pNombre + "';");
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * obtiene el idTablero del ultimo que se anadio a la base de datos
	 */

	public int getIdTableroUltimoAnadido() {
		int idTablero = 0;
		try {
			rs = sentencia.executeQuery("Select MAX(idTablero) from tablero;");
			rs.next();
			idTablero = rs.getInt(1);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return idTablero;
	}

	/**
	 * Obtiene un tablero aleatorio del nivel seleccionado
	 * 
	 * @param pNivel
	 * @return
	 */
	public String[] getTableroNivel(int pNivel) {
		String[] aDevolver = new String[2];
		try {
			String sentenciaSQL = "SELECT listaMinas, idTablero FROM tablero WHERE nivel=" + pNivel
					+ " ORDER BY RAND() LIMIT 1";
			rs = sentencia.executeQuery(sentenciaSQL);
			while (rs.next()) {
				aDevolver[0] = rs.getString(1);
				aDevolver[1] = rs.getString(2);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return aDevolver;
	}

	/**
	 * anade una partida a la base de datos
	 * 
	 * @param pNombreP
	 * @param pNombreJ
	 * @param pListaMinas
	 * @param pIdTablero
	 * @param pListaMarcadas
	 * @param pListaDestapadas
	 * @param pSegundos
	 * @param pSegundosFaltan
	 * @return
	 */
	public boolean guardarPartida(String pNombreP, String pNombreJ, int pIdTablero, String pListaMarcadas,
			String pListaDestapadas, int pSegundos, int pSegundosFaltan, int pNivel) {
		boolean guardado = true;
		try {
			PreparedStatement statement = connection.prepareStatement(
					"insert into partida(nombreJugador, nombrePartida,idTablero, listaMarcadas, listaDestapadas, segundos, segundosFaltan, nivel) values('"
							+ pNombreJ + "','" + pNombreP + "','" + pIdTablero + "','" + pListaMarcadas + "','"
							+ pListaDestapadas + "','" + pSegundos + "','" + pSegundosFaltan + "','" + pNivel + "');");
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		// Devuelvo un numero para saber si ha sido correcto o no el ingreso
		return guardado;
	}

	public boolean existenPartidasGuardadas(String pNombreJ) {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from partida where nombreJugador='" + pNombreJ + "';");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 el jugador no tiene partidas guardadas
		return (contador > 0);
	}

}