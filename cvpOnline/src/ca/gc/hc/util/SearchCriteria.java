package ca.gc.hc.util;

import java.util.Arrays;
import java.util.Iterator;

public class SearchCriteria extends LocaleDependantObject{

	private String dateFrom;
	private String dateTo;
	private String dateSelection; // default = 0 (Initial Received Date)
								  		// = 1 (Latest Received Date)	
	private String initialDateFrom;
	private String initialDateTo;
	private String followUpDateFrom;
	private String followUpDateTo;
	private String seriousnessCode;		//selection from dropdown
	private String reportFeatureCode;		//selection from dropdown
	private String reportTypeCode;		//selection from dropdown
	private String reportSourceCode;		//selection from dropdown
	private String genderCode;			//selection from dropdown
	private String outcomeCode;		//selection from dropdown
	
	private String ageFrom;
	private String ageTo;
	
	private String nameLevel;  //selection from dropdown 0=Brand Name, 1=Active Ingredient

	private String meddraTermLevel; 	// SOC = 1, PT = 2 (default SOC)	
	private String wildcardSection3;  // Begins With = 1, Contains = 2 (default Begins With)
	private String wildcardSection4;  // Begins With = 1, Contains = 2 (default Begins With)
	
	private String keywordsSection3;
	private String keywordsSection4;

	private String[] section3results = null;
	private String[] section4results = null;	

	private boolean initialDateProvided = true;
	private boolean followUpDateProvided = true;		
	
	public String seriousName;
	public String seriousNameE;
	public String seriousNameF;
	public String reportFeatureName;
	public String reportFeatureNameE;
	public String reportFeatureNameF;
	public String reportTypeName;
	public String reportTypeNameE;
	public String reportTypeNameF;
	public String reportSourceName;
	public String reportSourceNameE;
	public String reportSourceNameF;
	public String genderName;
	public String genderNameE;
	public String genderNameF;
	public String outcomeName;
	public String outcomeNameE;
	public String outcomeNameF;
	public String ageCrit;
	public String ageCritE;
	public String ageCritF;
	public String[] section4critE;
	public String[] section4critF;
	
	
	public SearchCriteria() {
	}
		
	public String getAgeFrom() {
		return ageFrom;
	}
	public void setAgeFrom(String ageFrom) {
		this.ageFrom = ageFrom;
	}
	public String getAgeTo() {
		return ageTo;
	}
	public void setAgeTo(String ageTo) {
		this.ageTo = ageTo;
	}
	public String getFollowUpDateFrom() {
		return followUpDateFrom;
	}
	public void setFollowUpDateFrom(String followUpDateFrom) {
		this.followUpDateFrom = followUpDateFrom;
	}
	public String getFollowUpDateTo() {
		return followUpDateTo;
	}
	public void setFollowUpDateTo(String followUpDateTo) {
		this.followUpDateTo = followUpDateTo;
	}
	public String getInitialDateFrom() {
		return initialDateFrom;
	}
	public void setInitialDateFrom(String initialDateFrom) {
		this.initialDateFrom = initialDateFrom;
	}
	public String getInitialDateTo() {
		return initialDateTo;
	}
	public void setInitialDateTo(String initialDateTo) {
		this.initialDateTo = initialDateTo;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderID) {
		this.genderCode = genderID;
	}
	public String getReportFeatureCode() {
		return reportFeatureCode;
	}
	public void setReportFeatureCode(String reportFeatureID) {
		this.reportFeatureCode = reportFeatureID;
	}
	public String getOutcomeCode() {
		return outcomeCode;
	}
	public void setOutcomeCode(String reportOutcomeID) {
		this.outcomeCode = reportOutcomeID;
	}
	public String getReportSourceCode() {
		return reportSourceCode;
	}
	public void setReportSourceCode(String reportSourceID) {
		this.reportSourceCode = reportSourceID;
	}
	public String getReportTypeCode() {
		return reportTypeCode;
	}
	public void setReportTypeCode(String reportTypeID) {
		this.reportTypeCode = reportTypeID;
	}
	public String getSeriousnessCode() {
		return seriousnessCode;
	}
	public void setSeriousnessCode(String seriousnessID) {
		this.seriousnessCode = seriousnessID;
	}
	public String getNameLevel() {
		return nameLevel;
	}
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

//	public String getAllReactionTerms() {
//		return allReactionTerms;
//	}
//
//	public void setAllReactionTerms(String allReactionTerms) {
//		this.allReactionTerms = allReactionTerms;
//	}

	public String getKeywordsSection3() {
		return keywordsSection3;
	}

	public void setKeywordsSection3(String keywordsSection3) {
		this.keywordsSection3 = keywordsSection3;
	}

	public String getKeywordsSection4() {
		return keywordsSection4;
	}

	public void setKeywordsSection4(String keywordsSection4) {
		this.keywordsSection4 = keywordsSection4;
	}

	public String getWildcardSection3() {
		return wildcardSection3;
	}

	public void setWildcardSection3(String wildcardSection3) {
		this.wildcardSection3 = wildcardSection3;
	}

	public String getWildcardSection4() {
		return wildcardSection4;
	}

	public void setWildcardSection4(String wildcardSection4) {
		this.wildcardSection4 = wildcardSection4;
	}

	public String getMeddraTermLevel() {
		return meddraTermLevel;
	}

	public void setMeddraTermLevel(String meddraTermLevel) {
		this.meddraTermLevel = meddraTermLevel;
	}

	public String[] getSection3results() {
		return section3results;
	}

	public void setSection3results(String[] section3results) {
		this.section3results = section3results;
	}

	public String[] getSection4results() {
		return section4results;
	}

	public void setSection4results(String[] section4results) {
		this.section4results = section4results;
	}
	
	public String getSection3Criteria(){
		if (this.section3results.length != 0){
			if (this.section3results[0].equals("0")){
				return this.keywordsSection3;
			} else {
				return getSQLString(this.section3results);			
			}			
		} else return "All/Tous";
	}

	public String getSection4Criteria(){
		if (this.section4results.length != 0){
			
			if (this.section4results[0].equals("0")){
				return this.keywordsSection4;
			} else {
				return getSQLString(this.section4results);			
			}			
		} else return "All/Tous";
	}	
	
	/**
	 * Method takes in a String Array (usually containg elements from a multi-select dropdown box)
	 * and returns the elements in the array in a form that can be used in
	 * an SQL IN statement --> eg. 'term1', 'term2'
	 * The brackets around the terms are added in the DAO
	 */
	public String getSQLString(String[] selectedList){
		String sqlString = "";
		int listSize = selectedList.length;
		if (listSize > 0){
			int count = 0;
			Iterator it = Arrays.asList(selectedList).iterator();
			while (it.hasNext()){
				count ++;
				if (count < listSize){
					sqlString += "'" + escapeSingleQuotes(it.next().toString()) + "', ";				
				} else {
					sqlString += "'" + escapeSingleQuotes(it.next().toString()) + "'";				
				}
			}			
		}
		return sqlString;
	}
	
	public String escapeSingleQuotes(String term){
		String escapedTerm = "";
		escapedTerm = term.replace("'", "''");
		return escapedTerm;
	}
	
	public String getCode(String joinedString){
		String code = "";
		String[] splitString;
		splitString = joinedString.split("\\,");
		code = splitString[0].toString();
		return code;
	}
	
	public String getSection4Name(String joinedString){
		String name = "";
		String[] splitString;
		splitString = joinedString.split("\\,");
		name = splitString[0].toString();
		return name;
	}	

	public String getSeriousName() {
		if (isLanguageFrench()) {
			return getSeriousNameF();
		}
		return getSeriousNameE();		
	}

	public void setSeriousName(String seriousName) {
		this.seriousName = seriousName;
	}

	public String getGenderName() {
		if (isLanguageFrench()) {
			return getGenderNameF();
		}
		return getGenderNameE();			
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getOutcomeName() {
		if (isLanguageFrench()) {
			return getOutcomeNameF();
		}
		return getOutcomeNameE();		
	}

	public void setOutcomeName(String outcomeName) {
		this.outcomeName = outcomeName;
	}

	public String getReportFeatureName() {
		if (isLanguageFrench()) {
			return getReportFeatureNameF();
		}
		return getReportFeatureNameE();	
	}

	public void setReportFeatureName(String reportFeatureName) {
		this.reportFeatureName = reportFeatureName;
	}

	public String getReportSourceName() {
		if (isLanguageFrench()) {
			return getReportSourceNameF();
		}
		return getReportSourceNameE();			
	}

	public void setReportSourceName(String reportSourceName) {
		this.reportSourceName = reportSourceName;
	}

	public String getReportTypeName() {
		if (isLanguageFrench()) {
			return getReportTypeNameF();
		}
		return getReportTypeNameE();			
	}

	public void setReportTypeName(String reportTypeName) {
		this.reportTypeName = reportTypeName;
	}

	public String getAgeCrit() {
		if (isLanguageFrench()) {
			return getAgeCritF();
		}
		return getAgeCritE();			
	}

	public void setAgeCrit(String ageCrit) {
		this.ageCrit = ageCrit;
	}

	public boolean isFollowUpDateProvided() {
		return followUpDateProvided;
	}

	public void setFollowUpDateProvided(boolean followUpDateProvided) {
		this.followUpDateProvided = followUpDateProvided;
	}

	public boolean isInitialDateProvided() {
		return initialDateProvided;
	}

	public void setInitialDateProvided(boolean initialDateProvided) {
		this.initialDateProvided = initialDateProvided;
	}

	public String getSeriousNameE() {
		return seriousNameE;
	}

	public void setSeriousNameE(String seriousNameE) {
		this.seriousNameE = seriousNameE;
	}

	public String getSeriousNameF() {
		return seriousNameF;
	}

	public void setSeriousNameF(String seriousNameF) {
		this.seriousNameF = seriousNameF;
	}

	public String getGenderNameE() {
		return genderNameE;
	}

	public void setGenderNameE(String genderNameE) {
		this.genderNameE = genderNameE;
	}

	public String getGenderNameF() {
		return genderNameF;
	}

	public void setGenderNameF(String genderNameF) {
		this.genderNameF = genderNameF;
	}

	public String getOutcomeNameE() {
		return outcomeNameE;
	}

	public void setOutcomeNameE(String outcomeNameE) {
		this.outcomeNameE = outcomeNameE;
	}

	public String getOutcomeNameF() {
		return outcomeNameF;
	}

	public void setOutcomeNameF(String outcomeNameF) {
		this.outcomeNameF = outcomeNameF;
	}

	public String getReportFeatureNameE() {
		return reportFeatureNameE;
	}

	public void setReportFeatureNameE(String reportFeatureNameE) {
		this.reportFeatureNameE = reportFeatureNameE;
	}

	public String getReportFeatureNameF() {
		return reportFeatureNameF;
	}

	public void setReportFeatureNameF(String reportFeatuteNameF) {
		this.reportFeatureNameF = reportFeatuteNameF;
	}

	public String getReportSourceNameE() {
		return reportSourceNameE;
	}

	public void setReportSourceNameE(String reportSourceNameE) {
		this.reportSourceNameE = reportSourceNameE;
	}

	public String getReportSourceNameF() {
		return reportSourceNameF;
	}

	public void setReportSourceNameF(String reportSourceNameF) {
		this.reportSourceNameF = reportSourceNameF;
	}

	public String getReportTypeNameE() {
		return reportTypeNameE;
	}

	public void setReportTypeNameE(String reportTypeNameE) {
		this.reportTypeNameE = reportTypeNameE;
	}

	public String getReportTypeNameF() {
		return reportTypeNameF;
	}

	public void setReportTypeNameF(String reportTypeNameF) {
		this.reportTypeNameF = reportTypeNameF;
	}

	public String getAgeCritE() {
		return ageCritE;
	}

	public void setAgeCritE(String ageCritE) {
		this.ageCritE = ageCritE;
	}

	public String getAgeCritF() {
		return ageCritF;
	}

	public void setAgeCritF(String ageCritF) {
		this.ageCritF = ageCritF;
	}

	public String[] getSection4critE() {
		return section4critE;
	}

	public void setSection4critE(String[] section4critE) {
		this.section4critE = section4critE;
	}

	public String[] getSection4critF() {
		return section4critF;
	}

	public void setSection4critF(String[] section4critF) {
		this.section4critF = section4critF;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateSelection() {
		return dateSelection;
	}

	public void setDateSelection(String dateSelection) {
		this.dateSelection = dateSelection;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}	
	
	
}
