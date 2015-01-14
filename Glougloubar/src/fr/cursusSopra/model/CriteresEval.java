package fr.cursusSopra.model;

import java.sql.*;
import java.util.*;

import fr.cursusSopra.tech.PostgresConnection;

public class CriteresEval {

	private int idcriteval;
	private String libcourt;
	private String liblong;

	public CriteresEval() {
	}

	public static List<CriteresEval> lstCriteresEval() {
		List<CriteresEval> lst = new ArrayList<CriteresEval>();

		Connection cnx = PostgresConnection.GetConnexion();
		try {

			String queryCand = "SELECT idcriteval, libcourt, liblong FROM critereseval";
			PreparedStatement ps = cnx.prepareStatement(queryCand);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CriteresEval critEval = new CriteresEval();
				critEval.setIdcriteval(rs.getInt("idcriteval"));
				critEval.setLibcourt(rs.getString("libcourt"));
				critEval.setLiblong(rs.getString("liblong"));
				lst.add(critEval);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;

	}

	public String getLiblong() {
		return liblong;
	}

	public void setLiblong(String liblong) {
		this.liblong = liblong;
	}

	public String getLibcourt() {
		return libcourt;
	}

	public void setLibcourt(String libcourt) {
		this.libcourt = libcourt;
	}

	public int getIdcriteval() {
		return idcriteval;
	}

	public void setIdcriteval(int idcriteval) {
		this.idcriteval = idcriteval;
	}

}
