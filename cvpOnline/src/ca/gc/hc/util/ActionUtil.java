/*
 * Created on Apr 21, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.util;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import ca.gc.hc.view.PagerForm;

/**
 * Untility class for the front end.
 */
public class ActionUtil
{
  //Get an instance for log4j
  private static Logger logger = Logger.getLogger(ActionUtil.class);

  /*
   * Help method to populate the page form that control the behavior of the 
   * page navigation.
   */
  public static void setPager(
    ActionMapping mapping,
    HttpServletRequest request,
    PagerForm myForm,
    int actionType)
  {
	int pageNumber = 0;
	int remainder = 0;
    long endIndex;
    long listSize;
    ApplicationGlobals.NEXT_QUERY = false;
    ApplicationGlobals.PREVIOUS_QUERY = false;
	
//    listSize =
//      ((ArrayList) request
//        .getSession()
//        .getAttribute(ApplicationGlobals.SEARCH_RESULTS))
//        .size();
    
    listSize =
        ((Integer) request
          .getSession()
          .getAttribute(ApplicationGlobals.SEARCH_RESULTS_SIZE))
          .longValue();
        
	long lngPageSize = 20;

    long totalPages = listSize / lngPageSize;
    if ((listSize % lngPageSize) > 0)
    {
      totalPages++;
    }

    int inputPageNumber = myForm.getPage();

    if (actionType == ApplicationGlobals.INITIAL_ACTION)
    {
      pageNumber = inputPageNumber;
    }
    else if (actionType == ApplicationGlobals.PREVIOUS_PAGE_ACTION)
    {
      if (inputPageNumber > 1)
        pageNumber = inputPageNumber - 1;
    }
    else if (actionType == ApplicationGlobals.NEXT_PAGE_ACTION)
    {
      if (inputPageNumber < totalPages)
        pageNumber = inputPageNumber + 1;
    }

    long offset = lngPageSize * (pageNumber - 1);
    long startIndex = offset + 1;

    if (pageNumber < totalPages)
    {
      endIndex = startIndex + lngPageSize - 1;
    }
    else
    {
      endIndex = listSize;
    }

    myForm.setTotalCount(new Long(listSize).toString());
    myForm.setTotalPages(new Long(totalPages).toString());
    myForm.setPage(pageNumber);
    myForm.setOffset(new Long(offset).toString());
    myForm.setPageSize(new Long(lngPageSize).toString());
    myForm.setStartIndex(new Long(startIndex).toString());
    myForm.setEndIndex(new Long(endIndex).toString());
    myForm.setPagesLeft(new Long(totalPages - pageNumber).toString());
    myForm.setPassedPages(new Long(pageNumber - 1).toString());
    
    if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
		System.out.println("ActionUtil PAGE NUMBER: " + pageNumber);
		System.out.println("ActionUtil Offset: " + offset);
	}
	
    if (pageNumber != 1) {
        remainder = Long.valueOf((pageNumber * lngPageSize) % 100).intValue();
        // for display purposes, must adjust the offset appropriately for every resultset
        switch (remainder){
    	    case 0:
    	    	if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
    	    		System.out.println("0 Remainder : " + remainder);
    	    	}
            	myForm.setOffset("80");
            	// the next link will require to get the next batch of records
            	ApplicationGlobals.NEXT_QUERY = true;	    	
    	    	break;
    	    case 20:
    	    	if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
    	    		System.out.println("20 Remainder : " + remainder);
    	    	}
            	myForm.setOffset("0");	  
            	// the previous link will require to get the previous batch of records            	
            	ApplicationGlobals.PREVIOUS_QUERY = true;
    	    	break;
    	    case 40:
    	    	if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
    	    		System.out.println("40 Remainder : " + remainder);
    	    	}
            	myForm.setOffset("20");	    	
    	    	break;
    	    case 60:
    	    	if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
    	    		System.out.println("60 Remainder : " + remainder);
  			  	}
            	myForm.setOffset("40");	    	
    	    	break;
    	    case 80:
    	    	if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
  			  		System.out.println("80 Remainder : " + remainder);
  			  	}
            	myForm.setOffset("60");	    	
    	    	break;
        }        
    }
    
    if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
		System.out.println("ActionUtil - Next Query Boolean : " + ApplicationGlobals.NEXT_QUERY);
		System.out.println("ActionUtil - Previous Query Boolean : " + ApplicationGlobals.PREVIOUS_QUERY);    
	}
	
	request.getSession().setAttribute(ApplicationGlobals.PAGER_FORM, myForm);
  }

}
