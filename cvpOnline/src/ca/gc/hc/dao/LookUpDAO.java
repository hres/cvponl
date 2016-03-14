/*
 * Created on 26-Mar-08
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.struts.util.LabelValueBean;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import ca.gc.hc.model.Drug;
import ca.gc.hc.model.ReportDrug;
import ca.gc.hc.model.lookups.ReportLink;
import ca.gc.hc.model.lookups.Seriousness;
import ca.gc.hc.model.lookups.Gender;
import ca.gc.hc.model.lookups.Outcome;
import ca.gc.hc.model.lookups.ReportSource;
import ca.gc.hc.model.lookups.ReportType;


/**
 * @author Ahasan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LookUpDAO extends AbstractDao {

	public LookUpDAO() {
		super(Drug.class);
	}

	public List getDrugs (Locale locale){
		List drugs = null;
		try {
			drugs= getSession()
					.createCriteria(Drug.class).setFirstResult(25).setMaxResults(50)
					.addOrder(Order.asc("drugName"))
					.list();
		} catch (HibernateException ex) {
			System.out.println(ex);	
			ex.printStackTrace();			
		}		
		return drugs;
	}
	
	public List getSeriousness (Locale locale){
		List seriousness = null;
		try {
			if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {			
				seriousness= getSession()
						.createCriteria(Seriousness.class)
						.addOrder( Order.asc("seriousnessNameE"))						
						.list();
			}
			else if (locale.getDisplayLanguage() == Locale.CANADA_FRENCH.getDisplayLanguage()) {
				seriousness= getSession()
						.createCriteria(Seriousness.class)
						.addOrder( Order.asc("seriousnessNameF"))						
						.list();				
			}
			else {			
				seriousness= getSession()
				.createCriteria(Seriousness.class)
				.addOrder( Order.asc("seriousnessNameE"))						
				.list();
			}
		} catch (HibernateException ex) {
			System.out.println(ex);	
			ex.printStackTrace();			
		}			
		return seriousness;
	}
	
	public List getReportTypes (Locale locale){
		List reportTypes = null;
		try {
			if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {			
				reportTypes= getSession()
						.createCriteria(ReportType.class)
						.addOrder( Order.asc("reportTypeNameE"))						
						.list();
			}
			else if (locale.getDisplayLanguage() == Locale.CANADA_FRENCH.getDisplayLanguage()) {
				reportTypes= getSession()
						.createCriteria(ReportType.class)
						.addOrder( Order.asc("reportTypeNameF"))						
						.list();				
			}
			else {			
				reportTypes= getSession()
				.createCriteria(ReportType.class)
				.addOrder( Order.asc("reportTypeNameE"))						
				.list();
			}
		} catch (HibernateException ex) {
			System.out.println(ex);	
			ex.printStackTrace();			
		}			
		return reportTypes;
	}
	
	public List getReportSources (Locale locale){
		List reportSources = null;
		try {
			if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {			
				reportSources= getSession()
						.createCriteria(ReportSource.class)
						.addOrder( Order.asc("reportSourceNameE"))						
						.list();
			}
			else if (locale.getDisplayLanguage() == Locale.CANADA_FRENCH.getDisplayLanguage()) {
				reportSources= getSession()
						.createCriteria(ReportSource.class)
						.addOrder( Order.asc("reportSourceNameF"))						
						.list();				
			}
			else {			
				reportSources= getSession()
				.createCriteria(ReportSource.class)
				.addOrder( Order.asc("reportSourceNameE"))						
				.list();
			}
		} catch (HibernateException ex) {
			System.out.println(ex);	
			ex.printStackTrace();			
		}			
		return reportSources;
	}
	
	public List getGenders (Locale locale){
		List genders = null;
		try {
			if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {			
				genders= getSession()
						.createCriteria(Gender.class)
						.addOrder( Order.asc("genderNameE"))						
						.list();
			}
			else if (locale.getDisplayLanguage() == Locale.CANADA_FRENCH.getDisplayLanguage()) {
				genders= getSession()
						.createCriteria(Gender.class)
						.addOrder( Order.asc("genderNameF"))						
						.list();				
			}
			else {			
				genders= getSession()
				.createCriteria(Gender.class)
				.addOrder( Order.asc("genderNameE"))						
				.list();
			}
		} catch (HibernateException ex) {
			System.out.println(ex);	
			ex.printStackTrace();			
		}		
		return genders;
	}
	
	public List getOutcomes (Locale locale){
		List outcomes = null;
		try {
			if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {			
				outcomes= getSession()
						.createCriteria(Outcome.class)
						.addOrder( Order.asc("outcomeNameE"))						
						.list();
			}
			else if (locale.getDisplayLanguage() == Locale.CANADA_FRENCH.getDisplayLanguage()) {
				outcomes= getSession()
						.createCriteria(Outcome.class)
						.addOrder( Order.asc("outcomeNameF"))						
						.list();				
			}
			else {			
				outcomes= getSession()
				.createCriteria(Outcome.class)
				.addOrder( Order.asc("outcomeNameE"))						
				.list();
			}
		} catch (HibernateException ex) {
			System.out.println(ex);	
			ex.printStackTrace();			
		}				
		
		return outcomes;
	}
	
	/*
	 * manually created age dropdown list, not retrieved from the database
	 */
	public List getAgeDropdown (){
		List ageDropdown = new ArrayList();
		Long ageValue = new Long(0);
		for (int i=0; i < 150; i++){
			ageValue ++;
			ageDropdown.add(new LabelValueBean(ageValue.toString(), ageValue.toString()));
		}
		return ageDropdown;
	}	
	
	public List getReportLinksbyReportID(Long reportID){
		List reportLinks = null;
		try {
			reportLinks = getSession()
						.createCriteria(ReportLink.class)
						.add(Expression.eq("reportID", reportID))
						.addOrder( Order.asc("reportID"))
						.list();			
		} catch (HibernateException ex) {
			System.out.println(ex);	
			ex.printStackTrace();
		}
		
		return reportLinks;
	}
	
	public String getQuarter(){
		String quarter = "";
		String sql = "";
		Date maxDate = null;
		int year, month;

		Calendar cal = new GregorianCalendar();		
		
		sql = "SELECT MAX(r.datreceived) FROM reports r";		
		maxDate = (Date) getSession().createSQLQuery(sql).uniqueResult();
		cal.setTime(maxDate);
		
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		
		switch (month){
		case (1): case (2): case (3):
			quarter = String.valueOf(year) + "-03-" + "31"; 
			break;
		case (4): case (5): case (6):
			quarter = String.valueOf(year) + "-06-" + "30";			
			break;
		case (7): case (8): case (9):
			quarter = String.valueOf(year) + "-09-" + "30";			
			break;			
		case (10): case (11): case (12):
			quarter = String.valueOf(year) + "-12-" + "31";			
			break;
		}
		
		return quarter;
	}

	public String getMeddraVersion(){
		String meddraVersion = null;
		String sql = "";
		
		sql = "select max(t.meddra_version) from reactions t";
		meddraVersion = (String) getSession().createSQLQuery(sql).uniqueResult();
		
		return meddraVersion;
	}
	
}
