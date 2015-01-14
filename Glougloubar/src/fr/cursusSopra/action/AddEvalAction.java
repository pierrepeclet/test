package fr.cursusSopra.action;

import java.util.*;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.model.Critique;

public class AddEvalAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int idBar;
	private String idLib;
	private List<Integer> notes;
	private String commentaire;

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public void setIdBar(int idBar) {
		this.idBar = idBar;
	}

	public void setIdLib(String idLib) {
		this.idLib = idLib;
	}

	public String execute() {
		notes = new ArrayList<Integer>();
		String[] party = idLib.split(",");

		// partsss =Arrays.asList( idLib.split(",") );
		for (int i = 0; i < party.length ; i++) {
			notes.add(  Integer.parseInt( party[i] ) );
		}

		Critique critTest = new Critique();
		critTest.AddCritique(notes, commentaire, idBar);

		return SUCCESS;
	}

}
