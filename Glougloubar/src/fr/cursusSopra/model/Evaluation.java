package fr.cursusSopra.model;

import java.sql.*;
import java.util.*;

import fr.cursusSopra.tech.PostgresConnection;

public class Evaluation {

	private int ideval;
	private int idcritique;
	private int idcriteval;
	private int note;
	private int nombreCriteres;
	private List<String> nomCritere;

	public int AddEval(int idcritiqueF, int idcritevalF, int noteF) {
		this.idcriteval = idcriteval;
		Connection cnx = PostgresConnection.GetConnexion();
		String queryAddEval = "INSERT INTO evaluations (idcritique,idcriteval,note ) VALUES (?,?, ? )";
		PreparedStatement psAddEval;
		try {
			psAddEval = cnx.prepareStatement(queryAddEval);
			psAddEval.setInt(1, idcritiqueF);
			psAddEval.setInt(2, idcritevalF);
			psAddEval.setInt(3, noteF);

			int result = psAddEval.executeUpdate();

			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	// Constructeur
	public Evaluation() {

	}
}
