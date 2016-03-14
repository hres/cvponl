package ca.gc.hc.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ca.gc.hc.bean.SearchResultsBean;
import ca.gc.hc.model.ReportDrug;

public class SearchUtility {
	private Long currentReportID = null;
	private Set ptNamesE = new HashSet();
	private Set ptNamesF = new HashSet();
	private Set drugNames = new HashSet();
	private Set socNamesE = new HashSet();
	private Set socNamesF = new HashSet();	

	public SearchUtility() {
	}
	
	/* this method is iterating through the SearchResultsRowObject to populate the SearchResultsBean */	
	public List populateResultsBean(List searchResults, SearchCriteria criteria){
		ArrayList searchReportResults = new ArrayList();
		Iterator it = searchResults.iterator();		
		int index = 0;
		int reportCount = 0;
		Long nextReportID= null;
		String sPtNamesE = "";
		String sPtNamesF = "";
		String sSocNamesE = "";
		String sSocNamesF = "";
		SearchResultsBean searchResultBean = new SearchResultsBean();
		while (it.hasNext()){
			Object[] searchResultRow = (Object[]) it.next();
			if ((currentReportID == null) && (nextReportID == null)){
				currentReportID = convertBigDecimalToLong((BigDecimal) searchResultRow[1]);
				// first record, add normal
				searchResultBean = fillBean(searchResultBean, searchResultRow);
				reportCount ++;
			} else {
				nextReportID = convertBigDecimalToLong((BigDecimal) searchResultRow[1]);
				if (currentReportID.equals(nextReportID)){
					// add terms to the collections for the same report
					this.ptNamesE.add(searchResultRow[41]);
					this.ptNamesF.add(searchResultRow[42]);					
					this.drugNames.add((String)searchResultRow[47]);
					this.socNamesE.add((String)searchResultRow[43]);
					this.socNamesF.add((String)searchResultRow[44]);					

				} else{

					if (searchResultBean.getMeddraVersion() != null){
						ApplicationGlobals.MEDDRA_VERSION_VALUE = searchResultBean.getMeddraVersion();						
					}
					// Add bean to collection
					// first set all drugs and PTs here and populate them in the bean					
					sPtNamesE = ptNamesE.toString().substring(1, (ptNamesE.toString().length()-1));
					sPtNamesF = ptNamesF.toString().substring(1, (ptNamesF.toString().length()-1));
					searchResultBean.setPtNameE(sPtNamesE);
					searchResultBean.setPtNameF(sPtNamesF);
					searchResultBean.setDrugName(this.drugNames.toString().substring(1, (drugNames.toString().length()-1)));
					sSocNamesE = socNamesE.toString().substring(1, (socNamesE.toString().length()-1));
					sSocNamesF = socNamesF.toString().substring(1, (socNamesF.toString().length()-1));
					searchResultBean.setSocNameE(sSocNamesE);
					searchResultBean.setSocNameF(sSocNamesF);
					
					searchReportResults.add(index, searchResultBean);
					index ++;
					searchResultBean = new SearchResultsBean();				
					//searchResultBean.resetProperties();
					
					// move to the next search result record
					currentReportID = nextReportID;
					this.drugNames.clear();
					this.ptNamesE.clear();
					this.ptNamesF.clear();
					this.socNamesE.clear();
					this.socNamesF.clear();
					searchResultBean = fillBean(searchResultBean, searchResultRow);	
					reportCount ++;
				}
			}
		}
		// add the last record OR first record if there is only one report
		if (reportCount == 1){
			if (searchResultBean.getMeddraVersion() != null){
				ApplicationGlobals.MEDDRA_VERSION_VALUE = searchResultBean.getMeddraVersion();						
			}
		}
		// add last record with its drugs and reaction terms
		sPtNamesE = ptNamesE.toString().substring(1, (ptNamesE.toString().length()-1));
		sPtNamesF = ptNamesF.toString().substring(1, (ptNamesF.toString().length()-1));
		sSocNamesE = socNamesE.toString().substring(1, (socNamesE.toString().length()-1));
		sSocNamesF = socNamesF.toString().substring(1, (socNamesF.toString().length()-1));
		searchResultBean.setPtNameE(sPtNamesE);
		searchResultBean.setPtNameF(sPtNamesF);		
		searchResultBean.setSocNameE(sSocNamesE);
		searchResultBean.setSocNameF(sSocNamesF);		
		searchResultBean.setDrugName(this.drugNames.toString().substring(1, (drugNames.toString().length()-1)));		
		searchReportResults.add(index, searchResultBean);
		return searchReportResults;
	}	

	private SearchResultsBean fillBean(SearchResultsBean bean, Object[] report){
		bean.setReportNo((String) report[2]);
		bean.setVersionNo(checkNull(convertBigDecimalToLong((BigDecimal) report[3])));
		bean.setMahNumber((String) report[6]);
		bean.setInitialDateReceived(formatDateDisplay((Date) report[5]));		
		bean.setLatestDateReceived(formatDateDisplay((Date) report[4]));
		bean.setReportFeatureE((String) report[7]);
		bean.setReportFeatureF((String) report[8]);
		bean.setReportTypeE((String) report[9]);
		bean.setReportTypeF((String) report[10]);
		bean.setSeriousnessE((String) report[25]);
		bean.setSeriousnessF((String) report[26]);	
		bean.setAge(checkNull(convertBigDecimalToLong((BigDecimal) report[13])));
		bean.setAgeUnitE((String) report[14]);
		bean.setAgeUnitF((String) report[15]);		
		bean.setGenderNameE((String) report[11]);
		bean.setGenderNameF((String) report[12]);		
		bean.setWeight(checkNull(convertBigDecimalToLong((BigDecimal) report[19])));
		bean.setWeightUnitE((String) report[20]);
		bean.setWeightUnitF((String) report[21]);
		bean.setHeight(checkNull(convertBigDecimalToLong((BigDecimal) report[22])));
		bean.setHeightUnitE((String) report[23]);
		bean.setHeightUnitF((String) report[24]);
		bean.setOutcomeNameE((String) report[17]);
		bean.setOutcomeNameF((String) report[18]);
		bean.setReporterTypeE((String) report[33]);
		bean.setReporterTypeF((String) report[34]);		
		bean.setSourceE((String) report[35]);
		bean.setSourceF((String) report[36]);
		bean.setDeath((String) report[27]);
		bean.setDisability((String) report[28]);
		bean.setCongenitalAnomaly((String) report[29]);
		bean.setLifeThreatening((String) report[30]);
		bean.setHospitalization((String) report[31]);
		bean.setOtherMedCondition((String) report[32]);

		this.drugNames.add((String)report[47]);		
		bean.setDrugName(this.drugNames.toString());
		
		bean.setDosageFormE((String) report[61]);
		bean.setDosageFormF((String) report[62]);
		bean.setDrugInvolveE((String) report[48]);
		bean.setDrugInvolveF((String) report[49]);
		bean.setRouteAdminE((String) report[50]);
		bean.setRouteAdminF((String) report[51]);
		bean.setAmount(checkNull(convertBigDecimalToLong((BigDecimal) report[52])));
		bean.setDoseUnitE((String) report[53]);
		bean.setDoseUnitF((String) report[54]);
		bean.setFrequency(checkNull(convertBigDecimalToLong((BigDecimal) report[55])));
		bean.setFreqTimeUnitE((String) report[56]);
		bean.setFreqTimeUnitF((String) report[57]);
		bean.setTherapyDuration(checkNull(convertBigDecimalToLong((BigDecimal) report[58])));
		bean.setTherapyDurationUnitE((String) report[59]);
		bean.setTherapyDurationUnitF((String) report[60]);
		
		this.ptNamesE.add(report[41]);
		this.ptNamesF.add(report[42]);

		this.socNamesE.add(report[43]);
		this.socNamesF.add(report[44]);
		
		bean.setReactionDuration(checkNull(convertBigDecimalToLong((BigDecimal) report[38])));		
		bean.setReactionDurationUnitE((String) report[39]);
		bean.setReactionDurationUnitF((String) report[40]);
		bean.setMeddraVersion((String) report[45]);

		return bean;
	}		
	
	
	public String checkNullObject(Object obj){
		String value = "";
		if (obj != null){
			value = obj.toString();
		}
		return value;
	}
	
	public String formatDateDisplay (Date date){
	   	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");		
		String formattedDate = null;
		formattedDate = dateFormat.format(date);
		return formattedDate;		
	}
	
	public String checkNull (Long property){
		String value = "";
		if (property != null){
			value = property.toString();
		}
		return value;
	}
	
	public Long convertBigDecimalToLong(BigDecimal number){
		Long convertedNumber = null;
		if (number != null) {
			convertedNumber = Long.valueOf(number.longValue());
		} 
		return convertedNumber;
	}
	

	
}
