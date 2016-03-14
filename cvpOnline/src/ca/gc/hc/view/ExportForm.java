package ca.gc.hc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ExportForm extends ActionForm {

	private String	foption="";
	
	public void reset (ActionMapping mapping, HttpServletRequest request){
		foption = "";
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors messages = new ActionErrors();
		HttpSession session = request.getSession();
		
		if (session.getAttribute("sessionActive") != null){
			session.setAttribute("searchForm", this);
			if (this.getFoption().equals("")){
				messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.export.option.empty"));				
			}
		}
		
		return messages;
	}

	public String getFoption() {
		return foption;
	}

	public void setFoption(String foption) {
		this.foption = foption;
	}

}