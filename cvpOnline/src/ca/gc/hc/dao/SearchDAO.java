/*
 * Created on 19-Mar-09
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import ca.gc.hc.model.ActiveIngredient;
import ca.gc.hc.model.Report;
import ca.gc.hc.model.lookups.MeddraPT;
import ca.gc.hc.model.lookups.MeddraSOC;
import ca.gc.hc.util.ApplicationGlobals;
import ca.gc.hc.util.SearchCriteria;


/**
 * @author Ahasan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchDAO extends AbstractDao {

	public SearchDAO() {
		super(SearchCriteria.class);
	}
	
	private static Log log = LogFactory.getLog(SearchDAO.class);	
	
	public int countRow(SearchCriteria crit, Locale locale){
		// note that this count includes ALL of the drugs associated to the report, not just the Suspect ones
		int recordCount = 0;
		String sql = "";
		String sqlFromWhere = "";
		String sqlCount = "SELECT count (r.ADR_ID) ";
		sqlFromWhere = sqlRowFromWhere(crit, locale);
		sql = sqlCount + sqlFromWhere;
		BigDecimal bigDCount = new BigDecimal(0.0);
		
		double startTimeStamp = System.currentTimeMillis();
		bigDCount = (BigDecimal)getSession().createSQLQuery(sql).uniqueResult();		
		//System.out.println("@@@@ Oracle Row Count Time: " + (System.currentTimeMillis()-startTimeStamp) + " ms");
		recordCount = bigDCount.intValue();
		//System.out.println("ROW COUNT  : " + recordCount);
		return recordCount;
	}
	
	public int countSearch(SearchCriteria crit, Locale locale){
		int recordCount = 0;
		String sql = "";
		String sqlFromWhere = "";
		String sqlCount = "SELECT count (DISTINCT r.REPORT_ID) ";
		sqlFromWhere = sqlFromWhere(crit, locale);
		sql = sqlCount + sqlFromWhere;
		BigDecimal bigDCount = new BigDecimal(0.0);
		
		double startTimeStamp = System.currentTimeMillis();
		bigDCount = (BigDecimal)getSession().createSQLQuery(sql).uniqueResult();		
		//System.out.println("@@@@ Oracle Report Count Time: " + (System.currentTimeMillis()-startTimeStamp) + " ms");
		recordCount = bigDCount.intValue();
		//System.out.println("CASE COUNT : " + recordCount);
		return recordCount;
	}

	public List searchByRowQuery(SearchCriteria criteria, Locale locale, int pageSize, int pageNumber, int sortField)
		throws Exception {
		List reports = new ArrayList();
		String sql = "";
		String sqlFromWhere = "";
		String sqlSelect = "";	
		try {
			
			/* directly selecting from columns */
			/* IMPORTANT to maintain the order of the select columns below, as other areas rely on it */
			/* r.REPORT_FEATURE_ENG, r.REPORT_FEATURE_FR,*/
			sqlSelect = "SELECT r.ADR_ID, r.REPORT_ID, r.REPORT_NO, r.VERSION_NO, r.DATRECEIVED, " +
			"r.DATINTRECEIVED, r.MAH_NO,      r.REPORT_TYPE_ENG, r.REPORT_TYPE_FR, r.GENDER_ENG, " +
			"r.GENDER_FR, r.AGE, r.AGE_UNIT_ENG, r.AGE_UNIT_FR, r.AGE_GROUP_CODE, r.OUTCOME_ENG, r.OUTCOME_FR, r.WEIGHT, r.WEIGHT_UNIT_ENG, " +
			"r.WEIGHT_UNIT_FR, r.HEIGHT, r.HEIGHT_UNIT_ENG, r.HEIGHT_UNIT_FR, r.SERIOUSNESS_ENG, r.SERIOUSNESS_FR, r.DEATH, r.DISABILITY, " +
			"r.CONGENITAL_ANOMALY, r.LIFE_THREATENING, r.HOSP_REQUIRED, r.OTHER_MEDICALLY_IMP_COND, r.REPORTER_TYPE_ENG, r.REPORTER_TYPE_FR, " +
			"r.SOURCE_ENG, r.SOURCE_FR, r.REPORT_LINK_FLG, r.DURATION, r.DURATION_UNIT_ENG, r.DURATION_UNIT_FR, r.PT_NAME_ENG, " +
			"r.PT_NAME_FR, r.SOC_NAME_ENG, r.SOC_NAME_FR, r.MEDDRA_VERSION, rd.REPORT_DRUG_ID, rd.DRUGNAME, rd.DRUGINVOLV_ENG, rd.DRUGINVOLV_FR, " +
			"rd.ROUTEADMIN_ENG, rd.ROUTEADMIN_FR, rd.UNIT_DOSE_QTY, rd.DOSE_UNIT_ENG, rd.DOSE_UNIT_FR, rd.FREQUENCY, rd.FREQ_TIME_UNIT_ENG, " +
			"rd.FREQ_TIME_UNIT_FR, rd.THERAPY_DURATION, rd.THERAPY_DURATION_UNIT_ENG, rd.THERAPY_DURATION_UNIT_FR, " +
			"rd.DOSAGEFORM_ENG, rd.DOSAGEFORM_FR, rd.DRUG_PRODUCT_ID, rd.FREQ_TIME, rd.FREQUENCY_TIME_ENG, rd.FREQUENCY_TIME_FR, r.AGE_GROUP_ENG, r.AGE_GROUP_FR, " +
			"rl.REPORT_LINK, rl.RECORD_TYPE_ENG, rl.RECORD_TYPE_FR, rd.INDICATION_NAME_ENG, rd.INDICATION_NAME_FR ";

			sqlFromWhere =  sqlRowFromWhere(criteria, locale);
			
			sql = sqlSelect + sqlFromWhere +  " ORDER BY r.report_id, r.datreceived ";
			log.debug("SQL Report Query: " + sql);
			//System.out.println("SQL Report Query: " + sql);

			double startTimeStamp = System.currentTimeMillis();
			reports = getSession().createSQLQuery(sql).list();
			//System.out.println("@@@@ Oracle Report Return Time: " + (System.currentTimeMillis()-startTimeStamp)/1000 + " second");
			
			ApplicationGlobals.EXECUTION_TIME = System.currentTimeMillis();
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Stack Trace: ", e);
			StringBuffer message = new StringBuffer("SearchDAO searchByRowQuery failed");
			throw new Exception(message.toString());
		}
		
		if (ApplicationGlobals.DEBUG == "hibernate" || ApplicationGlobals.DEBUG == "all"){
			System.out.println("First Hibernate execution time ---> " + (System.currentTimeMillis() - ApplicationGlobals.EXECUTION_TIME) + " ms");
			System.out.println("SIZE : " + reports.size());
		}
		
		return reports;
	}
	
	public List searchByReportQuery(SearchCriteria criteria, Locale locale, int pageSize, int pageNumber, int sortField)
	throws Exception {
		List reports = new ArrayList();
		String sql = "";
		String sqlFromWhere = "";
		String sqlSelect = "";	
		
		try {
			
			/* directly selecting from columns */
			/* IMPORTANT to maintain the order of the select columns below, as other areas rely on it */

			//OLD sqlSelect = "SELECT r.REPORT_ID" ;
			sqlSelect = "SELECT {rp.*} FROM REPORTS rp WHERE rp.REPORT_ID IN ( SELECT DISTINCT r.REPORT_ID " ;
	
			sqlFromWhere =  sqlFromWhere(criteria, locale);
			
			//OLD sql = sqlSelect + sqlFromWhere + orderByClause(sortField);
			sql = sqlSelect + sqlFromWhere + ")" + orderByClause(sortField);
			
			log.debug("SQL Report Query: " + sql);
			System.out.println("SQL Report Query: " + sql);
	
			double startTimeStamp = System.currentTimeMillis();
			//System.out.println("CURRENT TIME START ---- " + System.currentTimeMillis());
			//OLD reports = getSession().createSQLQuery(sql).addEntity("rp", Report.class).list();			
			Query query = getSession().createSQLQuery(sql).addEntity("rp", Report.class);
			
			if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
				System.out.println("SORT BY COLUMN : " + ApplicationGlobals.SORT_BY_COLUMN);
				System.out.println("BLOCK NUMBER : " + ApplicationGlobals.RESULTS_BLOCK);
			}
			
			// paging the results by blocks of 100 records
			if (ApplicationGlobals.SORT_BY_COLUMN){
				query.setFirstResult(100 * (ApplicationGlobals.RESULTS_BLOCK - 1));				
			} else {
				query.setFirstResult(pageSize * (pageNumber - 1));				
			}
			query.setMaxResults(pageSize * 5);
			long objSize = Runtime.getRuntime().freeMemory();    			 
			reports = query.list();
			objSize = objSize - Runtime.getRuntime().freeMemory();

			if (ApplicationGlobals.DEBUG == "memory" || ApplicationGlobals.DEBUG == "all"){
				System.out.println("#### DAO Object Size : " + objSize);
				System.out.println("#### DAO free memory : " + Runtime.getRuntime().freeMemory());
			}

			Runtime.getRuntime().gc();
			
			if (ApplicationGlobals.DEBUG == "paging" || ApplicationGlobals.DEBUG == "all"){
				Iterator it = reports.iterator();
				Report report = (Report) it.next();
				System.out.println("DAO - Page Number: " + pageNumber);
				System.out.println("Report First Record ID -- " + report.getReportID());
			}

			System.out.println("@@@@ Oracle Search Time: " + (System.currentTimeMillis()-startTimeStamp)/1000 + " second");
			ApplicationGlobals.EXECUTION_TIME = System.currentTimeMillis();
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Stack Trace: ", e);
			StringBuffer message = new StringBuffer("SearchDAO searchByReportQuery failed");
			throw new Exception(message.toString());
		}
		
		if (ApplicationGlobals.DEBUG == "hibernate" || ApplicationGlobals.DEBUG == "all"){
			System.out.println("First Hibernate execution time ---> " + (System.currentTimeMillis() - ApplicationGlobals.EXECUTION_TIME) + " ms");
			System.out.println("SIZE : " + reports.size());
		}
		
		return reports;		
	}
	
	private String sqlFromWhere(SearchCriteria crit, Locale locale){
		String sqlFromWhere = "";
		String sqlFrom = "";
		String sqlWhere = "";
		sqlFrom = "from ADR_MV r, REPORT_DRUGS_MV rd ";
		
		// Section 1 of Search
		if (crit.isInitialDateProvided()){
			// set initial received date range
			sqlWhere = "WHERE ";
			sqlWhere += "r.datintreceived BETWEEN TO_DATE('" + crit.getInitialDateFrom() + "', 'YYYY/MM/DD') AND TO_DATE('" + crit.getInitialDateTo() + "', 'YYYY/MM/DD') ";
		}
		if (crit.isFollowUpDateProvided()){
			// set latest received date range
			sqlWhere = "WHERE ";			
			sqlWhere += "r.datreceived BETWEEN TO_DATE('" + crit.getFollowUpDateFrom() + "', 'YYYY/MM/DD') AND TO_DATE('" + crit.getFollowUpDateTo() + "', 'YYYY/MM/DD') ";			
		}
		if (!crit.getSeriousnessCode().equals("0")) {
			sqlWhere += "AND r.seriousness_code = '" + crit.getCode(crit.getSeriousnessCode()) + "' ";
		}
//		if (!crit.getReportFeatureCode().equals("0")) {
//			sqlWhere += "AND r.report_feature_code = '" + crit.getCode(crit.getReportFeatureCode()) + "' ";			
//		}
//		if (!crit.getReportTypeCode().equals("0")) {
//			sqlWhere += "AND r.report_type_code = '" + crit.getCode(crit.getReportTypeCode()) + "' ";			
//		}
		if (!crit.getReportSourceCode().equals("0")) {
			sqlWhere += "AND r.source_code = '" + crit.getCode(crit.getReportSourceCode()) + "' ";			
		}
		// Section 2 of Search
		if (!crit.getGenderCode().equals("0")) {
			sqlWhere += "AND r.gender_code = '" + crit.getCode(crit.getGenderCode()) + "' ";			
		}
		if (!crit.getOutcomeCode().equals("0")) {
			sqlWhere += "AND r.outcome_code = '" + crit.getCode(crit.getOutcomeCode()) + "' ";			
		}
		if (!crit.getAgeFrom().equals("0")){
			// check if Age To is set to all
			if (crit.getAgeTo().equals("0")){
				// then inlude blank ages as well
				sqlWhere += "AND (r.age_y >= " + crit.getAgeFrom() + " ";				
				sqlWhere += "OR r.age_y is null) ";
			} else {
				sqlWhere += "AND r.age_y >= " + crit.getAgeFrom() + " ";				
			}
		}
		if (!crit.getAgeTo().equals("0")){
			if (crit.getAgeFrom().equals("0")){
				// this will ensure that blank ages are not included
				sqlWhere += "AND r.age_y >= 0 ";				
			}
			sqlWhere += "AND r.age_y <= " + crit.getAgeTo() + " ";
		}
		// Section 3 of Search - USING VIEWS
		if (!crit.getNameLevel().equals("0")) {
			if ((crit.getNameLevel().equals(ApplicationGlobals.DRUG_PRODUCT)) && (!crit.getKeywordsSection3().equals("")) ){
				ApplicationGlobals.DRUG_SEARCH = true; 
				if (crit.getSection3results()[0].equals("0")){
					// Select All (terms)
					sqlFrom += ", (SELECT DISTINCT report_id COL1 from REPORT_DRUGS_MV where UPPER(DRUGNAME) IN (SELECT DISTINCT dp.DRUGNAME FROM DRUG_PRODUCTS dp where dp.DRUGNAME LIKE UPPER(" + sqlCriteria(crit, "3") + "))) TEMP1 ";					
				} else {
					// Selected multiple terms
					sqlFrom += ", (SELECT DISTINCT report_id COL1 from REPORT_DRUGS_MV where UPPER(DRUGNAME) IN (SELECT DISTINCT dp.DRUGNAME FROM DRUG_PRODUCTS dp where dp.DRUGNAME IN (" + crit.getSQLString(crit.getSection3results()) + "))) TEMP1 ";					
				}
			}
			if ((crit.getNameLevel().equals(ApplicationGlobals.INGREDIENT)) && (!crit.getKeywordsSection3().equals(""))){
				ApplicationGlobals.INGREDIENT_SEARCH = true;
				if (crit.getSection3results()[0].equals("0")){
					// Select All (terms)					
					sqlFrom += ", (SELECT DISTINCT reportDrug.REPORT_ID COL1 FROM REPORT_DRUGS_MV reportDrug where UPPER(reportDrug.DRUGNAME) IN (SELECT DISTINCT dpi.DRUGNAME FROM DRUG_PRODUCT_INGREDIENTS dpi WHERE UPPER(dpi.ACTIVE_INGREDIENT_NAME) LIKE UPPER(" + sqlCriteria(crit, "3") + "))) TEMP1 ";					
				} else {
					// Selected multiple terms
					sqlFrom += ", (SELECT DISTINCT reportDrug.REPORT_ID COL1 FROM REPORT_DRUGS_MV reportDrug where UPPER(reportDrug.DRUGNAME) IN (SELECT DISTINCT dpi.DRUGNAME FROM DRUG_PRODUCT_INGREDIENTS dpi WHERE dpi.ACTIVE_INGREDIENT_NAME IN (" + crit.getSQLString(crit.getSection3results()) + "))) TEMP1 ";					
				}
			}
			sqlWhere += "AND r.REPORT_ID = TEMP1.COL1 ";			
		}
		// Section 4 of Search - USING VIEWS
		if (!crit.getMeddraTermLevel().equals("0")){
			if ((crit.getMeddraTermLevel().equals(ApplicationGlobals.PT)) && (!crit.getKeywordsSection4().equals("")) ){
				// PT Search
				if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {
					if (crit.getSection4results()[0].equals("0")){
						// Select All (terms)
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where UPPER(pt_name_eng) LIKE UPPER("  + sqlCriteria(crit, "4") + ")) TEMP2 ";						
					} else {
						// Selected multiple terms
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where pt_name_eng IN(" + crit.getSQLString(crit.getSection4results()) + ")) TEMP2 ";					
					}
				} else {
					// french
					if (crit.getSection4results()[0].equals("0")){
						// Select All (terms)
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where UPPER(pt_name_fr) LIKE UPPER("  + sqlCriteria(crit, "4") + ")) TEMP2 ";						
					} else {
						// Selected multiple terms
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where pt_name_fr IN(" + crit.getSQLString(crit.getSection4results()) + ")) TEMP2 ";					
					}
				}
				sqlWhere += "AND r.REPORT_ID = TEMP2.COL2 ";				
			} else {
				// SOC Search
				if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {
					if (crit.getSection4results()[0].equals("0")){
						// Select All (terms)
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where UPPER(soc_name_eng) LIKE UPPER("  + sqlCriteria(crit, "4") + ")) TEMP2 ";							
					} else {
						// Selected multiple terms
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where soc_name_eng IN (" + crit.getSQLString(crit.getSection4results()) + ")) TEMP2 ";
					}
				} else {
					// french
					if (crit.getSection4results()[0].equals("0")){
						// Select All (terms)
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where UPPER(soc_name_fr) LIKE UPPER("  + sqlCriteria(crit, "4") + ")) TEMP2 ";							
					} else {
						// Selected multiple terms
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where soc_name_fr IN (" + crit.getSQLString(crit.getSection4results()) + ")) TEMP2 ";
					}
				}
				sqlWhere += "AND r.REPORT_ID = TEMP2.COL2 ";					
			}
		}
		sqlWhere += "AND r.REPORT_ID = TEMP1.COL1 AND r.REPORT_ID = rd.REPORT_ID ";
		sqlFromWhere = sqlFrom + sqlWhere;
		return sqlFromWhere;
	}

	private String sqlRowFromWhere(SearchCriteria crit, Locale locale){
		// retrieves report along with ALL associated drugs
		String sqlFromWhere = "";
		String sqlFrom = "";
		String sqlWhere = "";
		sqlFrom = "from ADR_MV r, REPORT_DRUG rd, REPORT_LINKS rl " + 
					"where r.REPORT_ID = rd.REPORT_ID " +
					"and r.REPORT_ID = rl.REPORT_ID(+) " +
					"and r.REPORT_ID in (" +
					"select DISTINCT r.REPORT_ID " +
					"from ADR_MV r, REPORT_DRUGS_MV rd ";
		
		// Section 1 of Search
		if (crit.isInitialDateProvided()){
			// set initial received date range
			sqlWhere = "WHERE ";
			sqlWhere += "r.datintreceived BETWEEN TO_DATE('" + crit.getInitialDateFrom() + "', 'YYYY/MM/DD') AND TO_DATE('" + crit.getInitialDateTo() + "', 'YYYY/MM/DD') ";
		}
		if (crit.isFollowUpDateProvided()){
			// set latest received date range
			sqlWhere = "WHERE ";			
			sqlWhere += "r.datreceived BETWEEN TO_DATE('" + crit.getFollowUpDateFrom() + "', 'YYYY/MM/DD') AND TO_DATE('" + crit.getFollowUpDateTo() + "', 'YYYY/MM/DD') ";			
		}
		if (!crit.getSeriousnessCode().equals("0")) {
			sqlWhere += "AND r.seriousness_code = '" + crit.getCode(crit.getSeriousnessCode()) + "' ";
		}
//		if (!crit.getReportFeatureCode().equals("0")) {
//			sqlWhere += "AND r.report_feature_code = '" + crit.getCode(crit.getReportFeatureCode()) + "' ";			
//		}
//		if (!crit.getReportTypeCode().equals("0")) {
//			sqlWhere += "AND r.report_type_code = '" + crit.getCode(crit.getReportTypeCode()) + "' ";			
//		}
		if (!crit.getReportSourceCode().equals("0")) {
			sqlWhere += "AND r.source_code = '" + crit.getCode(crit.getReportSourceCode()) + "' ";			
		}
		// Section 2 of Search
		if (!crit.getGenderCode().equals("0")) {
			sqlWhere += "AND r.gender_code = '" + crit.getCode(crit.getGenderCode()) + "' ";			
		}
		if (!crit.getOutcomeCode().equals("0")) {
			sqlWhere += "AND r.outcome_code = '" + crit.getCode(crit.getOutcomeCode()) + "' ";			
		}
		if (!crit.getAgeFrom().equals("0")){
			// check if Age To is set to all
			if (crit.getAgeTo().equals("0")){
				// then inlude blank ages as well
				sqlWhere += "AND (r.age_y >= " + crit.getAgeFrom() + " ";				
				sqlWhere += "OR r.age_y is null) ";
			} else {
				sqlWhere += "AND r.age_y >= " + crit.getAgeFrom() + " ";				
			}
		}
		if (!crit.getAgeTo().equals("0")){
			if (crit.getAgeFrom().equals("0")){
				// this will ensure that blank ages are not included
				sqlWhere += "AND r.age_y >= 0 ";				
			}
			sqlWhere += "AND r.age_y <= " + crit.getAgeTo() + " ";
		}
		// Section 3 of Search - USING VIEWS
		if (!crit.getNameLevel().equals("0")) {
			if ((crit.getNameLevel().equals(ApplicationGlobals.DRUG_PRODUCT)) && (!crit.getKeywordsSection3().equals("")) ){
				ApplicationGlobals.DRUG_SEARCH = true; 
				if (crit.getSection3results()[0].equals("0")){
					// Select All (terms)
					sqlFrom += ", (SELECT DISTINCT report_id COL1 from REPORT_DRUGS_MV where UPPER(DRUGNAME) IN (SELECT DISTINCT dp.DRUGNAME FROM DRUG_PRODUCTS dp where dp.DRUGNAME LIKE UPPER(" + sqlCriteria(crit, "3") + "))) TEMP1 ";					
				} else {
					// Selected multiple terms
					sqlFrom += ", (SELECT DISTINCT report_id COL1 from REPORT_DRUGS_MV where UPPER(DRUGNAME) IN (SELECT DISTINCT dp.DRUGNAME FROM DRUG_PRODUCTS dp where dp.DRUGNAME IN (" + crit.getSQLString(crit.getSection3results()) + "))) TEMP1 ";					
				}
			}
			if ((crit.getNameLevel().equals(ApplicationGlobals.INGREDIENT)) && (!crit.getKeywordsSection3().equals(""))){
				ApplicationGlobals.INGREDIENT_SEARCH = true;
				if (crit.getSection3results()[0].equals("0")){
					// Select All (terms)					
					sqlFrom += ", (SELECT DISTINCT reportDrug.REPORT_ID COL1 FROM REPORT_DRUGS_MV reportDrug where UPPER(reportDrug.DRUGNAME) IN (SELECT DISTINCT dpi.DRUGNAME FROM DRUG_PRODUCT_INGREDIENTS dpi WHERE UPPER(dpi.ACTIVE_INGREDIENT_NAME) LIKE UPPER(" + sqlCriteria(crit, "3") + "))) TEMP1 ";					
				} else {
					// Selected multiple terms
					sqlFrom += ", (SELECT DISTINCT reportDrug.REPORT_ID COL1 FROM REPORT_DRUGS_MV reportDrug where UPPER(reportDrug.DRUGNAME) IN (SELECT DISTINCT dpi.DRUGNAME FROM DRUG_PRODUCT_INGREDIENTS dpi WHERE dpi.ACTIVE_INGREDIENT_NAME IN (" + crit.getSQLString(crit.getSection3results()) + "))) TEMP1 ";					
				}
			}
			sqlWhere += "AND r.REPORT_ID = TEMP1.COL1 ";			
		}
		// Section 4 of Search - USING VIEWS
		if (!crit.getMeddraTermLevel().equals("0")){
			if ((crit.getMeddraTermLevel().equals(ApplicationGlobals.PT)) && (!crit.getKeywordsSection4().equals("")) ){
				// PT Search
				if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {
					if (crit.getSection4results()[0].equals("0")){
						// Select All (terms)
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where UPPER(pt_name_eng) LIKE UPPER("  + sqlCriteria(crit, "4") + ")) TEMP2 ";						
					} else {
						// Selected multiple terms
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where pt_name_eng IN(" + crit.getSQLString(crit.getSection4results()) + ")) TEMP2 ";					
					}
				} else {
					// french
					if (crit.getSection4results()[0].equals("0")){
						// Select All (terms)
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where UPPER(pt_name_fr) LIKE UPPER("  + sqlCriteria(crit, "4") + ")) TEMP2 ";						
					} else {
						// Selected multiple terms
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where pt_name_fr IN(" + crit.getSQLString(crit.getSection4results()) + ")) TEMP2 ";					
					}
				}
				sqlWhere += "AND r.REPORT_ID = TEMP2.COL2 ";				
			} else {
				// SOC Search
				if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {
					if (crit.getSection4results()[0].equals("0")){
						// Select All (terms)
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where UPPER(soc_name_eng) LIKE UPPER("  + sqlCriteria(crit, "4") + ")) TEMP2 ";							
					} else {
						// Selected multiple terms
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where soc_name_eng IN (" + crit.getSQLString(crit.getSection4results()) + ")) TEMP2 ";
					}
				} else {
					// french
					if (crit.getSection4results()[0].equals("0")){
						// Select All (terms)
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where UPPER(soc_name_fr) LIKE UPPER("  + sqlCriteria(crit, "4") + ")) TEMP2 ";							
					} else {
						// Selected multiple terms
						sqlFrom += ", (SELECT DISTINCT report_id COL2 from RPTNAME_MV where soc_name_fr IN (" + crit.getSQLString(crit.getSection4results()) + ")) TEMP2 ";
					}
				}
				sqlWhere += "AND r.REPORT_ID = TEMP2.COL2 ";					
			}
		}
		sqlWhere += "AND r.REPORT_ID = rd.REPORT_ID ";
		sqlFromWhere = sqlFrom + sqlWhere + ")";
		return sqlFromWhere;
	}
	
	private String sqlCriteria(SearchCriteria crit, String section){
		String sqlCriteria = "";
		if (section.equals("3")){
			// SECTION 3 - Select All
			if (crit.getWildcardSection3().equals(ApplicationGlobals.BEGINS_WITH)) {
				// begins with
				sqlCriteria = "'" + sqlSingleQuoteFix(crit.getKeywordsSection3()) + "%'";
			} else {
				// contains
				sqlCriteria = "'%" + sqlSingleQuoteFix(crit.getKeywordsSection3()) + "%'";					
			}
		} else {
			// SECTION 4 - Select All
			if (crit.getWildcardSection4().equals(ApplicationGlobals.BEGINS_WITH)) {
				// begins with
				sqlCriteria = "'" + sqlSingleQuoteFix(crit.getKeywordsSection4()) + "%'";
			} else {
				// contains
				sqlCriteria = "'%" + sqlSingleQuoteFix(crit.getKeywordsSection4()) + "%'";					
			}			
		}
		return sqlCriteria;
	}
	
	private String orderByClause(int sortField){
		String sqlOrder = "";
		switch (sortField) {
		case 1:
			//sqlOrder = " ORDER BY r.report_id, r.datreceived ";
			sqlOrder = " ORDER BY rp.report_id, rp.datreceived ";
			break;
		case 3:
			//sqlOrder = " ORDER BY r.version_no, r.report_id ";
			sqlOrder = " ORDER BY rp.version_no, rp.report_id ";
			break;			
		case 4:
			//sqlOrder = " ORDER BY r.datreceived, r.report_id ";
			sqlOrder = " ORDER BY rp.datreceived, rp.report_id ";
			break;			
		case 5:
			//sqlOrder = " ORDER BY r.datintreceived, r.report_id ";
			sqlOrder = " ORDER BY rp.datintreceived, rp.report_id ";
			break;			
		case 6:
			//sqlOrder = " ORDER BY r.mah_no, r.report_id ";
			sqlOrder = " ORDER BY rp.mah_no, rp.report_id ";
			break;			
		case 7:
			//sqlOrder = " ORDER BY r.report_feature_eng, r.report_id ";
			sqlOrder = " ORDER BY rp.report_feature_eng, rp.report_id ";
			break;			
		case 8:
			//sqlOrder = " ORDER BY r.report_feature_fr, r.report_id ";
			sqlOrder = " ORDER BY rp.report_feature_fr, rp.report_id ";
			break;			
		case 9:
			//sqlOrder = " ORDER BY r.report_type_eng, r.report_id ";
			sqlOrder = " ORDER BY rp.report_type_eng, rp.report_id ";
			break;			
		case 10:
			//sqlOrder = " ORDER BY r.report_type_fr, r.report_id ";
			sqlOrder = " ORDER BY rp.report_type_fr, rp.report_id ";
			break;			
		case 11:
			//sqlOrder = " ORDER BY r.gender_eng, r.report_id ";
			sqlOrder = " ORDER BY rp.gender_eng, rp.report_id ";
			break;			
		case 12:
			//sqlOrder = " ORDER BY r.gender_fr, r.report_id ";
			sqlOrder = " ORDER BY rp.gender_fr, rp.report_id ";
			break;			
		case 13:
			//sqlOrder = " ORDER BY r.age_y, r.report_id ";
			sqlOrder = " ORDER BY rp.age_y, rp.report_id ";
			break;			
		case 17:
			//sqlOrder = " ORDER BY r.outcome_eng, r.report_id ";
			sqlOrder = " ORDER BY rp.outcome_eng, rp.report_id ";
			break;			
		case 18:
			//sqlOrder = " ORDER BY r.outcome_fr, r.report_id ";
			sqlOrder = " ORDER BY rp.outcome_fr, rp.report_id ";
			break;			
		case 19:
			//sqlOrder = " ORDER BY r.weight, r.report_id ";
			sqlOrder = " ORDER BY rp.weight, rp.report_id ";
			break;			
		case 22:
			//sqlOrder = " ORDER BY r.height, r.report_id ";
			sqlOrder = " ORDER BY rp.height, rp.report_id ";
			break;			
		case 25:
			//sqlOrder = " ORDER BY r.seriousness_eng, r.report_id ";
			sqlOrder = " ORDER BY rp.seriousness_eng, rp.report_id ";
			break;			
		case 26:
			//sqlOrder = " ORDER BY r.seriousness_fr, r.report_id ";
			sqlOrder = " ORDER BY rp.seriousness_fr, rp.report_id ";
			break;			
		case 27:
			//sqlOrder = " ORDER BY r.death, r.report_id ";
			sqlOrder = " ORDER BY rp.death, rp.report_id ";
			break;			
		case 28:
			//sqlOrder = " ORDER BY r.disability, r.report_id ";			
			sqlOrder = " ORDER BY rp.disability, rp.report_id ";
			break;			
		case 29:
			//sqlOrder = " ORDER BY r.congenital_anomaly, r.report_id ";
			sqlOrder = " ORDER BY rp.congenital_anomaly, rp.report_id ";
			break;			
		case 30:
			//sqlOrder = " ORDER BY r.life_threatening, r.report_id ";
			sqlOrder = " ORDER BY rp.life_threatening, rp.report_id ";
			break;			
		case 31:
			//sqlOrder = " ORDER BY r.hosp_required, r.report_id ";
			sqlOrder = " ORDER BY rp.hosp_required, rp.report_id ";
			break;			
		case 32:
			//sqlOrder = " ORDER BY r.other_medically_imp_cond, r.report_id ";
			sqlOrder = " ORDER BY rp.other_medically_imp_cond, rp.report_id ";
			break;			
		case 33:
			//sqlOrder = " ORDER BY r.reporter_type_eng, r.report_id ";
			sqlOrder = " ORDER BY rp.reporter_type_eng, rp.report_id ";
			break;			
		case 34:
			//sqlOrder = " ORDER BY r.reporter_type_fr, r.report_id ";
			sqlOrder = " ORDER BY rp.reporter_type_fr, rp.report_id ";
			break;			
		case 35:
			//sqlOrder = " ORDER BY r.source_eng, r.report_id ";
			sqlOrder = " ORDER BY rp.source_eng, rp.report_id ";
			break;			
		case 36:
			//sqlOrder = " ORDER BY r.source_fr, r.report_id ";
			sqlOrder = " ORDER BY rp.source_fr, rp.report_id ";
			break;			
		case 38:	
			//sqlOrder = " ORDER BY r.duration, r.report_id ";
			sqlOrder = " ORDER BY rp.duration, rp.report_id ";
			break;			
		case 48:	
			sqlOrder = " ORDER BY rd.druginvolv_eng, r.report_id ";
			break;			
		case 49:
			sqlOrder = " ORDER BY rd.druginvolv_fr, r.report_id ";			
			break;			
		case 50:
			sqlOrder = " ORDER BY rd.routeadmin_eng, r.report_id ";			
			break;			
		case 51:
			sqlOrder = " ORDER BY rd.routeadmin_fr, r.report_id ";			
			break;			
		case 52:
			sqlOrder = " ORDER BY rd.unit_dose_qty, r.report_id ";			
			break;			
		case 55:
			sqlOrder = " ORDER BY rd.frequency, r.report_id ";			
			break;			
		case 58:
			sqlOrder = " ORDER BY rd.therapy_duration, r.report_id ";			
			break;			
		case 61:
			sqlOrder = " ORDER BY rd.dosageform_eng, r.report_id ";			
			break;			
		case 62:	
			sqlOrder = " ORDER BY rd.dosageform_fr, r.report_id ";			
			break;			
		default:
			//sqlOrder = " ORDER BY r.report_id, r.datreceived, r.report_id ";
			sqlOrder = " ORDER BY rp.report_id, rp.datreceived, rp.report_id ";
			break;			
		}
		return sqlOrder;
	}

	public List findSuspectedDrugs(String searchTerm, String keyword ){
		List<LabelValueBean> drugsForDisplay = new ArrayList<LabelValueBean>();
		String sql = "";
		String sqlSelect = "";
		String sqlWhere = "";
		String sqlOrder = "";
			//sqlSelect = "SELECT {rd.*} FROM REPORT_DRUGS_MV rd ";
			sqlSelect = "SELECT DISTINCT rd.DRUGNAME FROM REPORT_DRUGS_MV rd ";			
			if (searchTerm.equals(ApplicationGlobals.BEGINS_WITH)){
				sqlWhere = "WHERE UPPER(rd.DRUGNAME) LIKE UPPER('" + sqlSingleQuoteFix(keyword) + "%') ";		
			} else {
				sqlWhere = "WHERE UPPER(rd.DRUGNAME) LIKE UPPER('%" + sqlSingleQuoteFix(keyword) + "%') ";			
			}
			sqlOrder = "ORDER BY rd.DRUGNAME ";
			sql = sqlSelect + sqlWhere + sqlOrder;
			Iterator it = getSession().createSQLQuery(sql)
							.list().iterator();
			if (it != null) {
				while (it.hasNext()){
					String drugName = it.next().toString();
					drugsForDisplay.add(new LabelValueBean(drugName, drugName));
				}
			}
		return drugsForDisplay;
	}
	
	public List findActiveIngredients(String searchTerm, String keyword){
		List activeIngredients = null;
		String sql = "";
		String sqlSelect = "";
		String sqlWhere = "";
		String sqlOrder = "";
			sqlSelect = "SELECT {ai.*} FROM ACTIVE_INGREDIENTS ai ";
			if (searchTerm.equals(ApplicationGlobals.BEGINS_WITH)){
				sqlWhere = "WHERE UPPER(ai.ACTIVE_INGREDIENT_NAME) LIKE UPPER('" + sqlSingleQuoteFix(keyword) + "%') ";		
			} else {
				sqlWhere = "WHERE UPPER(ai.ACTIVE_INGREDIENT_NAME) LIKE UPPER('%" + sqlSingleQuoteFix(keyword) + "%') ";			
			}
			sqlOrder = "ORDER BY ai.ACTIVE_INGREDIENT_NAME ";
			sql = sqlSelect + sqlWhere + sqlOrder;
			activeIngredients = getSession().createSQLQuery(sql)
					.addEntity("ai", ActiveIngredient.class)
					.list();
		return activeIngredients;
	}
	
	public List findPTs(String searchTerm, String keyword, Locale locale){
		List pTs = null;
		String sql = "";
		String sqlSelect = "";
		String sqlWhere = "";
		String sqlOrder = "";
			sqlSelect = "SELECT {p.*} FROM MEDDRA_PT_LX p ";
			if (searchTerm.equals(ApplicationGlobals.BEGINS_WITH)){
				if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {
					sqlWhere = "WHERE UPPER(p.PT_NAME_ENG) LIKE UPPER('" + sqlSingleQuoteFix(keyword) + "%') ";	
					sqlOrder = "ORDER BY p.PT_NAME_ENG ";					
				} else { // french - begins with
					sqlWhere = "WHERE UPPER(p.PT_NAME_FR) LIKE UPPER('" + sqlSingleQuoteFix(keyword) + "%') ";	
					sqlOrder = "ORDER BY p.PT_NAME_FR ";					
				}
			} else { // english - contains
				if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {
					sqlWhere = "WHERE UPPER(p.PT_NAME_ENG) LIKE UPPER('%" + sqlSingleQuoteFix(keyword) + "%') ";
					sqlOrder = "ORDER BY p.PT_NAME_ENG ";					
				} else { // french - contains
					sqlWhere = "WHERE UPPER(p.PT_NAME_FR) LIKE UPPER('%" + sqlSingleQuoteFix(keyword) + "%') ";
					sqlOrder = "ORDER BY p.PT_NAME_FR ";					
				}
			}
			sql = sqlSelect + sqlWhere + sqlOrder;
			pTs = getSession().createSQLQuery(sql)
					.addEntity("p", MeddraPT.class)
					.list();		
		return pTs;
	}
	
	public List findSOCs(String searchTerm, String keyword, Locale locale){
		List sOCs = null;
		String sql = "";
		String sqlSelect = "";
		String sqlWhere = "";
		String sqlOrder = "";
			sqlSelect = "SELECT {s.*} FROM MEDDRA_SOC_LX s ";
			if (searchTerm.equals(ApplicationGlobals.BEGINS_WITH)){
				if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {
					sqlWhere = "WHERE UPPER(s.SOC_NAME_ENG) LIKE UPPER('" + sqlSingleQuoteFix(keyword) + "%') ";	
					sqlOrder = "ORDER BY s.SOC_NAME_ENG ";					
				} else { // french - begins with
					sqlWhere = "WHERE UPPER(s.SOC_NAME_FR) LIKE UPPER('" + sqlSingleQuoteFix(keyword) + "%') ";	
					sqlOrder = "ORDER BY s.SOC_NAME_FR ";					
				}
			} else { // english - contains
				if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {
					sqlWhere = "WHERE UPPER(s.SOC_NAME_ENG) LIKE UPPER('%" + sqlSingleQuoteFix(keyword) + "%') ";
					sqlOrder = "ORDER BY s.SOC_NAME_ENG ";					
				} else { // french - contains
					sqlWhere = "WHERE UPPER(s.SOC_NAME_FR) LIKE UPPER('%" + sqlSingleQuoteFix(keyword) + "%') ";
					sqlOrder = "ORDER BY s.SOC_NAME_FR ";					
				}
			}
			sql = sqlSelect + sqlWhere + sqlOrder;
			sOCs = getSession().createSQLQuery(sql)
					.addEntity("s", MeddraSOC.class)
					.list();		
		return sOCs;
	}
	
	public String sqlSingleQuoteFix(String term){
		String keyword = "";
		keyword = term.replace("'", "''");
		keyword = keyword.replace("%", "");
		keyword = keyword.replace("!", "");
		keyword = keyword.replace("*", "");
		return keyword;
	}
	
}
