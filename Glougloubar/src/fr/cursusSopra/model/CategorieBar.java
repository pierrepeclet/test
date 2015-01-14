package fr.cursusSopra.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.tech.PostgresConnection;

public class CategorieBar {
	private int idCategorie;
	private String categorieBar;

	public CategorieBar(){}

	public int getIdCategorie() {
		return idCategorie;
	}

	public String getCategorieBar() {
		return categorieBar;
	}

	/**
	 * R�cup�re la liste de toutes les cat�gories de bars dans la base de donn�es et cr�e les objets CategorieBar correspondant
	 * @return List<Bar>
	 */
	public static List<CategorieBar> getListeCategoriesBar(){
		List<CategorieBar> lstCategorieBar = new ArrayList<CategorieBar>();		
		Connection cnx = PostgresConnection.GetConnexion();
		
		//requete de selection de tous les bars
		String query = "SELECT idcategorie, categoriebar FROM categories";
		
		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			//remplissage tant qu'on trouve des cat�gories
			while (rs.next())
			{
				CategorieBar newCategorieBar = new CategorieBar();
				newCategorieBar.idCategorie = rs.getInt("idcategorie");
				newCategorieBar.categorieBar = rs.getString("categoriebar");		
				lstCategorieBar.add(newCategorieBar);
			}		
			rs.close();		
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return lstCategorieBar;
	}
}