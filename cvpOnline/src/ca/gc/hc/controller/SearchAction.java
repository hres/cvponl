 /*
 * Created on March 19, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.controller;

// Import Java IO classes.
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.LookupDispatchAction;

import ca.gc.hc.dao.SearchDAO;
import ca.gc.hc.util.ActionUtil;
import ca.gc.hc.util.ApplicationGlobals;
import ca.gc.hc.util.SearchCriteria;
import ca.gc.hc.view.PagerForm;
import ca.gc.hc.view.SearchForm;
/**
 * Action class that will populate the search criteria into the search bean and then send to
 * the search dao for retrieval of object results
 */
public class SearchAction extends LookupDispatchAction 
{
	
	/***************************************************************************
	 * Maps the resources used for the button text to the related action methods.
	 */
   	protected Map getKeyMethodMap() {
		Map<String, String> buttonActions = new HashMap<String, String>();

		buttonActions.put("button.sec3find", "section3Find");
		buttonActions.put("button.sec4find", "section4Find");
		buttonActions.put("button.search", "searchExecute");
		buttonActions.put("button.reset", "searchReset");
//		buttonActions.put("button.report.go", "section3Go");
		buttonActions.put("button.section3.go", "section3Go");		
//		buttonActions.put("button.section4.go", "section4Go");		
		return buttonActions;   		
	}
   	
	private String strLanguage = null;
 
	public SearchAction(){
		super();
	}
	
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
 * @throws Exception 
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
		  			HttpServletRequest request, HttpServletResponse response) throws Exception {

	  ActionMessages messages = new ActionMessages();
	  HttpSession session = request.getSession(true);
	  String sortParameter = "";
		
		try {			
		   if (session.getAttribute("sessionActive") != null){	
			   if(request.getParameter("lang") == null)	{
				   Locale currentLocale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);									   
				   strLanguage = currentLocale.getLanguage() + "_" + currentLocale.getCountry();
			   	} else {
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
				if (this.isCancelled(request)){
					return (mapping.findForward("backToSearch"));
				}

//				if (request.getParameter("section") != null){
//					return (mapping.findForward("backToSearch"));					
//				}
			   
				if (request.getParameter("colID") != null){
					 // get the id value from the link URL
						sortParameter = request.getParameter("colID");
						session.setAttribute(ApplicationGlobals.SORT_PARAMETER, sortParameter);
						return searchExecute(mapping, form, request, response);						
				} else {
					// default sorting (by report id)
					session.removeAttribute(ApplicationGlobals.SORT_PARAMETER);	
					ApplicationGlobals.RESULTS_BLOCK = 1;
				}
		   } else  {
			   //forward to timeout
				return mapping.findForward("sessionTimeout");			   
		   }
		} catch (Exception e) {	
		   // Report the error using the appropriate name and ID.
		   messages.add("name", new ActionMessage("id"));
		   return mapping.findForward("failure");		   
		} 
	   return super.execute(mapping, form, request, response);			

	}

  public ActionForward searchExecute(
		    ActionMapping mapping,
		    ActionForm form,
		    HttpServletRequest request,
		    HttpServletResponse response)
		  {
				ActionMessages messages = new ActionMessages();
				ActionForward forward = new ActionForward();
				HttpSession session = request.getSession(true);
				//List searchResultBeans = null;
				Locale locale;
				int sortValue = 1; // default sort by report ID
				int recordCount = 0;
				int rowCount = 0;
				int pageNum = 1;
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
					   locale = (Locale)session.getAttribute("org.apache.struts.action.LOCALE");
					   SearchCriteria searchCriteria = new SearchCriteria();
					   SearchForm searchForm = (SearchForm) form;	
					   
					   if ( ((session.getAttribute("suspectedDrugs")) == null) 
							   && ((session.getAttribute("activeIngredients")) == null) 
							   && ((session.getAttribute("systemOrganClasses")) == null)
							   && ((session.getAttribute("preferredTerms")) == null)) {
						   // no items have been selected from the dropdown, or no matching results were found by use of the 'Find'
						   if ((searchForm.getNameLevel().equals("0")) && (searchForm.getMeddraTermLevel().equals("0"))) {
							   messages.add("selectAllError", new ActionMessage("error.go.notSelected"));							   
						   } else {
							   messages.add("nothingSelected", new ActionMessage("error.nothing.selected"));							   
						   }
						   saveMessages(request, messages);						   
						   return mapping.findForward("backToSearch");
//					   } else {
//						   if (searchForm.getNameLevel().equals("0")) {
//							   // clean up attributes if the Select All option is selected
//							   session.removeAttribute("suspectedDrugs");
//							   session.removeAttribute("activeIngredients");
//							   session.removeAttribute("displaySection3Search");
//						   }
//						   if (searchForm.getMeddraTermLevel().equals("0")){
//							   // clean up attributes if the Select All option is selected	
//							   session.removeAttribute("systemOrganClasses");
//							   session.removeAttribute("preferredTerms");							   
//						   }
					   }

					   ActionErrors error = new ActionErrors();
					   
					   if (session.getAttribute(ApplicationGlobals.SORT_PARAMETER) != null){
						   ApplicationGlobals.SORT_BY_COLUMN = true;
						   sortValue = Integer.valueOf((String) session.getAttribute(ApplicationGlobals.SORT_PARAMETER)).intValue();
						   // Criteria should already be stored in the session
						   searchCriteria = (SearchCriteria) session.getAttribute("SearchCriteria");
					    	if(session.getAttribute(ApplicationGlobals.SEARCH_RESULT_PAGE_NUMBER) != null){
					    		pageNum = Integer.valueOf((String) session.getAttribute(ApplicationGlobals.SEARCH_RESULT_PAGE_NUMBER)).intValue();
					    		
					    		if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
					    			System.out.println("PAGE NUMBER FOR SORTING : " + pageNum);
					    		}
					    	}
					   } else {
						   // copy values from search form to the search criteria
						   ApplicationGlobals.SORT_BY_COLUMN = false;
						   if (sortValue == 1){
							   // only validate when submitting from the search page
							   error = searchForm.validate(mapping, request);						   
						   }
						   BeanUtils.copyProperties(searchCriteria, searchForm);	
						   // get the relevant 'names' of the dropdown selections based on the searchForm
						   // store this information in the SearchCriteria object
						   getNames(searchForm, searchCriteria, locale);
						   // populate the 'searched' keyword
						   searchCriteria.setKeywordsSection3((String) session.getAttribute("section3keyword"));
						   searchCriteria.setKeywordsSection4((String) session.getAttribute("section4keyword"));
						   
						   session.setAttribute(ApplicationGlobals.SEARCH_CRITERIA, searchCriteria);
						   // clearing paging parameters
						   session.removeAttribute(ApplicationGlobals.SEARCH_RESULT_PAGE_NUMBER);
						   session.removeAttribute("page");
					   }

					   // use the criteria and send to the DAO
					   SearchDAO searchDAO = new SearchDAO();
					   //SearchUtility searchUtil = new SearchUtility();
					   
					   List searchResults = null;
					   
					   if (error.isEmpty()) {
						   // Count records before submitting for a search
						   recordCount = searchDAO.countSearch(searchCriteria, locale);
						   
						   if (recordCount > 1000){
							   // warning message regarding exporting
							   session.setAttribute("maxExportLimit", "1");
							   //messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("warning.search.max"));
							   //System.out.println("greater than a 1000 reports");
						   } else {
							   session.removeAttribute("maxExportLimit");
						   }
						   // Set row counter number to session
						   rowCount = searchDAO.countRow(searchCriteria, locale);
						   session.setAttribute(ApplicationGlobals.SEARCH_ROWS_SIZE, rowCount);
						   
//						   if (recordCount > 20000){
//							   System.out.println("Record Count :" + recordCount);
//							   System.out.println("Exceeded 4000 cases");
//							   messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("warning.search.max"));
//							   saveMessages(request, messages);						   
//							   return mapping.findForward("backToSearch");							   
//						   }						   
						   
						   Runtime.getRuntime().gc();
						   
						   long objSize = Runtime.getRuntime().freeMemory();
						   searchResults = searchDAO.searchByReportQuery(searchCriteria, locale, 20, pageNum, sortValue);
						   objSize = objSize - Runtime.getRuntime().freeMemory();  
						   
						   if (ApplicationGlobals.DEBUG == "memory" || ApplicationGlobals.DEBUG == "all"){
							   System.out.println("@@@@ Java Object Size : " + objSize);
							   System.out.println("@@@@ Java free memory : " + Runtime.getRuntime().freeMemory());
						   }
						   session.setAttribute(ApplicationGlobals.SEARCH_RESULTS_SIZE, recordCount);
						   System.out.println("search results size : " + recordCount);
					   
						   // System.gc();
						   
					   // pass searchResults and searchCriteria to the SearchResultsBean in order to
					   // gather all the required report data in one place
					   if ((searchResults != null) && (searchResults.size() > 0)){
						   //HashSet distinctSearchResults = new HashSet(searchResults);
						   
						   //OLD searchResultBeans = searchUtil.populateResultsBean(searchResults, searchCriteria);
						   //OLD System.out.println("SEARCH RESULTS BEANS : " + searchResultBeans.size());
						   
						   //OLD session.setAttribute(ApplicationGlobals.SEARCH_RESULTS, searchResultBeans);
						   objSize = Runtime.getRuntime().freeMemory();
						   session.setAttribute(ApplicationGlobals.SEARCH_RESULTS, searchResults);
						   objSize = objSize - Runtime.getRuntime().freeMemory();
						   if (ApplicationGlobals.DEBUG == "memory" || ApplicationGlobals.DEBUG == "all"){
							   System.out.println("@@@@ Session Object Size : " + objSize);
						   }
						   //session.setAttribute(ApplicationGlobals.SEARCH_RESULT_KEY, searchResultBeans);
						   session.setAttribute("MEDDRA_VERSION", ApplicationGlobals.MEDDRA_VERSION_VALUE);
					   } else {
						   messages.add("noMatchingRecords", new ActionMessage("error.search.results"));						   
						   session.setAttribute("MEDDRA_VERSION", "");	
						   forward = mapping.findForward("backToSearch");						   
					   }

					   //OLD searchForm.setSearchResults((ArrayList) searchResultBeans);
					   //OLD System.out.println("RESULTS SIZE: " + searchResults.size());
					   ApplicationGlobals.EXECUTION_TIME = (System.currentTimeMillis() - ApplicationGlobals.EXECUTION_TIME);
					   //OLD System.out.println("$$$$ JAVA Processing Time: " + ApplicationGlobals.EXECUTION_TIME + " ms");
					   
					   if (session.getAttribute(ApplicationGlobals.SORT_PARAMETER) == null) {
						   // return the report elements page
						   forward = mapping.findForward("reportElements");	
						   session.removeAttribute("customReport");						   
						   request.setAttribute(ApplicationGlobals.DEFAULT_ELEMENTS, ApplicationGlobals.DEFAULT_ELEMENTS);
					   } else {
						   // user has re-submitted from the search results page to sort by a specific column
						   // return directly to the search results page
						   forward = mapping.findForward("success");						   
					   }
					   
					   } else {
						   forward = mapping.findForward("backToSearch");
						   messages.add(error);
					   }
				   }
				   else  {
					   //forward to timeout
						forward = mapping.findForward("sessionTimeout");			   
				   }
			   } catch (Exception e) {	
				   e.printStackTrace();
				   System.out.println(e.getMessage());				   
				   messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.failure.system"));				   
			   } 
			   if (!messages.isEmpty()) {
				   saveMessages(request, messages);
				   forward = mapping.getInputForward();		   
			   } else {
					if (session.getAttribute(ApplicationGlobals.SEARCH_RESULTS) != null){
				    	PagerForm myForm = new PagerForm();
						
				    	if(session.getAttribute(ApplicationGlobals.SEARCH_RESULT_PAGE_NUMBER) != null){
				    		String strPageNumber = (String)session.getAttribute("page");
				    		if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
				    			System.out.println("PAGE NUMBER : " + strPageNumber);
				    		}
				    		myForm.setPage(Integer.parseInt(strPageNumber));
				    	}
				    	else {
				    		myForm.setPage(1);
				    	}
					    ActionUtil.setPager(mapping,
									        request,
									        myForm,
											ApplicationGlobals.INITIAL_ACTION);
								
				    }				   				   
			   }
			   return (forward);
	}
  
 	public ActionForward searchReset (ActionMapping mapping, ActionForm form,
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
				
				// Reset all form values
				searchForm.reset(mapping, request);
				
				// Clean up session variable holder
				session.removeAttribute("suspectedDrugs");
				session.removeAttribute("activeIngredients");
				session.removeAttribute("systemOrganClasses");
 				session.removeAttribute("preferredTerms");
 				//session.removeAttribute("displaySection3Search");
 				session.setAttribute("displaySection3Search", "Active"); 				
 				//session.removeAttribute("displaySection4Search"); 			
 				session.setAttribute("displaySection4Search", "Active"); 				
 				session.removeAttribute(ApplicationGlobals.SEARCH_RESULTS);
 				session.removeAttribute(ApplicationGlobals.SEARCH_RESULT_KEY);
 				
   	   	   		forward = mapping.findForward("backToSearch");  	   		
   	   		} else {
				forward = mapping.findForward("sessionTimeout");   	   			
   	   		}
   	 	return (forward);   		
   	 }

 	public ActionForward section3Go (ActionMapping mapping, ActionForm form,
 	 		HttpServletRequest request, HttpServletResponse response) throws Exception {
 	 		
 	   		ActionForward forward = new ActionForward(); // return value
 	   		HttpSession session = request.getSession(false);
 	   		SearchForm searchForm = (SearchForm) form;
 	   		System.out.println("in SECTION 3");
 	   		
 	   		if (session.getAttribute("sessionActive") != null) {

 	   			ActionRedirect redirect = new ActionRedirect(mapping.findForwardConfig("anchor"));
   				if (strLanguage.equals("eng")){
 	   				redirect.addParameter("lang", "eng"); 	   					
   				} else {
 	   				redirect.addParameter("lang", "fra"); 	   					
   				}
	   			
 	   			if ((searchForm.getNameLevel().equals("1")) || (searchForm.getNameLevel().equals("2"))){
 	   				redirect.setAnchor("section3");
 	   				redirect.addParameter("section", "3");
 	 	   			session.setAttribute("displaySection3Search", "Active");
 	   			} else {
 	   				session.removeAttribute("displaySection3Search");
					session.removeAttribute("suspectedDrugs");
					session.removeAttribute("activeIngredients"); 	   				
 	   			}
 	   			
 	   			if ((searchForm.getMeddraTermLevel().equals("1")) || (searchForm.getMeddraTermLevel().equals("2"))){
 	   				redirect.setAnchor("section4");
 	   				redirect.addParameter("section", "4"); 	   				
 	 	   			session.setAttribute("displaySection4Search", "Active"); 	 
 	   			} else {
 	   				session.removeAttribute("displaySection4Search");
					session.removeAttribute("systemOrganClasses");
					session.removeAttribute("preferredTerms"); 	   				
 	   			}
 	   			
 	   			if ((searchForm.getNameLevel().equals("0")) && (searchForm.getMeddraTermLevel().equals("0"))){
 	   				// if the user presses the Go button without selecting any of the options first
 	   				forward = mapping.getInputForward();
 	   			} else {
 		 	   		return (redirect); 	   				
 	   			}
 	   		} else {
 				forward = mapping.findForward("sessionTimeout");   	   			
 	   		}
 	   		return (forward);
 	 	}

 	public ActionForward section4Go (ActionMapping mapping, ActionForm form,
 	 		HttpServletRequest request, HttpServletResponse response) throws Exception {
 	 		
 	   		ActionForward forward = new ActionForward(); // return value
 	   		HttpSession session = request.getSession(false);
 	   		SearchForm searchForm = (SearchForm) form;
 	   		System.out.println("in SECTION 4");

// 	   		if (session.getAttribute("sessionActive") != null) {
//
// 	   			ActionRedirect redirect = new ActionRedirect(mapping.findForwardConfig("anchor"));
//   				if (strLanguage.equals("eng")){
// 	   				redirect.addParameter("lang", "eng"); 	   					
//   				} else {
// 	   				redirect.addParameter("lang", "fra"); 	   					
//   				}
//	   			
// 	   			if ((searchForm.getNameLevel().equals("1")) || (searchForm.getNameLevel().equals("2"))){
// 	   				redirect.setAnchor("section3");
// 	   				redirect.addParameter("section", "3");
// 	 	   			session.setAttribute("displaySection3Search", "Active");
// 	   			} else {
// 	   				session.removeAttribute("displaySection3Search");
//					session.removeAttribute("suspectedDrugs");
//					session.removeAttribute("activeIngredients"); 	   				
// 	   			}
// 	   			
// 	   			if ((searchForm.getMeddraTermLevel().equals("1")) || (searchForm.getMeddraTermLevel().equals("2"))){
// 	   				redirect.setAnchor("section4");
// 	   				redirect.addParameter("section", "4"); 	   				
// 	 	   			session.setAttribute("displaySection4Search", "Active"); 	 
// 	   			} else {
// 	   				session.removeAttribute("displaySection4Search");
//					session.removeAttribute("systemOrganClasses");
//					session.removeAttribute("preferredTerms"); 	   				
// 	   			}
// 	   			
// 	   			if ((searchForm.getNameLevel().equals("0")) && (searchForm.getMeddraTermLevel().equals("0"))){
// 	   				// if the user presses the Go button without selecting any of the options first
// 	   				forward = mapping.getInputForward();
// 	   			} else {
// 		 	   		return (redirect); 	   				
// 	   			}
// 	   		} else {
// 				forward = mapping.findForward("sessionTimeout");   	   			
// 	   		}
			forward = mapping.getInputForward(); 	   		
 	   		return (forward);
 	 	}
 	
 	public ActionForward section3Find (ActionMapping mapping, ActionForm form,
 		HttpServletRequest request, HttpServletResponse response) throws Exception {
 		
   		ActionForward forward = new ActionForward(); // return value
   		HttpSession session = request.getSession(false);
   		SearchForm searchForm = (SearchForm) form;
   		SearchDAO searchDao = new SearchDAO();
		ActionMessages messages = new ActionMessages();   		
   		List susDrugs, actIngs;
   		
   		if (session.getAttribute("sessionActive") != null) {
			String strLanguage = request.getParameter("lang");
			
			// Clean up session variable holder
			if (Integer.valueOf(searchForm.getMeddraTermLevel()) == 0) {
				// if Select All is selected (after the user has already searched once in Section 4)
				session.removeAttribute("activeIngredients");
				searchForm.setSection4results(null);
				searchForm.setWildcardSection4("2");
				searchForm.setKeywordsSection4("");
			}
				
			// Clean up session variable holder
			session.removeAttribute("suspectedDrugs");
			session.removeAttribute("activeIngredients");
			session.removeAttribute("section3keyword");						
			searchForm.setSection3results(null);
			
			if ("eng".equals(strLanguage)) {
				session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
				request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
			} else if ("fra".equals(strLanguage)) {
				session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("fr","CA"));
				request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
			}
			
			searchForm.setSection3FindSearch(true);
			
			// validate Search Form submission
			ActionErrors errors = new ActionErrors();				   
			errors = searchForm.validate(mapping, request);

			if (!errors.isEmpty()) {
				messages.add(errors);
				saveMessages(request, messages);
				searchForm.setSection3FindSearch(false);
				return mapping.findForward("backToSearch");
			}

			ActionRedirect redirect = new ActionRedirect(mapping.findForwardConfig("anchor"));
			if (strLanguage.equals("eng")){
   				redirect.addParameter("lang", "eng"); 	   					
			} else {
   				redirect.addParameter("lang", "fra"); 	   					
			}
   				
			switch (Integer.valueOf(searchForm.getNameLevel())) {
			case 1: {
				susDrugs = searchDao.findSuspectedDrugs(searchForm.getWildcardSection3(), searchForm.getKeywordsSection3());
				if (susDrugs.size() > 0){
					session.setAttribute("section3keyword", searchForm.getKeywordsSection3());					
					session.setAttribute("suspectedDrugs", susDrugs);
 	   				redirect.setAnchor("section3");
 	   				redirect.addParameter("section", "3");
 	 	   			return (redirect);					
				} else {
					messages.add("noMatch3", new ActionMessage("error.products.empty"));
				} 
				break;
			}
			case 2: {
				actIngs = searchDao.findActiveIngredients(searchForm.getWildcardSection3(), searchForm.getKeywordsSection3());
				if (actIngs.size() > 0){
					session.setAttribute("section3keyword", searchForm.getKeywordsSection3());										
					session.setAttribute("activeIngredients", actIngs);
 	   				redirect.setAnchor("section3");
 	   				redirect.addParameter("section", "3");
 	 	   			return (redirect);					
				} else {
					messages.add("noMatch3", new ActionMessage("error.products.empty"));
				}
				break;
			}
			default:
				searchForm.setWildcardSection3("");
				searchForm.setKeywordsSection3("");
				break;
			}

			if (!messages.isEmpty()) {
					saveMessages(request, messages);
			}
			
			//BeanUtils.copyProperties(searchForm, session.getAttribute(ApplicationGlobals.SEARCH_CRITERIA));
			forward = mapping.findForward("backToSearch");  	   		
   		} else {
			forward = mapping.findForward("sessionTimeout");   	   			
   		}
   		
   		return (forward);
 	}
 	
 	public ActionForward section4Find (ActionMapping mapping, ActionForm form,
 	 		HttpServletRequest request, HttpServletResponse response) throws Exception {
 	 		
 	   		ActionForward forward = new ActionForward(); // return value
			ActionMessages messages = new ActionMessages(); 	   		
 	   		HttpSession session = request.getSession(false);
 	   		SearchForm searchForm = (SearchForm) form;
 	   		SearchDAO searchDao = new SearchDAO();
 	   		List sysOrgClasses, preTerms;
 	   		
 	   		if (session.getAttribute("sessionActive") != null) {
 				String strLanguage = request.getParameter("lang");
 				Locale currentLocale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
 				
 				// Clean up session variable holder
 				if (Integer.valueOf(searchForm.getNameLevel()) == 0) {
 					// if Select All is selected (after the user has already searched once in Section 3)
 					session.removeAttribute("suspectedDrugs");
 					searchForm.setSection3results(null);
 					searchForm.setWildcardSection3("2");
 					searchForm.setKeywordsSection3("");
 				}
 				session.removeAttribute("systemOrganClasses");
 				session.removeAttribute("preferredTerms");
 				session.removeAttribute("section4keyword"); 				
 				searchForm.setSection4results(null);
 				
 				if ("eng".equals(strLanguage)) {
 					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
 					request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
 				} else if ("fra".equals(strLanguage)) {
 					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("fr","CA"));
 					request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
 				}
 				
 				searchForm.setSection4FindSearch(true);
 				
				// validate Search Form submission
 				ActionErrors errors = new ActionErrors();				   
 				errors = searchForm.validate(mapping, request);

 				if (!errors.isEmpty()) {
 					messages.add(errors);
 					saveMessages(request, messages);
 					searchForm.setSection4FindSearch(false);
 					return mapping.findForward("backToSearch");
 				}				   

 				ActionRedirect redirect = new ActionRedirect(mapping.findForwardConfig("anchor"));
 				if (strLanguage.equals("eng")){
 	   				redirect.addParameter("lang", "eng"); 	   					
 				} else {
 	   				redirect.addParameter("lang", "fra"); 	   					
 				}
 				
 				switch (Integer.valueOf(searchForm.getMeddraTermLevel())) {
 				case 1: {
 					sysOrgClasses = searchDao.findSOCs(searchForm.getWildcardSection4(), searchForm.getKeywordsSection4(), currentLocale);
 					if (sysOrgClasses.size() > 0){
 						session.setAttribute("section4keyword", searchForm.getKeywordsSection4()); 						 						
 	 					session.setAttribute("systemOrganClasses", sysOrgClasses);
 	 	   				redirect.setAnchor("section4");
 	 	   				redirect.addParameter("section", "4"); 	   				
 	 	 	   			return (redirect);  	 					
 					} else {
 						messages.add("noMatch4", new ActionMessage("error.meddra.empty"));
 					}
 					break;
 				}
 				case 2: {
 					preTerms = searchDao.findPTs(searchForm.getWildcardSection4(), searchForm.getKeywordsSection4(), currentLocale);
 					if (preTerms.size() > 0){
 						session.setAttribute("section4keyword", searchForm.getKeywordsSection4()); 						 						
 	 					session.setAttribute("preferredTerms", preTerms);	
 	 	   				redirect.setAnchor("section4");
 	 	   				redirect.addParameter("section", "4"); 	   				
 	 	 	   			return (redirect);  	 					
 					} else {
 						messages.add("noMatch4", new ActionMessage("error.meddra.empty"));
 					}
 					break;
 				}
 				default:
 					searchForm.setWildcardSection4("");
 					searchForm.setKeywordsSection4("");
 					break;
 				}

 				if (!messages.isEmpty()) {
 					saveMessages(request, messages);
 				}

 				forward = mapping.findForward("backToSearch");  	   		
 	   		} else {
 				forward = mapping.findForward("sessionTimeout");   	   			
 	   		}
 	   		
 	   		return (forward);
 	 	}
 	
 	// Set SearchCriteria display name on searchForm/all jsp pages
 	public void getNames(ActionForm form, SearchCriteria criteria, Locale locale){
 		SearchForm searchForm = (SearchForm) form;

 		if (searchForm.isFollowUpDateProvided()){
 			criteria.setInitialDateFrom("");
 			criteria.setInitialDateTo("");
 		} else {
 			criteria.setFollowUpDateFrom("");
 			criteria.setFollowUpDateTo("");
 		}
 		if (!searchForm.getSeriousnessCode().equals("0")){
 			criteria.setSeriousNameE(searchForm.getSeriousnessCode().split("\\,")[1].toString()); 			
 			criteria.setSeriousNameF(searchForm.getSeriousnessCode().split("\\,")[2].toString()); 			
 		} else {
 			criteria.setSeriousNameE("Both");
 			criteria.setSeriousNameF("Les deux"); 			
 		}
 		
 		if (!searchForm.getReportFeatureCode().equals("0")){
 			criteria.setReportFeatureNameE(searchForm.getReportFeatureCode().split("\\,")[1].toString());
 			criteria.setReportFeatureNameF(searchForm.getReportFeatureCode().split("\\,")[2].toString()); 			
 		} else {
 			criteria.setReportFeatureNameE("All");
 			criteria.setReportFeatureNameF("Toutes"); 			
 		}

 		if (!searchForm.getReportTypeCode().equals("0")){
 			criteria.setReportTypeNameE(searchForm.getReportTypeCode().split("\\,")[1].toString());
 			criteria.setReportTypeNameF(searchForm.getReportTypeCode().split("\\,")[2].toString()); 			
 		} else {
 			criteria.setReportTypeNameE("All");
 			criteria.setReportTypeNameF("Tous"); 	 			
 		}
 		
 		if (!searchForm.getReportSourceCode().equals("0")){
 			criteria.setReportSourceNameE(searchForm.getReportSourceCode().split("\\,")[1].toString());
 			criteria.setReportSourceNameF(searchForm.getReportSourceCode().split("\\,")[2].toString()); 			
 		} else {
 			criteria.setReportSourceNameE("All");
 			criteria.setReportSourceNameF("Toutes");  			
 		}
 		
 		if (!searchForm.getGenderCode().equals("0")){
 			criteria.setGenderNameE(searchForm.getGenderCode().split("\\,")[1].toString());
 			criteria.setGenderNameF(searchForm.getGenderCode().split("\\,")[2].toString()); 			
 		} else {
 			criteria.setGenderNameE("All");
 			criteria.setGenderNameF("Tous");   			
 		}
 		
 		if (!searchForm.getOutcomeCode().equals("0")){
 			criteria.setOutcomeNameE(searchForm.getOutcomeCode().split("\\,")[1].toString());
 			criteria.setOutcomeNameF(searchForm.getOutcomeCode().split("\\,")[2].toString()); 			
 		} else {
 			criteria.setOutcomeNameE("All");
 			criteria.setOutcomeNameF("Tous");   			
 		}

 		if ((searchForm.getAgeFrom().equals("0")) && (searchForm.getAgeTo().equals("0"))){
 			criteria.setAgeCritE("All"); 			
 			criteria.setAgeCritF("Tous"); 			
 		} else {
 			if (searchForm.getAgeTo().equals("0")) {
 	 			criteria.setAgeCritE(searchForm.getAgeFrom() + " - All");
 	 			criteria.setAgeCritF(searchForm.getAgeFrom() + " - Tous"); 				
 			} else {
 	 			criteria.setAgeCritE(searchForm.getAgeFrom() + " - " + searchForm.getAgeTo());
 	 			criteria.setAgeCritF(searchForm.getAgeFrom() + " - " + searchForm.getAgeTo()); 			
 			}
 		}
 		
 	}
  
}