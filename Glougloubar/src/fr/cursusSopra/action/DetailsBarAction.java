package fr.cursusSopra.action;
import fr.cursusSopra.model.Bar;

import com.opensymphony.xwork2.ActionSupport;

public class DetailsBarAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2448904005907254703L;

	private int idBar;
	private Bar leBar;
	
	public void setIdBar(int idBar) {
		this.idBar = idBar;
	}

	public Bar getLeBar(){
		return leBar;
	}
	
	public String execute(){
		leBar = new Bar(idBar);
		
		return SUCCESS;
	}
}
