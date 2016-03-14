/*
 * Created on Apr 21, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ca.gc.hc.dao.SearchDAO;
import ca.gc.hc.util.ActionUtil;
import ca.gc.hc.util.ApplicationGlobals;
import ca.gc.hc.util.SearchCriteria;
import ca.gc.hc.view.PagerForm;

/**
 * @author tpdwebsp
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayNextPageAction extends Action
{

 
  /**
   * Process the specified HTTP request, and create the corresponding
   * non-HTTP response (or forward to another web component that will create
   * it).  Return an <code>ActionForward</code> instance describing where
   * and how control should be forwarded, or <code>null</code> if the
   * response has already been completed.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param actionForm The optional ActionForm bean for this request (if any)
   * @param request The non-HTTP request we are processing
   * @param response The non-HTTP response we are creating
   *
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet exception occurs    
   *            
   */
  public ActionForward execute(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
	  ActionForward forward = new ActionForward();
	  HttpSession session = request.getSession(false);
	  PagerForm thisForm = (PagerForm) form;
	  int sortValue = 1;
	  Locale locale = (Locale)session.getAttribute("org.apache.struts.action.LOCALE");	 
	  List searchResults = null;
	  
	  if (session.getAttribute("sessionActive") != null){
		  
		  if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
			  System.out.println("in NEXT - PAGE NUMBER : " + thisForm.getPage());
			  System.out.println("in NEXT - Next Query Boolean : " + ApplicationGlobals.NEXT_QUERY);
		  }
		  
		  if (ApplicationGlobals.NEXT_QUERY){
			  // need to fetch the next block of 100 records
			  SearchDAO searchDAO = new SearchDAO();
			  SearchCriteria crit = (SearchCriteria) session.getAttribute("SearchCriteria");
			  try {
				  session.removeAttribute(ApplicationGlobals.SEARCH_RESULTS);
				   if (session.getAttribute(ApplicationGlobals.SORT_PARAMETER) != null){
					   sortValue = Integer.valueOf((String) session.getAttribute(ApplicationGlobals.SORT_PARAMETER)).intValue();				   
				   }
	  			ApplicationGlobals.RESULTS_BLOCK = ApplicationGlobals.RESULTS_BLOCK + 1;
	  			long objSize = Runtime.getRuntime().freeMemory();
				searchResults = searchDAO.searchByReportQuery(crit, locale, 20, (thisForm.getPage()+1), sortValue);
				objSize = objSize - Runtime.getRuntime().freeMemory();  
				
				if (ApplicationGlobals.DEBUG == "memory" || ApplicationGlobals.DEBUG == "all"){
					System.out.println("@@@@ Next Java Object Size : " + objSize);
					System.out.println("@@@@ Next free memory : " + Runtime.getRuntime().freeMemory());
				}
				
			  } catch (Exception e) {
				  e.printStackTrace();
			  }
			  
			  long objSize = Runtime.getRuntime().freeMemory();
			  session.setAttribute(ApplicationGlobals.SEARCH_RESULTS, searchResults);
			  objSize = objSize - Runtime.getRuntime().freeMemory();  
			  
			  if (ApplicationGlobals.DEBUG == "memory" || ApplicationGlobals.DEBUG == "all"){
				  System.out.println("@@@@ Next Session Object Size : " + objSize);
			  }
		  }
		  
		    ActionUtil.setPager(
		      mapping,
		      request,
		      thisForm,
			ApplicationGlobals.NEXT_PAGE_ACTION);
		
		  forward = mapping.findForward("success");
	  }
	  else
	  {
		  forward = mapping.findForward("sessionTimeout");
  	  }
	  //	set the page number it is at.	  
	  session.setAttribute(ApplicationGlobals.SEARCH_RESULT_PAGE_NUMBER, String.valueOf(thisForm.getPage()));
	  
	  return forward;
  }
}
