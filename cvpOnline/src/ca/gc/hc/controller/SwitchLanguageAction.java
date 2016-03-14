package ca.gc.hc.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ca.gc.hc.util.ApplicationGlobals;

/*******************************************************************************
 * An Action used to switch between english and french 
 * depending on the language the user chooses from the top nav button
 * which is in the header.jsp file on all pages
 */
public class SwitchLanguageAction extends Action {

	private static Log log = LogFactory.getLog(SwitchLanguageAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
						 throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("sessionActive") != null){		
			String strLang = request.getParameter("lang");			
		    String formname = request.getParameter("url");

		    if (request.getParameter("section") == null) {		    
				if (strLang.equals("eng")) {
					request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
					ApplicationGlobals.setUserLocale(new Locale("en", "CA"));
					//return mapping.findForward("reload");					
				} else {
					request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("fr", "CA"));
					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("fr","CA"));
					ApplicationGlobals.setUserLocale(new Locale("fr", "CA"));
					//return mapping.findForward("reload");					
				}
		    }
			
			if (formname != null) {
				forward = new ActionForward(formname);
			} else {
				//go to the main page
				forward = new ActionForward("t.search.recherche");
			}
		}
		else {
			forward = mapping.findForward("sessionTimeout");			
		}
		return forward;
	}
}