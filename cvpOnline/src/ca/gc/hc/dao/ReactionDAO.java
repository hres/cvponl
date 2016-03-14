/*
 * Created on 2-April-09
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.dao;

import java.util.List;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import ca.gc.hc.model.Reaction;


/**
 * @author Ahasan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReactionDAO extends AbstractDao {

	public ReactionDAO() {
		super(Reaction.class);
	}

	public Reaction getReactionByID(Long reactionID, boolean lock){
//		Report report = null;
//		try {
//			report = (Report) getSession().get(Report.class, reportID);
//		} catch (HibernateException e) {
//		} 
//		return report;	
	    return (Reaction)super.findByIdBase(reactionID, lock);	    
	}
	
	public List getReactionsByReportID(Long reportID){
		List reactions = null;
		//if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {
			reactions = getSession()
						.createCriteria(Reaction.class)
						.add(Expression.eq("reportID", reportID))
						.addOrder( Order.asc("ptNameE"))
						.list();			
//		} else {
//			reactions = getSession()
//						.createCriteria(Reaction.class)
//						.add(Expression.eq("reportID", reportID))
//						.addOrder( Order.asc("ptNameF"))
//						.list();			
//		}
		return reactions;
	}
	
}
