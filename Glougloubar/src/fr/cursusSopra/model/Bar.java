//Antoine et Nicolas

package fr.cursusSopra.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.tech.PostgresConnection;

public class Bar {
	private int idBar;
	private String nom;
	private String numTel;
	private String site;
	private String description;
	private String lienImage;
	private String voie;
	private String ville;
	private String cp;
	private String shortDescription;
	private int[] tabCriteres;
	private int[] tabCategories;
	private Double globalNote;

	private List<Horaire> lstHoraires;

	public List<Horaire> getLstHoraires() {
		return lstHoraires;
	}
	
	public Double getGlobalNote() {
		globalNote = (double) 0;
		Connection cnx = PostgresConnection.GetConnexion();
		// requete de selection du bar d'idbar = id
		String query = "SELECT SUM (note) as total, count (*) as idx FROM evaluations INNER JOIN critiques USING (idcritique) WHERE idbar = ?";

		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setInt(1, idBar);
			ResultSet rs = ps.executeQuery();

			// remplissage de l'objet si le bar est trouvé
			if (rs.next()) {
				globalNote =  (double) rs.getInt("total")/ (double) rs.getInt("idx");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return globalNote;
	}

	public void setTabCategories(int[] tabCategories) {
		this.tabCategories = tabCategories;
	}

	public void setTabCriteres(int[] tabCriteres) {
		this.tabCriteres = tabCriteres;
	}

	public String getLienImage() {
		return ("content/images/" + String.valueOf(idBar) + ".jpg");
	}
	
	public String getShortDescription() {
		int len = this.description.length();
		int maxlength = 40;
		if (len < maxlength){
			return description;
		}else{
			int index = this.description.indexOf(" ", maxlength);
			return this.description.substring(0, index)+" [...]";
		}
	}

	public void setShortDescription(String shortDescription) {
		
	}

	public Bar() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public int getIdBar() {
		return idBar;
	}

	/**
	 * Constructeur de l'objet bar qui récupère le bar correspondant à l'id dans
	 * la table
	 * 
	 * @param id
	 */
	public Bar(int id) {
		Connection cnx = PostgresConnection.GetConnexion();
		idBar = id;
		lstHoraires = Horaire.getListeHoraires(idBar);
		// requete de selection du bar d'idbar = id
		String query = "SELECT nom, numtel, site, description FROM bars WHERE idbar = ?";

		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			// remplissage de l'objet si le bar est trouvé
			if (rs.next()) {
				fillBar(this, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Récupère la liste de tous les bars dans la base de données et crée les
	 * objets Bar correspondant
	 * 
	 * @return List<Bar>
	 */
	public static List<Bar> getListeDesBars() {
		List<Bar> lstBar = new ArrayList<Bar>();
		Connection cnx = PostgresConnection.GetConnexion();

		// requete de selection de tous les bars
		String query = "SELECT idbar, nom, numtel, site, description FROM bars ";
		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			// remplissage tant qu'on trouve des bars
			while (rs.next()) {
				Bar newBar = new Bar();
				newBar.idBar = rs.getInt("idbar");
				fillBar(newBar, rs);
				newBar.lstHoraires = Horaire.getListeHoraires(newBar.idBar);
				lstBar.add(newBar);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lstBar;
	}

	/**
	 * Remplit un bar avec les élements tirés de la base de données
	 * 
	 * @param thisBar
	 *            : le bar qu'on est en train de remplir
	 * @param rs
	 *            : résultat de la requête contenant les infos du bar
	 */
	private static void fillBar(Bar thisBar, ResultSet rs) {
		try {
			thisBar.nom = rs.getString("nom");
			thisBar.numTel = rs.getString("numtel");
			thisBar.site = rs.getString("site");
			thisBar.description = rs.getString("description");
		} catch (SQLException e) {
		}
	}

	/**
	 * Ajoute une adresse à un bar. Appelée dans la méthode Create()
	 * 
	 * @return
	 */
	public int CreateAdresse() {
		Connection cnx = PostgresConnection.GetConnexion();

		String queryCp = "SELECT cp FROM villes WHERE ville=?";
		String queryAdresse = "INSERT INTO adresses (voie, cp) VALUES (?, ?) RETURNING idadresse";

		try {
			// Récupération du code postal de la ville sélectionnée
			PreparedStatement psCp = cnx.prepareStatement(queryCp);
			psCp.setString(1, ville);
			ResultSet rsCp = psCp.executeQuery();
			rsCp.next();
			setCp(rsCp.getString("cp"));

			// Insertion de l'adresse dans la base de données
			PreparedStatement psAdresse = cnx.prepareStatement(queryAdresse);
			psAdresse.setString(1, voie);
			psAdresse.setString(2, cp);
			// Récupération de l'idadresse
			ResultSet rsAdresse = psAdresse.executeQuery();
			rsAdresse.next();
			return rsAdresse.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Ajoute des critères à un bar. Appelée dans la méthode Create()
	 * 
	 * @return
	 */
	public int CreateCriteres(int idBar){
		Connection cnx = PostgresConnection.GetConnexion();

		String queryCriteres = "INSERT INTO criteresbars (idbar, idcritere) VALUES (?, ?)";

		try {
			// Récupération du code postal de la ville sélectionnée
			PreparedStatement psCriteres = cnx.prepareStatement(queryCriteres);
			int i;
			int retour = 1;
			for (i=0;i<tabCriteres.length;i++){
				psCriteres.setInt(1, idBar);
				psCriteres.setInt(2,tabCriteres[i]);
				retour = psCriteres.executeUpdate();
			}
			return retour;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Ajoute des catégories à un bar. Appelée dans la méthode Create()
	 * 
	 * @return
	 */
	public int CreateCategories(int idBar){
		Connection cnx = PostgresConnection.GetConnexion();

		String queryCategories = "INSERT INTO categoriesbars (idbar, idcategorie) VALUES (?, ?)";

		try {
			// Récupération du code postal de la ville sélectionnée
			PreparedStatement psCategories = cnx.prepareStatement(queryCategories);
			int i;
			int retour = 1;
			for (i=0;i<tabCategories.length;i++){
				psCategories.setInt(1, idBar);
				psCategories.setInt(2,tabCategories[i]);
				retour = psCategories.executeUpdate();
			}
			return retour;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Ajoute un bar dans la base de données à partir des données connues dans
	 * l'objet ci-présent
	 * 
	 * @return 1 si la création s'est effectuée correctement, 0 sinon
	 */
	public int Create() {
		Connection cnx = PostgresConnection.GetConnexion();

		int idAdresse = CreateAdresse();

		String query = "INSERT INTO bars (nom,numtel,site,description, idadresse) VALUES (?,?,?,?,?) RETURNING idbar";

		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setString(1, nom);
			ps.setString(2, numTel);
			ps.setString(3, site);
			ps.setString(4, description);
			ps.setInt(5, idAdresse);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			//L'id du bar créé automatiquement est récupéré
			int idBar = rs.getInt(1);
			//On remplit la table "criteresbars"
			if(tabCriteres.length!=0){
				CreateCriteres(idBar);
			}
			//On remplit la table "categoriesbars"
			if(tabCategories.length!=0){
				CreateCategories(idBar);
			}
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Met à jour un bar dans la base de données à partir des données connues
	 * dans l'objet ci-présent
	 * 
	 * @return 1 si la modification s'est effectuée correctement, 0 sinon
	 */
	public int Update() {
		Connection cnx = PostgresConnection.GetConnexion();
		String query = "UPDATE bars SET " + "nom = ? ," + "numtel = ? ,"
				+ "site = ? ," + "description = ? ," + "WHERE idbar = ?";

		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setString(1, nom);
			ps.setString(2, numTel);
			ps.setString(3, site);
			ps.setString(4, description);
			ps.setInt(5, idBar);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Détruit le bar avec l'idbar de l'objet en cours dans la base de données
	 * 
	 * @return 1 si la destruction s'est effectuée correctement, 0 sinon
	 */
	public int Delete() {
		Connection cnx = PostgresConnection.GetConnexion();
		String query = "DELETE FROM bars WHERE idbar = ?";

		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setInt(1, idBar);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}