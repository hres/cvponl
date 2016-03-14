/*
 * Created on 2-April-09
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import ca.gc.hc.model.ReportDrug;


/**
 * @author Ahasan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportDrugDAO extends AbstractDao {

	public ReportDrugDAO() {
		super(ReportDrug.class);
	}

	public ReportDrug getReportbyID(Long drugID, boolean lock){
	    return (ReportDrug)super.findByIdBase(drugID, lock);	    
	}
	
	/* get drugnames from the Suspect Drugs view ReportDrugMV*/
	public String getSuspectDrugNamesByReportID(Long reportID){
		String drugNames = "";
		String hql = "select rd.drugName from ReportDrugMV rd where rd.reportID = " + reportID;
		hql += " order by rd.drugName ";
		Iterator it = getSession().createQuery(hql).list().iterator();
		while (it.hasNext()){
			drugNames += it.next().toString() + "<br/>";
		}
		return drugNames;
	}
	
	public List getDrugsByReportID(Long reportID){
		List reportDrugs = null;
		reportDrugs = getSession()
					.createCriteria(ReportDrug.class)
					.add(Expression.eq("reportID", reportID))
					.addOrder( Order.asc("drugName"))
					.list();		
		return reportDrugs;
	}
	
}
