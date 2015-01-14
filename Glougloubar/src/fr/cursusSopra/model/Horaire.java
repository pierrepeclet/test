package fr.cursusSopra.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.tech.PostgresConnection;

public class Horaire {
	private int idHoraire;
	private int idBar;
	private int idJour;
	private Time heureDebut;
	private Time heureFin;

	public Horaire(){}
	
	/**
	 * Récupère la liste des jours
	 * @return
	 */
	
	public static List<String> getLstJours(){
		List<String> lstJours = new ArrayList<String>();
		Connection cnx = PostgresConnection.GetConnexion();

		String queryJour = "SELECT jour FROM jours";
		
		Statement state;
		try {
			state = cnx.createStatement();
			ResultSet rsLstJours = state.executeQuery(queryJour);
			while(rsLstJours.next()){
				lstJours.add(rsLstJours.getString("jour"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
				
		return lstJours;
	}
	
	public int getIdHoraire() {
		return idHoraire;
	}
	
	public int getIdBar() {
		return idBar;
	}

	public void setIdBar(int idBar) {
		this.idBar = idBar;
	}

	public int getIdJour() {
		return idJour;
	}

	public void setIdJour(int idJour) {
		this.idJour = idJour;
	}
	
	public Time getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Time getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}
	
	/**
	 * Récupère le mot associé au numéro de jour
	 * @return le jour en toute lettre
	 */
	public String getNomJour(){
		Connection cnx = PostgresConnection.GetConnexion();
		
		//requete de selection de tous les bars
		String query = "SELECT jour FROM jours WHERE idjour = ?";
		
		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setInt(1, idJour);
			ResultSet rs = ps.executeQuery();
			
			//remplissage tant qu'on trouve des critères spéciaux
			if (rs.next())
			{
				return rs.getString("jour");
			}		
			rs.close();		
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return "Jour inconnu";
	}

	/**
	 * Récupère la liste de tous les horaires correspondant à un bar dans la base de données
	 * @return List<Horaire>
	 */
	public static List<Horaire> getListeHoraires(int idBar){
		List<Horaire> lstHoraireBar = new ArrayList<Horaire>();		
		Connection cnx = PostgresConnection.GetConnexion();
		
		//requete de selection de tous les bars
		String query = "SELECT idhoraire, idbar, idjour, heuredebut, heurefin FROM horaires WHERE idbar = ? ORDER BY idjour, heuredebut";
		
		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setInt(1, idBar);
			ResultSet rs = ps.executeQuery();
			
			//remplissage tant qu'on trouve des critères spéciaux
			while (rs.next())
			{
				Horaire newHoraire = new Horaire();
				newHoraire.idHoraire = rs.getInt("idhoraire");	
				newHoraire.idBar = rs.getInt("idbar");
				newHoraire.idJour = rs.getInt("idjour");
				newHoraire.heureDebut = rs.getTime("heuredebut");
				newHoraire.heureFin = rs.getTime("heurefin");
				lstHoraireBar.add(newHoraire);
			}		
			rs.close();		
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return lstHoraireBar;
	}
	
	/**
	 * Ajoute un horaire dans la base de données à partir des données connues dans
	 * l'objet ci-présent
	 * 
	 * @return 1 si la création s'est effectuée correctement, 0 sinon
	 */
	public int Create() {
		Connection cnx = PostgresConnection.GetConnexion();

		String query = "INSERT INTO horaires (idbar, idjour, heuredebut, heurefin) VALUES (?,?,?,?)";

		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setInt(1, idBar);
			ps.setInt(2, idJour);
			ps.setTime(3, heureDebut);
			ps.setTime(4, heureFin);

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Détruit l'horaire avec l'idhoraire de l'objet en cours dans la base de données
	 * 
	 * @return 1 si la destruction s'est effectuée correctement, 0 sinon
	 */
	public int Delete() {
		Connection cnx = PostgresConnection.GetConnexion();
		String query = "DELETE FROM horaires WHERE idhoraire = ?";

		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setInt(1, idHoraire);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
