package fr.cursusSopra.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.model.Bar;
import fr.cursusSopra.model.Horaire;
import fr.cursusSopra.model.Ville;
import fr.cursusSopra.model.CategorieBar;
import fr.cursusSopra.model.Critere;

public class CreationBarAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6003588437744997532L;

	private String nom;
	private String numTel;
	private String site;
	private String description;
	private String voie;
	private String ville;
	private List<CategorieBar> lstCategories;
	private List<Critere> lstCriteres;
	private List<String> champVille;
	private List<String> lstJours;

	private int[] checkboxCritere;
	private int[] checkboxCategorie;

	public String execute() {
		Bar leBar = new Bar();
		leBar.setNom(nom);
		leBar.setNumTel(numTel);
		leBar.setSite(site);
		leBar.setDescription(description);
		leBar.setVoie(voie);
		leBar.setVille(ville);
		leBar.setTabCriteres(checkboxCritere);
		leBar.setTabCategories(checkboxCategorie);
		
		int result = leBar.Create();
		
		return result == 0 ? ERROR : SUCCESS;
	}
	
	public String accessFormCreation(){
		setChampVille(Ville.getLstChampVille());
		lstCategories = CategorieBar.getListeCategoriesBar();
		lstCriteres = Critere.getListeCriteres();
		lstJours = Horaire.getLstJours();
		return SUCCESS;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public void setSite(String site) {
		this.site = site;
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
	
	public List<String> getChampVille() {
		return champVille;
	}
	
	public void setChampVille(List<String> champVille) {
		this.champVille = champVille;
	}
	
	public List<CategorieBar> getLstCategories() {
		return lstCategories;
	}
	
	public List<Critere> getLstCriteres() {
		return lstCriteres;
	}
	
	public void setCheckboxCritere(int[] checkboxCritere) {
		this.checkboxCritere = checkboxCritere;
	}
	
	public void setCheckboxCategorie(int[] checkboxCategorie) {
		this.checkboxCategorie = checkboxCategorie;
	}
	
	public List<String> getLstJours() {
		return lstJours;
	}
}
