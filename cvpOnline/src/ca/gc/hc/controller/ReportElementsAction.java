/*
 * Created on April 22, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.controller;

// Import Java IO classes.
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ca.gc.hc.bean.DisplayBean;
import ca.gc.hc.util.ApplicationGlobals;
import ca.gc.hc.view.ReportElementForm;
/**
 * Action class that will collect the desired report element before directing the user
 * to the search results page
 */
public class ReportElementsAction extends LookupDispatchAction {

	private String strLanguage = null;
	
	/***************************************************************************
	 * Maps the resources used for the button text to the related action methods.
	 */
	protected Map getKeyMethodMap() {
		Map<String, String> buttonActions = new HashMap<String, String>();

		buttonActions.put("button.report.go", "reportView");
		buttonActions.put("button.searchResults", "searchResults");		
		return buttonActions;
	}	
	
	public ReportElementsAction(){
		super();
	}

	public ActionForward execute(
		    ActionMapping mapping,
		    ActionForm form,
		    HttpServletRequest request,
		    HttpServletResponse response)
		    throws Exception {
				
				HttpSession session = request.getSession(true);
				
				if (session.getAttribute("sessionActive") != null){
				   String strLanguage = request.getParameter("lang");	
				   Locale currentLocale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);									   
				   strLanguage = currentLocale.getLanguage() + "_" + currentLocale.getCountry();			
				   if ("eng".equals(strLanguage)){
						session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
						request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
				   } else if ("fra".equals(strLanguage)){
						session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("fr","CA"));
						request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
				   } 				
				} else {
					return mapping.findForward("sessionTimeout");				
				}
				session.removeAttribute("maxExportLimit");
				return super.execute(mapping, form, request, response);		 	
	}
	
	public ActionForward searchResults(
		    ActionMapping mapping,
		    ActionForm form,
		    HttpServletRequest request,
		    HttpServletResponse response)
		    throws Exception {
				ActionForward forward = new ActionForward();
				HttpSession session = request.getSession(true);
				ReportElementForm reportElementForm = (ReportElementForm)form;		
//				List selectedElements = null;
				
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
					//selectedElements = reportElementForm.getSelectedItems();			
					//session.setAttribute(ApplicationGlobals.DISPLAY_ELEMENTS, selectedElements);
//					if (((Integer)(session.getAttribute("searchResultsSize"))).intValue() > 1000) {
//						messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("warning.search.max"));
//						saveMessages(request, messages);						
//					}
					forward = mapping.findForward("success");
				} else {
					forward = mapping.findForward("sessionTimeout");
				}
				reportElementForm.setReportFormView("1");				
				return (forward);
			  }

	public ActionForward reportView (ActionMapping mapping, ActionForm form,
 	 		HttpServletRequest request, HttpServletResponse response) throws Exception {
 	 		
 	   		ActionForward forward = new ActionForward(); // return value
 	   		HttpSession session = request.getSession(false);
 	   		if (session.getAttribute("sessionActive") != null) {
 				String strLanguage = request.getParameter("lang");
				ReportElementForm reportElementForm = (ReportElementForm)form; 	
				if (reportElementForm.getReportFormView().equals("2")){
					// custom view, so display the report elements
					session.setAttribute("customReport", "custom");
					// pre-check the standard report elements
					reportElementForm.setMahNumber("on");
					reportElementForm.setInitialDate("on");
					reportElementForm.setAge("on");
					reportElementForm.setGender("on");
					reportElementForm.setDrugName("on");
					reportElementForm.setMeddraPTName("on");					
					forward = mapping.findForward("reportElements");					
				} else {
					// standard view, so go straight to the search results page (with the standard report elements selected)
					DisplayBean displayBean = new DisplayBean();
					reportElementForm.setMahNumber("on");
					reportElementForm.setInitialDate("on");
					reportElementForm.setAge("on");
					reportElementForm.setGender("on");
					reportElementForm.setDrugName("on");
					reportElementForm.setMeddraPTName("on");
					displayBean.setDisplayMahNumber(ApplicationGlobals.SELECTED);
					displayBean.setDisplayInitialDate(ApplicationGlobals.SELECTED);
					displayBean.setDisplayAge(ApplicationGlobals.SELECTED);
					displayBean.setDisplayGender(ApplicationGlobals.SELECTED);
					displayBean.setDisplayDrugName(ApplicationGlobals.SELECTED);
					displayBean.setDisplayMeddraPTName(ApplicationGlobals.SELECTED);
					
					session.setAttribute(ApplicationGlobals.DISPLAY_ELEMENTS, displayBean);					
					return searchResults(mapping, form, request, response); 
				}
 	   		} else {
 				forward = mapping.findForward("sessionTimeout");   	   			
 	   		}
 	   		
 	   		return (forward);
 	 	}
 	
 	
//	public ActionForward execute(
//    ActionMapping mapping,
//    ActionForm form,
//    HttpServletRequest request,
//    HttpServletResponse response)
//    throws Exception {
//		ActionForward forward = new ActionForward();
//		ActionMessages messages = new ActionMessages();        
//		HttpSession session = request.getSession(true);
//		ReportElementForm reportElementForm = (ReportElementForm)form;		
//		List selectedElements = null;
//		
//		if (session.getAttribute("sessionActive") != null){
//			   if(request.getParameter("lang") == null)	{
//				   Locale currentLocale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);									   
//				   strLanguage = currentLocale.getLanguage() + "_" + currentLocale.getCountry();
//			   	} else 	{
//				   	strLanguage = request.getParameter("lang");	
//					if ("eng".equals(strLanguage))	{
//						session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
//						ApplicationGlobals.setUserLocale(new Locale("en", "CA"));
//				   	}
//				   	else if ("fra".equals(strLanguage))	{
//						session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("fr","CA"));
//						ApplicationGlobals.setUserLocale(new Locale("fr", "CA"));
//				   	}
//			   	}			
//			selectedElements = reportElementForm.getSelectedItems();			
//			//session.setAttribute(ApplicationGlobals.DISPLAY_ELEMENTS, selectedElements);
//			forward = mapping.findForward("success");
//		} else {
//			forward = mapping.findForward("sessionTimeout");
//		}
//		return (forward);
//	  }
  
}