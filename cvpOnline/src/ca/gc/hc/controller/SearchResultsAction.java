/*
 * Created on March 19, 2009
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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import ca.gc.hc.util.ApplicationGlobals;
import ca.gc.hc.view.SearchForm;
/**
 * Action class that will populate the search criteria into the search bean and then send to
 * the search dao for retrieval of object results
 */
public class SearchResultsAction extends LookupDispatchAction {
	/***************************************************************************
	 * Maps the resources used for the button text to the related action methods.
	 */
   	protected Map getKeyMethodMap() {
	// TODO Auto-generated method stub
		Map<String, String> buttonActions = new HashMap<String, String>();
        
		buttonActions.put("button.newSearch", "dispatchNewSearch");
		buttonActions.put("button.back", "dispatchBack");
		buttonActions.put("button.exportOption", "exportOption");
		buttonActions.put("submit", "save");		
		return buttonActions;   		
	}
	
	public SearchResultsAction(){
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
		
		String action = request.getParameter("action");
		if (action.equals("1")){
			return dispatchBack(mapping, form, request, response);
		} else {
			return super.execute(mapping, form, request, response);			
		}
		 	
	  }
  

   	public ActionForward dispatchBack (ActionMapping mapping, ActionForm form,
   		    HttpServletRequest request, HttpServletResponse response)
   		    throws Exception {
   	   		ActionForward forward = new ActionForward(); // return value
   	   		HttpSession session = request.getSession(false);  
   	   		SearchForm searchForm = (SearchForm) form;
   	   		if (session.getAttribute("sessionActive") != null){
				String strLanguage = request.getParameter("lang");	
				if ("eng".equals(strLanguage)){
					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
					request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
				} else if ("fra".equals(strLanguage)){
					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("fr","CA"));
					request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
				}
				BeanUtils.copyProperties(searchForm, session.getAttribute(ApplicationGlobals.SEARCH_CRITERIA));
				ApplicationGlobals.RESULTS_BLOCK = 1;				
   	   	   		forward = mapping.findForward("backToSearch");  	   		
   	   		} else {
				forward = mapping.findForward("sessionTimeout");   	   			
   	   		}
   	 	return (forward);   		
   	 }

   	public ActionForward dispatchNewSearch (ActionMapping mapping, ActionForm form,
   		    HttpServletRequest request, HttpServletResponse response)
   		    throws Exception { 	
   		
   	   		ActionForward forward = new ActionForward(); // return value
   	   		HttpSession session = request.getSession(false);   	   		
   	   		if (session.getAttribute("sessionActive") != null){
				String strLanguage = request.getParameter("lang");	
				if ("eng".equals(strLanguage)){
					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
					request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
				} else if ("fra".equals(strLanguage)){
					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("fr","CA"));
					request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
				}    

   	   	   		forward = mapping.findForward("newSearch");  	   		
   	   		} else {
				forward = mapping.findForward("sessionTimeout");   	   			
   	   		}
   	 	return (forward);   		
   	}
   	
   	public ActionForward exportOption (ActionMapping mapping, ActionForm form,
   		    HttpServletRequest request, HttpServletResponse response)
   		    throws Exception { 	
   		
   	   		ActionForward forward = new ActionForward(); // return value
   	   		HttpSession session = request.getSession(false);
   			ActionMessages messages = new ActionMessages();
   			//int searchRowsSize = 0; 
   			int searchResultsSize = 0;
   	   		
   	   		if (session.getAttribute("sessionActive") != null){
				String strLanguage = request.getParameter("lang");	
				if ("eng".equals(strLanguage)){
					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
					request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
				} else if ("fra".equals(strLanguage)){
					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("fr","CA"));
					request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
				}    

   	   	   		forward = mapping.findForward("exportOption");  	   		
   	   		} else {
				forward = mapping.findForward("sessionTimeout");   	   			
   	   		}
   	   		
   			//searchRowsSize = ((Integer) session.getAttribute("searchRowsSize")).intValue();
   			searchResultsSize = ((Integer) session.getAttribute("searchResultsSize")).intValue();
   			
   			if (searchResultsSize > 1000) {
   				session.setAttribute("maxExportLimit", "1");
   				System.out.println("Export limit exceeded");
   				//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("warning.export.max"));
   				forward = mapping.findForward("searchResults");
   			} else {
   				session.removeAttribute("maxExportLimit");
   			}
   			
   			
   			if (!messages.isEmpty()){
   				saveMessages(request, messages);   				
   			}
   			
   	 	return (forward);   		
   	 }
  
}