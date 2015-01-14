package fr.cursusSopra.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.model.Bar;

public class IndexAction extends ActionSupport {

	private List<Bar> lstBars;	
	/**
	 * 
	 */
	private static final long serialVersionUID = -336197339380396177L;
	
	public String execute(){
		lstBars= Bar.getListeDesBars();
		return SUCCESS;
	}
	public List<Bar> getLstBars() {
		return lstBars;
	}
}
