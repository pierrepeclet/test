package fr.cursusSopra.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.cursusSopra.tech.PostgresConnection;

public class Critique {
	
	private int idcritique;
	private int idbar;
	private String comm;
	private Date datecomm;
	
	
	public Critique() {}
	
	// Ajoute une critique et retoure le numéro de la critique
	public int AddCritique(List<Integer> Listnotes, String commentaire, int idbarfromAction){
		Connection cnx = PostgresConnection.GetConnexion();
		String queryAddCrit = "INSERT INTO critiques (idbar,comm,datecomm ) VALUES (?,?, NOW() ) RETURNING idcritique";
		PreparedStatement psAddCrit;
		try {
			psAddCrit = cnx.prepareStatement(queryAddCrit);
			psAddCrit.setInt(1, idbarfromAction);
			psAddCrit.setString(2, commentaire);
			
			ResultSet rs = psAddCrit.executeQuery();
			rs.next();

			int result=0;
			Evaluation evalTest=new Evaluation();
			//evalTest.AddEval(6, 2, 4);
			
			for(int i=0; i<Listnotes.size();i++) {
				 evalTest.AddEval(rs.getInt("idcritique"), i+1, Listnotes.get(i));
			}
			
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public int getIdcritique() {
		return idcritique;
	}

	public void setIdcritique(int idcritique) {
		this.idcritique = idcritique;
	}

	public int getIdbar() {
		return idbar;
	}

	public void setIdbar(int idbar) {
		this.idbar = idbar;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public Date getDatecomm() {
		return datecomm;
	}

	public void setDatecomm(Date datecomm) {
		this.datecomm = datecomm;
	}

	

}
