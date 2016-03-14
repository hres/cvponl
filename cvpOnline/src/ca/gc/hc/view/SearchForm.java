package ca.gc.hc.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 */
public class SearchForm extends ActionForm {
	
	private ArrayList searchResults;

	private String dateFrom = "1965-01-01";
	private String dateTo;
	private String dateSelection = "0"; // default = 0 (Initial Received Date)
								  		// = 1 (Latest Received Date)
	
	private String initialDateFrom;
	private String initialDateTo;
	private String followUpDateFrom;
	private String followUpDateTo;	
	private String seriousnessCode;
	private String reportFeatureCode;
	private String reportTypeCode;
	private String reportSourceCode;
	private String genderCode;
	private String outcomeCode;
	
	private String ageFrom;		// default = 0
	private String ageTo;		// default = 0 = All
	
	private String nameLevel = "1";  // default = 1, Brand name
									//Suspected Name Level dropdown - loaded directly in JSP through resource properties
									//This can be done through ApplicationGlobals too (if you want to sort it a different way)
	
	private String meddraTermLevel = "0";  // MedDRA Term Level dropdwon (this may be divided into 2 dropdowns PT and SOC)
	private String wildcardSection3;  // wildcard selection for section 3
	private String wildcardSection4;  // wildcard selection for section 4
	private String keywordsSection3;  // Search term for Product or Ingredient
	private String keywordsSection4;  // Search term for Reaction
	
	private String[] section3results;
	private String[] section4results;
	
	private boolean initialDateProvided = false;
	private boolean followUpDateProvided = false;	
	private boolean dateProvided = true;

	private boolean section3FindSearch = false;	
	private boolean section4FindSearch = false;
	
//	private boolean goButtonPressed = false;

	
	public void reset (ActionMapping mapping, HttpServletRequest request){
		HttpSession session = request.getSession();		
		this.initialDateFrom = "";
		this.initialDateTo = "";
		this.followUpDateFrom = "";
		this.followUpDateTo = "";
		this.seriousnessCode = "";
		this.reportFeatureCode = "0";
		this.reportTypeCode = "0";
		this.reportSourceCode = "";
		this.genderCode = "";
		this.outcomeCode = "";
		this.ageFrom = "";
		this.ageTo = "";
		this.nameLevel = "1";
		this.meddraTermLevel = "0";
		this.wildcardSection3 = "";
		this.wildcardSection4 = "";
		this.keywordsSection3 = "";
		this.keywordsSection4 = "";
		this.section3results = null;
		this.section4results = null;
		this.initialDateProvided = false;
		this.followUpDateProvided = false;
		this.section3FindSearch = false;
		this.section4FindSearch = false;
		this.dateFrom = "1965-01-01";
		this.dateTo = (String) session.getAttribute("quarter");
		this.dateSelection = "0";
//		this.goButtonPressed = false;
	}	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors messages = new ActionErrors();
		HttpSession session = request.getSession();

		if (session.getAttribute("sessionActive") != null){

			session.setAttribute("searchForm", this);
			if ((!this.section4FindSearch) && (!this.section3FindSearch)){
				// this means that the user clicked on the 'Search' button and not the 'Find'
				if (!this.dateSelection.equals("")){
					if (!this.dateFrom.equals("")){
						if (this.dateTo.equals("")){
							// date To is required
							this.dateProvided = false;
						} else {
							// both date From and date To have values
							if (org.apache.commons.validator.DateValidator.getInstance().isValid(this.dateTo, "yyyy-MM-dd", true)){
								// if the date is valid, then check to see if it is greater than the quarter end date
								if (checkDateTo(this.dateTo, (String) session.getAttribute("quarter"))) {
									messages.add("dateMaxRangeError", new ActionMessage("error.link.toDate.maxRange"));							
								}
							}							
						}
					} else if (this.dateTo.equals("")){
						// both From and To date fields are blank
						this.dateProvided = false;
					} else {
						// date From is required
						this.dateProvided = false;						
					}
					if (this.dateSelection.equals("0")){
						this.initialDateProvided = true;
					} else {
						this.followUpDateProvided = true;						
					}
				}
				
				if (!this.dateProvided){
					messages.add("dateMissingError", new ActionMessage("error.link.date.empty"));
				} else if (!org.apache.commons.validator.DateValidator.getInstance().isValid(this.dateFrom, "yyyy-MM-dd", true) ||
						   !org.apache.commons.validator.DateValidator.getInstance().isValid(this.dateTo, "yyyy-MM-dd", true)) {
					messages.add("dateFormatError", new ActionMessage("error.date.format"));
				} else {
					if (!checkDateLarger(this.dateFrom, this.dateTo)){
						messages.add("dateRangeError", new ActionMessage("error.date.range"));							
					}
				} 
				
				if (this.dateProvided){
					if (this.dateSelection.equals("0")){
						this.initialDateFrom = this.dateFrom;
						this.initialDateTo = this.dateTo;
					} else {
						this.followUpDateFrom = this.dateFrom;
						this.followUpDateTo = this.dateTo;
					}
				}
				
//				if (!this.initialDateFrom.equals("")){
//					if (this.initialDateTo.equals("")){
//						// Date To is required
//						this.initialDateProvided = false;					
//					} else {
//						if (org.apache.commons.validator.DateValidator.getInstance().isValid(this.initialDateTo, "yyyy-MM-dd", true)){
//							// if the date is valid, then check to see if it is greater than the quarter end date
//							if (checkDateTo(this.initialDateTo, (String) session.getAttribute("quarter"))) {
//								messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.toDate.maxRange"));							
//							}
//						}
//					}
//				} else if (this.initialDateTo.equals("")){
//					// initial date is completely blank
//					this.initialDateProvided = false;
//				} else {
//					// date from is required
//					this.initialDateProvided = false;				
//				}
//
//				if (!this.followUpDateFrom.equals("")) {
//					if (this.followUpDateTo.equals("")){
//						// Date to is required
//						this.followUpDateProvided = false;					
//					} else {
//						if (org.apache.commons.validator.DateValidator.getInstance().isValid(this.followUpDateTo, "yyyy-MM-dd", true)){
//							// if the date is valid, then check to see if it is greater than the quarter end date							
//							if (checkDateTo(this.followUpDateTo, (String) session.getAttribute("quarter"))) {
//								messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.toDate.maxRange"));							
//							}						
//						}
//					}
//				} else if (this.followUpDateTo.equals("")){
//					// follow up date is completely blank
//					this.followUpDateProvided = false;
//				} else {
//					// date from is required
//					this.followUpDateProvided = false;				
//				}
//			
//				if ((!this.initialDateProvided) && (!this.followUpDateProvided)) {
//					messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.date.empty"));				
//				} else if (this.initialDateProvided) {
//					if (this.followUpDateProvided) {
//						messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.date.toomany"));
//					} else if (!org.apache.commons.validator.DateValidator.getInstance().isValid(this.initialDateFrom, "yyyy-MM-dd", true) ||
//							   !org.apache.commons.validator.DateValidator.getInstance().isValid(this.initialDateTo, "yyyy-MM-dd", true)) {
//						messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.date.format"));
//					} else {
//						if (!checkDateLarger(this.initialDateFrom, this.initialDateTo)){
//							messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.date.range"));							
//						}
//					} 
//				} else {
//					if (this.followUpDateProvided) {
//						if (!org.apache.commons.validator.DateValidator.getInstance().isValid(this.followUpDateFrom, "yyyy-MM-dd", true) ||
//							!org.apache.commons.validator.DateValidator.getInstance().isValid(this.followUpDateTo, "yyyy-MM-dd", true)) {
//							messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.date.format"));
//						}
//						else {
//							if (!checkDateLarger(this.followUpDateFrom, this.followUpDateTo)){
//								messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.date.range"));							
//							}							
//						}
//					}
//				}
				
				if ((Integer.valueOf(this.ageFrom).intValue() > 0) && (Integer.valueOf(this.ageTo).intValue()) > 0){
					if (Integer.valueOf(this.ageFrom).intValue() > Integer.valueOf(this.ageTo).intValue()) {
						messages.add("ageRangeError", new ActionMessage("error.age.range.invalid"));				
					}
				}
				
				if ((this.nameLevel.equals("0")) && (this.meddraTermLevel.equals("0"))){
					messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.search.criteria"));				
				}
				
				if (((session.getAttribute("suspectedDrugs") == null) && ((session.getAttribute("activeIngredients")) == null))){
					// 'Find' has not been executed for this section, therefore reset unused form values before submitting the search
					this.setNameLevel("0");
					this.setWildcardSection3("1");
					this.setKeywordsSection3("");					
				}
				if (((session.getAttribute("systemOrganClasses") == null) && ((session.getAttribute("preferredTerms")) == null))){
					// 'Find' has not been executed for this section, therefore reset unused form values before submitting the search
					this.setMeddraTermLevel("0");
					this.setWildcardSection4("1");
					this.setKeywordsSection4("");					
				}				
			}

			if ((!this.nameLevel.equals("0"))){
				// Suspected Brand Name or Active Ingredient has been selected
				if (parseString(this.keywordsSection3).length() < 3){
					messages.add("productKeywordsError", new ActionMessage("error.products.keywords"));					
				}
				if (((session.getAttribute("suspectedDrugs") != null) || ((session.getAttribute("activeIngredients")) != null))){
					if (this.section3results == null){
						messages.add("productSelectError", new ActionMessage("error.products.select"));						
					}
				} 
			} else {
				// Select All has been selected
				if (parseString(this.keywordsSection3).length() > 0){
					messages.add("productNoKeywordError", new ActionMessage("error.products.nokeywords"));					
				}
				this.keywordsSection3 = (parseString(this.keywordsSection3));				
			}
			
			
			if (!this.meddraTermLevel.equals("0")){
				// System Organ Class or Preferred Term has been selected
				if (!this.meddraTermLevel.equals("1")){
					// Reaction Term selected - check keyword length
					if (parseString(this.keywordsSection4).length() < 3){
						messages.add("meddraKeywordError", new ActionMessage("error.meddra.keywords"));					
					}					
				}
				// Note: if SOC selected - in this case, keywords are not required
				// however still required to press the Find button

				if (((session.getAttribute("systemOrganClasses") != null) || ((session.getAttribute("preferredTerms")) != null))){
					if (this.section4results == null){
						messages.add("meddraSelectError", new ActionMessage("error.meddra.select"));						
					}					
				}
			} else {
				// Select All has been selected
				if (parseString(this.keywordsSection4).length() > 0){
					messages.add("meddraNoKeywordError", new ActionMessage("error.meddra.nokeywords"));					
				}
			}
		}

		return messages;
	}

	public boolean checkDateLarger(String dateFrom, String dateTo){
   		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		boolean isLarger = false;
		try {
			Date dDateFrom = dateFormat.parse(dateFrom);
			Date dDateTo = dateFormat.parse(dateTo);
			if (dDateTo.after(dDateFrom)) {
				isLarger = true;
			} else if (!(dDateTo.after(dDateFrom)) && (!dDateTo.before(dDateFrom))){
				// the dates are equal
				isLarger = true;
			} 
		} catch (ParseException e) {
			e.printStackTrace();
		} 		
		
		return isLarger;
	}

/**
 * Boolean function that returns whether the specified date is larger than the data quarter end date
 * @param dateTo
 * @param quarterDate
 * @return
 */
	public boolean checkDateTo(String dateTo, String quarterDate){
   		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		boolean isLarger = false;
		try {
			Date dInitialDateTo = dateFormat.parse(dateTo);
			Date dQuarterDate = dateFormat.parse(quarterDate);
			if ((dInitialDateTo.after(dQuarterDate))){
				isLarger = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} 		
		return isLarger;
	}
	
	public boolean checkDateValidity(String year, String month, String day){
			//	confirm that the date is appropriate.
			 String date = month + "/" + day + "/" + year;  
			 SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd");
			 // setting lenient to false ensures that the date is a valid date
			 df.setLenient(false);
			 try{
				 df.parse(date);
				return true;
			 } catch (Exception e){
				 // the date isn't valid so return false
				 return false;
			 }
	}
	
	public String parseString(String term){
		String keyword = "";
		keyword = term.replace("'", "''");
		keyword = keyword.replace("%", "");
		keyword = keyword.replace("!", "");
		keyword = keyword.replace("*", "");
		return keyword;
	}	

	public String getInitialDateFrom() {
		return initialDateFrom;
	}

	public void setInitialDateFrom(String dateReceived) {
		this.initialDateFrom = dateReceived;
	}

	public String getFollowUpDateFrom() {
		return followUpDateFrom;
	}

	public void setFollowUpDateFrom(String followUpDateReceivedFrom) {
		this.followUpDateFrom = followUpDateReceivedFrom;
	}

	public String getFollowUpDateTo() {
		return followUpDateTo;
	}

	public void setFollowUpDateTo(String followUpDateReceivedTo) {
		this.followUpDateTo = followUpDateReceivedTo;
	}

	public String getInitialDateTo() {
		return initialDateTo;
	}

	public void setInitialDateTo(String initialDateReceivedTo) {
		this.initialDateTo = initialDateReceivedTo;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderID) {
		this.genderCode = genderID;
	}

	public String getOutcomeCode() {
		return outcomeCode;
	}

	public void setOutcomeCode(String outcomeID) {
		this.outcomeCode = outcomeID;
	}

	public String getReportFeatureCode() {
		return reportFeatureCode;
	}

	public void setReportFeatureCode(String reportFeatureID) {
		this.reportFeatureCode = reportFeatureID;
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

	public String getKeywordsSection3() {
		return keywordsSection3;
	}

	public void setKeywordsSection3(String keywordSearch3) {
		this.keywordsSection3 = keywordSearch3;
	}

	public String getKeywordsSection4() {
		return keywordsSection4;
	}

	public void setKeywordsSection4(String keywordSearch4) {
		this.keywordsSection4 = keywordSearch4;
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

	public ArrayList getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(ArrayList searchResults) {
		this.searchResults = searchResults;
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

	public boolean isSection3FindSearch() {
		return section3FindSearch;
	}

	public void setSection3FindSearch(boolean section3FindSearch) {
		this.section3FindSearch = section3FindSearch;
	}

	public boolean isSection4FindSearch() {
		return section4FindSearch;
	}

	public void setSection4FindSearch(boolean section4FindSearch) {
		this.section4FindSearch = section4FindSearch;
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

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getDateSelection() {
		return dateSelection;
	}

	public void setDateSelection(String dateSelection) {
		this.dateSelection = dateSelection;
	}

}
