/*
 * Created on Apr 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.controller;

// Import Java IO classes.
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ca.gc.hc.dao.LookUpDAO;
import ca.gc.hc.util.ApplicationGlobals;
import ca.gc.hc.view.SearchForm;
/**
 * Action class that will pick the LOCALE paased from called application.
 */
public class StartupAction extends Action
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
		//creates a session if one does not exist	
		HttpSession session = request.getSession(true);
		request.getSession().setAttribute("sessionActive","Yes");
		try {
			
		   if (session != null){		
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
		   }
		   else  {
			   //default to english
			   session.setAttribute("org.apache.struts.action.LOCALE",new java.util.Locale("en","CA"));
		   }
				
	   } catch (Exception e) {	
		   // Report the error using the appropriate name and ID.
		   messages.add("name", new ActionMessage("id"));	
	   }
	
	   //If a message is required, save the specified key(s)
	   // into the request for use by the <struts:errors> tag.
	
	   if (!messages.isEmpty()) {
		   saveErrors(request, messages);
	
		   // Forward control to the appropriate 'failure' URI (change name as desired)
		   forward = mapping.findForward("failure");
	
		} else {
			//load the search page
			forward = mapping.findForward("searchPage");
		}
	   // return the appropriate page
		   return (forward);
	  }
}