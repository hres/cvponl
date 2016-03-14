/*
 * Created on March 10, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.controller;

// Import Java IO classes.
import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ca.gc.hc.dao.LookUpDAO;
import ca.gc.hc.util.ApplicationGlobals;
import ca.gc.hc.view.SearchForm;
/**
 * Action class that will load all of the required elements for the search page.
 */
public class LoadSearchAction extends Action
{
	private String strLanguage = null;
 
  /**
   * Process the specified HTTP request, and create the corresponding HTTP
   * response (or forward to another web component that will create it).
   * Return an <code>ActionForward</code> instance describing where and how
   * control should be forwarded, or <code>null</code> if the response has
   * already been completed.
   * <p>
   * @param mapping  The ActionMapping used to select this instance.
   * @param actionForm  The optional ActionForm bean for this request (if any).
   * @param request  The HTTP request we are processing.
   * @param response  The HTTP response we are creating.
   *
   * @exception IOException  If an input/output error occurs.
   * @exception ServletException  If a servlet exception occurs.
   */
  public ActionForward execute(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException
  {
		ActionMessages messages = new ActionMessages();
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		try {			
		   if (session.getAttribute("sessionActive") != null){		
			   if(request.getParameter("lang") == null)	{
				   Locale currentLocale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);									   
				   strLanguage = currentLocale.getLanguage() + "_" + currentLocale.getCountry();
			   	} else 	{
				   	strLanguage = request.getParameter("lang");	
					if ("eng".equals(strLanguage))	{
						session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
						ApplicationGlobals.setUserLocale(new Locale("en", "CA"));
				   	}
				   	else if ("fra".equals(strLanguage))	{
						session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("fr","CA"));
						ApplicationGlobals.setUserLocale(new Locale("fr", "CA"));
				   	}
			   	}
			   		Locale locale = (Locale) session.getAttribute("org.apache.struts.action.LOCALE");
					LookUpDAO lookUpDAO = new LookUpDAO();
					SearchForm searchForm = (SearchForm) form;
					
					// load all data into the session for the dropdown boxes
					session.setAttribute("ageDropdown", lookUpDAO.getAgeDropdown());
					session.setAttribute("seriousness", lookUpDAO.getSeriousness(locale));
					session.setAttribute("reportSources", lookUpDAO.getReportSources(locale));
					session.setAttribute("genders", lookUpDAO.getGenders(locale));
					session.setAttribute("outcomes", lookUpDAO.getOutcomes(locale));
					session.setAttribute("quarter", lookUpDAO.getQuarter());
					ApplicationGlobals.MEDDRA_VERSION_VALUE = lookUpDAO.getMeddraVersion();

					// Clean up session variable holder
					session.removeAttribute("suspectedDrugs");
					session.removeAttribute("activeIngredients");
					session.removeAttribute("systemOrganClasses");
	 				session.removeAttribute("preferredTerms");
	 				session.setAttribute("displaySection3Search", "Active");
	 				session.setAttribute("displaySection4Search", "Active");	 				
//	 				session.removeAttribute("displaySection4Search");	 				

	 				session.removeAttribute(ApplicationGlobals.SEARCH_RESULTS);
	 				session.removeAttribute(ApplicationGlobals.SEARCH_RESULT_KEY);
					session.removeAttribute(ApplicationGlobals.SEARCH_RESULT_PAGE_NUMBER);
					session.removeAttribute("page");
					ApplicationGlobals.RESULTS_BLOCK = 1;

					// System.gc();

					forward = mapping.findForward("success");
		   }
		   else  {
			   //forward to timeout
				forward = mapping.findForward("sessionTimeout");			   
		   }
	   } catch (Exception e) {	
		   // Report the error using the appropriate name and ID.
		   messages.add("name", new ActionMessage("id"));
		   forward = mapping.findForward("failure");		   
	   }
	   if (!messages.isEmpty()) {
		   saveErrors(request, messages);
		} 
	   return (forward);
	  }
}