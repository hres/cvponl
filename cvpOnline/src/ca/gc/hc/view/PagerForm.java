/*
 * Created on Apr 21, 2004

 */
package ca.gc.hc.view;

import org.apache.struts.action.ActionForm;

/**
 * @author tpdwebsp
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PagerForm extends ActionForm
{
	private String totalCount = null;
	private String totalPages = null;
	private int page = 1;
	private String pageSize = null;
	private String startIndex = null;
	private String endIndex = null;
	private String offset = null;
	private String passedPages = null;
	private String pagesLeft =  null;
	
	
  /**
   * @return
   */
  public String getEndIndex()
  {
    return endIndex;
  }

  /**
   * @return
   */
  public String getOffset()
  {
    return offset;
  }

  /**
   * @return
   */
  public int getPage()
  {
    return page;
  }

  /**
   * @return
   */
  public String getPageSize()
  {
    return pageSize;
  }

  /**
   * @return
   */
  public String getPagesLeft()
  {
    return pagesLeft;
  }

  /**
   * @return
   */
  public String getPassedPages()
  {
    return passedPages;
  }

  /**
   * @return
   */
  public String getStartIndex()
  {
    return startIndex;
  }

  /**
   * @return
   */
  public String getTotalCount()
  {
    return totalCount;
  }

  /**
   * @param string
   */
  public void setEndIndex(String string)
  {
    endIndex = string;
  }

  /**
   * @param string
   */
  public void setOffset(String string)
  {
    offset = string;
  }

  /**
   * @param string
   */
  public void setPage(int intPage)
  {
    page = intPage;
  }

  /**
   * @param string
   */
  public void setPageSize(String string)
  {
    pageSize = string;
  }

  /**
   * @param string
   */
  public void setPagesLeft(String string)
  {
    pagesLeft = string;
  }

  /**
   * @param string
   */
  public void setPassedPages(String string)
  {
    passedPages = string;
  }

  /**
   * @param string
   */
  public void setStartIndex(String string)
  {
    startIndex = string;
  }

  /**
   * @param string
   */
  public void setTotalCount(String string)
  {
    totalCount = string;
  }

  /**
   * @return
   */
  public String getTotalPages()
  {
    return totalPages;
  }

  /**
   * @param string
   */
  public void setTotalPages(String string)
  {
    totalPages = string;
  }

}
