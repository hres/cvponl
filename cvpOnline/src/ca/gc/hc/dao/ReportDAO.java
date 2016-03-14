/*
 * Created on 2-April-09
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.dao;

import org.hibernate.HibernateException;

import ca.gc.hc.model.Report;


/**
 * @author Ahasan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportDAO extends AbstractDao {

	public ReportDAO() {
		super(Report.class);
	}

	public Report getReportbyID(Long reportID){
	    return (Report)super.findByIdBase(reportID, true);	    
	}

	public Report getReportByReportNumber (String reportNumber) throws Exception{
		Report report = null;
		String sql;
		try{
			sql = "SELECT {r.*} FROM reports r" ;
			sql += " WHERE r.report_no = " + "'" + reportNumber.trim() + "'";
        	
			report = (Report) getSession().createSQLQuery(sql)
										.addEntity("r", Report.class)
										.uniqueResult();
		} catch (HibernateException he) {
				StringBuffer message = new StringBuffer(
				"getReportByReportNumber [");
				message.append(reportNumber);
				message.append("] failed");
				throw new Exception(message.toString());
		} 
		return report;	
	}
	
}
