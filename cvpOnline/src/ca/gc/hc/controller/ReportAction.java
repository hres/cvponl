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
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import ca.gc.hc.dao.LookUpDAO;
import ca.gc.hc.dao.ReactionDAO;
import ca.gc.hc.dao.ReportDAO;
import ca.gc.hc.dao.ReportDrugDAO;
import ca.gc.hc.model.Report;
import ca.gc.hc.util.ApplicationGlobals;
/**
 * Action class that will populate the search criteria into the search bean and then send to
 * the search dao for retrieval of object results
 */
public class ReportAction extends LookupDispatchAction {
	/***************************************************************************
	 * Maps the resources used for the button text to the related action methods.
	 */
   	protected Map getKeyMethodMap() {
		Map<String, String> buttonActions = new HashMap<String, String>();
        
		buttonActions.put("button.newSearch", "dispatchNewSearch");		
		buttonActions.put("button.backResult", "dispatchBack");
		return buttonActions;   		
	}

	private String strLanguage = null;
	
	public ReportAction(){
		super();
	}

	public ActionForward execute(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws Exception {
		ActionForward forward = new ActionForward();
		ActionMessages messages = new ActionMessages();        
		HttpSession session = request.getSession(true);
		String method = request.getParameter("action");
		
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

			if (request.getParameter("id") != null){
				
				// Accepting request parameter from search action
				String reportId = request.getParameter("id");				
				
				// Clean up all session variables
				session.removeAttribute("report");
				session.removeAttribute("death");
				session.removeAttribute("lifeThreat");
				session.removeAttribute("disability");
				session.removeAttribute("hospital");
				session.removeAttribute("congenital");
				session.removeAttribute("otherMedical");
				session.removeAttribute("reportLink");
				session.removeAttribute("reportDrugs");
				session.removeAttribute("reactions");
				
				ReportDAO repDao = new ReportDAO();
				LookUpDAO repLinkDao = new LookUpDAO();
				ReactionDAO reactDao = new ReactionDAO();
				ReportDrugDAO repdrugDao = new ReportDrugDAO();
				Report rep = repDao.getReportByReportNumber(reportId);
//				ReportLink repLink = repLinkDao.getReportLinkbyReportID(Long.valueOf(reportId));
//				ReportDrugForm rdForm = new ReportDrugForm();
				
//				List reportDrugs = repdrugDao.getDrugsByReportID(reportId);
//				Object[] rdarray = reportDrugs.toArray();
//				int test = rdarray.length;
//				List<ReportDrugForm> reportDrugForms = new ArrayList<ReportDrugForm>();
//				for (int i = 0; i < test; i++){
//					ReportDrug rd = (ReportDrug) rdarray[i];
//					rdForm = ActionFormMapper.convertRepDrugToForm(rd, rdForm);
//					reportDrugForms.add(i, rdForm);
//				}
//				session.setAttribute("reportDrugForms", reportDrugForms);
//				
//				List reactions = reactDao.getReactionsByReportID(reportId);
				session.setAttribute("report", rep);
				if ((rep.getSeriousnessName().equals("Yes")) || (rep.getSeriousnessName().equals("Oui"))){
					session.setAttribute("serious", "1");					
				} else {
					session.removeAttribute("serious");
				}
				session.setAttribute("death", rep.getDeath());
				session.setAttribute("lifeThreat", rep.getLifeThreatening());
				session.setAttribute("disability", rep.getDisability());
				session.setAttribute("hospital", rep.getHospitalRequired());
				session.setAttribute("congenital", rep.getCongenitalAnomaly());
				session.setAttribute("otherMedical", rep.getOtherMedicalCondition());
 				session.setAttribute("reportLinks", repLinkDao.getReportLinksbyReportID(rep.getReportID()));
				session.setAttribute("reportDrugs", repdrugDao.getDrugsByReportID(rep.getReportID()));
				session.setAttribute("reactions", reactDao.getReactionsByReportID(rep.getReportID()));
				//forward = new ActionForward("t.reportInfo");
				forward = mapping.findForward("success");
			}else{
				if (this.isCancelled(request)){
		    		return mapping.findForward("backToSearch");
		      	}
				return super.execute(mapping, form, request, response);
			} 				
		} else {
			forward = mapping.findForward("sessionTimeout");
		}
		return (forward);
	  }
  

	// This method is not used, but kept just in case.
   	public ActionForward dispatchBack (ActionMapping mapping, ActionForm form,
   		    HttpServletRequest request, HttpServletResponse response)
   		    throws Exception {

   			ActionForward forward = new ActionForward(); // return value
   	   		HttpSession session = request.getSession(false);  
   	   		//SearchForm searchForm = (SearchForm) form;
   	   		if (session.getAttribute("sessionActive") != null){
				String strLanguage = request.getParameter("lang");	
				if ("eng".equals(strLanguage)){
					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
					request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
				} else if ("fra".equals(strLanguage)){
					session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("fr","CA"));
					request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
				}
				//BeanUtils.copyProperties(searchForm, session.getAttribute(ApplicationGlobals.SEARCH_CRITERIA));
   	   	   		forward = mapping.findForward("backToSearchResults");  	   		
   	   		} else {
				forward = mapping.findForward("sessionTimeout");   	   			
   	   		}
   	 	return (forward);   		
   	 }

   	public ActionForward dispatchNewSearch (ActionMapping mapping, ActionForm form,
   		    HttpServletRequest request, HttpServletResponse response) throws Exception {
   		
   	   		ActionForward forward = new ActionForward(); // return value
   	   		HttpSession session = request.getSession(false);
   	   		if (session.getAttribute("sessionActive") != null){
	   	   		if (session.getAttribute("sessionActive") != null){
					String strLanguage = request.getParameter("lang");	
					if ("eng".equals(strLanguage)){
						session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
						request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
					} else if ("fra".equals(strLanguage)){
						session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("fr","CA"));
						request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
					}
	   	   		}
 				session.removeAttribute(ApplicationGlobals.SEARCH_RESULTS);
 				session.removeAttribute(ApplicationGlobals.SEARCH_RESULT_KEY);	   	   		
   	   			forward = mapping.findForward("newSearch");  
   	   		} else {
				forward = mapping.findForward("sessionTimeout");   	   			
   	   		}
   	 	return (forward);   		
   	 }   	
  
}